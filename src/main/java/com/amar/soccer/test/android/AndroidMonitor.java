package com.amar.soccer.test.android;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import com.amar.soccer.test.android.event.AndroidEvent;
import com.amar.soccer.test.android.event.ClickEvent;
import com.amar.soccer.test.android.event.DeleteEvent;
import com.amar.soccer.test.android.event.DragEvent;
import com.amar.soccer.test.android.event.InputEvent;
import com.amar.soccer.test.android.event.SleepEvent;
import com.amar.soccer.test.android.event.TransActivityEvent;

public class AndroidMonitor extends AndroidCommand implements CallBack<String>, Runnable
{
	public static final String CMD_ALL_EVENT = "getevent -lt";

	public static final String RULER_IS_CLICK_DOWN = "^.*BTN_TOUCH.*DOWN$";

	public static final String RULER_IS_CLICK_UP = "^.*BTN_TOUCH.*UP$";

	public static final String RULER_IS_POSITION_X = "^(.*ABS_MT_POSITION_X.*)([0-9|abcdef|ABCDEF]{8})$";

	public static final String RULER_IS_POSITION_Y = "^(.*ABS_MT_POSITION_Y.*)([0-9|abcdef|ABCDEF]{8})$";

	public static final String RULER_IS_KEY_SHIFT_DOWN = "^.*SHIFT.*DOWN$";

	public static final String RULER_IS_KEY_SHIFT_UP = "^.*SHIFT.*UP$";

	public static final String RULER_IS_KEY_BACKSPACE_DOWN = "^.*KEY_BACKSPACE.*DOWN$";

	public static final String RULER_IS_KEY_BACKSPACE_UP = "^.*KEY_BACKSPACE.*UP$";

	public static final String RULER_IS_KEY_CHAR_DOWN = "^(.*EV_KEY.*KEY_)([0-9|a-z|A-Z]{1})(.*)DOWN$";

	public static final String RULER_IS_KEY_CHAR_UP = "^(.*EV_KEY.*KEY_)([0-9|a-z|A-Z]{1})(.*)UP$";

	public static final String RULER_IS_UPPER_LATTER = "^[A-Z]$";

	public static final String RULER_IS_LOWER_LATTER = "^[A-Z]$";

	public static final String RULER_IS_NUMBER = "^[0-9]$";

	/**
	 * 按住 shift 之后的键盘映射表
	 */
	public static Map<String,String> shiftMap;

	/**
	 * 可能会频繁发送读取当前 activity 状态，所以用个线程池
	 */
	ExecutorService threadExecutor;

	private String device;


	/**
	 * 用于接收点击按下之后的坐标值
	 */
	private boolean isClickDown = false;

	/**
	 * 用于判断点击之后何时抬起，是否有拖拽操作
	 */
	private boolean clickDown = false;

	private int position_x = AndroidEvent.InvalidValue;

	private int position_y = AndroidEvent.InvalidValue;

	private int drag_x = AndroidEvent.InvalidValue;

	private int drag_y = AndroidEvent.InvalidValue;

	/**
	 * 是否在拖拽中
	 */
	private boolean isDrag = false;

	/**
	 * 是否按住了 shift 键
	 */
	private boolean shiftIsDown = false;

	/**
	 * 当前 activity 的名字,用于判断是否有切换
	 */
	private String currentActivityName = "";

	CallBack<AndroidEvent> callbackEvent;

	public static void main( String args[] )
	{
		AndroidMonitor androidMonitor = new AndroidMonitor( "192.168.112.101:5555" , null );
		androidMonitor.run();
	}

	public AndroidMonitor( String device , CallBack<AndroidEvent> callbackEvent )
	{
		init();
		this.device = device;
		this.callbackEvent = callbackEvent;
	}

	@Override
	public void callback( String info ,int status)
	{
		if ( ! currentActivityName.equals( info ) )
		{
			sendEvent( new TransActivityEvent( currentActivityName , info ) );
			currentActivityName = info;
		}
	}

	private void readCurrentActivityName()
	{
		threadExecutor.execute( new GetAndroidInfoImmediately( device , this ) );
	}

	@Override
	public void run()
	{
		getAllEvent();
	}

	public void removeListener()
	{
		try
		{
			if ( getProcess() != null )
			{
				getProcess().destroy();
			}

			if ( getInput() != null )
			{
				getInput().close();
			}

			if ( bufferedReader != null )
			{
				bufferedReader.close();
				bufferedReader = null;
			}

			if ( threadExecutor != null )
			{
				threadExecutor.shutdownNow();
				threadExecutor = null;
			}
		}
		catch ( IOException e )
		{
			e.printStackTrace();
		}
	}

	BufferedReader bufferedReader;

	private void getAllEvent()
	{
		try
		{
			readCurrentActivityName();// 读取当前 activity name

			Thread.sleep( 100 );

			originShell( getCommandToDevice( device , CMD_ALL_EVENT ) );
			bufferedReader = new BufferedReader( new InputStreamReader( getInput() ) );

			String line;
			while ( ( line = bufferedReader.readLine() ) != null )
			{
				try
				{
					if ( line == null || "".equals( line ) )
					{
						continue;
					}
					else
					{
						line = line.trim();
					}

					// System.out.println( line );

					/************ 判断点击开始 ********/
					if ( isClickDown( line ) )
					{
						isClickDown = true;
						clickDown = true;
						continue;
					}

					if ( isClickUp( line ) )
					{
						if ( ! isDrag ) // 没有拖拽，直接点击
						{
							AndroidEvent event = new ClickEvent( position_x , position_y );
							sendEvent( event );

							position_x = AndroidEvent.InvalidValue;
							position_y = AndroidEvent.InvalidValue;
						}
						else
						{ // 发生了拖拽
							AndroidEvent event = new DragEvent( position_x , position_y , drag_x , drag_y );
							sendEvent( event );

							isDrag = false;
							drag_x = AndroidEvent.InvalidValue;
							drag_y = AndroidEvent.InvalidValue;
							position_x = AndroidEvent.InvalidValue;
							position_y = AndroidEvent.InvalidValue;
						}

						clickDown = false;
						continue;
					}

					if ( isClickDown ) // 采集按下时的坐标
					{
						int position = getPositionX( line );
						if ( position != AndroidEvent.InvalidValue )
						{
							position_x = position;
							continue;
						}
						position = getPositionY( line );
						if ( position != AndroidEvent.InvalidValue )
						{
							position_y = position;
							isClickDown = false;
							continue;
						}
					}

					if ( clickDown )
					{
						int x = getPositionX( line );
						if ( x != AndroidEvent.InvalidValue && x != position_x )
						{
							drag_x = x;
							isDrag = true;
							continue;
						}
						int y = getPositionY( line );
						if ( y != AndroidEvent.InvalidValue && y != position_y )
						{
							isDrag = true;
							drag_y = y;
							continue;
						}
					}

					/************ 判断点击结束 ********/

					/** **/
					if ( isKeyShiftDown( line ) )
					{
						shiftIsDown = true;
						continue;
					}
					else if ( isKeyShiftUp( line ) )
					{
						shiftIsDown = false;
						continue;
					}

					if ( isKeyDeleteDown( line ) )
					{
						AndroidEvent event = new DeleteEvent();
						sendEvent( event );
						continue;
					}

					String key = isKeyCharDown( line );
					if ( key != null )
					{
						if ( shiftIsDown )
						{
							key = shiftString( key );
						}

						AndroidEvent event = new InputEvent( key );
						sendEvent( event );
						continue;
					}

					/** **/
				}
				catch ( Exception e )
				{
					e.printStackTrace();
				}
			}
		}
		catch ( Exception e )
		{
			e.printStackTrace();
		}
	}

	protected void init()
	{
		// threadExecutor = Executors.newSingleThreadExecutor();
		threadExecutor = Executors.newCachedThreadPool();
		shiftMap = new HashMap<String,String>();
		shiftMap.put( "`" , "~" );
		shiftMap.put( "1" , "!" );
		shiftMap.put( "2" , "@" );
		shiftMap.put( "3" , "#" );
		shiftMap.put( "4" , "$" );
		shiftMap.put( "5" , "%" );
		shiftMap.put( "6" , "^" );
		shiftMap.put( "7" , "&" );
		shiftMap.put( "8" , "*" );
		shiftMap.put( "9" , "(" );
		shiftMap.put( "0" , ")" );
		shiftMap.put( "-" , "_" );
		shiftMap.put( "=" , "+" );
		shiftMap.put( "\\" , "|" );
	}

	private String shiftString( String originString )
	{
		Matcher matcher1 = Pattern.compile( RULER_IS_UPPER_LATTER ).matcher( originString );
		Matcher matcher2 = Pattern.compile( RULER_IS_LOWER_LATTER ).matcher( originString );
		Matcher matcher3 = Pattern.compile( RULER_IS_NUMBER ).matcher( originString );
		String result = originString;
		if ( matcher1.matches() )
		{
			result = originString.toLowerCase();
		}
		else if ( matcher2.matches() )
		{
			result = originString.toUpperCase();
		}
		else if ( matcher3.matches() )
		{
			result = shiftMap.get( originString );
		}
		return result;
	}

	private boolean isKeyShiftDown( String originString )
	{
		Matcher matcher = Pattern.compile( RULER_IS_KEY_SHIFT_DOWN ).matcher( originString );

		return matcher.matches();
	}

	private boolean isKeyShiftUp( String originString )
	{
		Matcher matcher = Pattern.compile( RULER_IS_KEY_SHIFT_UP ).matcher( originString );

		return matcher.matches();
	}

	private boolean isKeyDeleteDown( String originString )
	{
		Matcher matcher = Pattern.compile( RULER_IS_KEY_BACKSPACE_DOWN ).matcher( originString );

		return matcher.matches();
	}

	@SuppressWarnings( "unused" )
	private boolean isKeyDeleteUp( String originString )
	{
		Matcher matcher = Pattern.compile( RULER_IS_KEY_BACKSPACE_UP ).matcher( originString );

		return matcher.matches();
	}

	private String isKeyCharDown( String originString )
	{
		String result = null;
		try
		{
			Matcher matcher = Pattern.compile( RULER_IS_KEY_CHAR_DOWN ).matcher( originString );

			if ( matcher.matches() && matcher.groupCount() == 3 )
			{
				result = matcher.group( 2 );
			}
		}
		catch ( Exception e )
		{
			e.printStackTrace();
		}

		return result;
	}

	@SuppressWarnings( "unused" )
	private boolean isKeyCharUp( String originString )
	{
		Matcher matcher = Pattern.compile( RULER_IS_KEY_CHAR_UP ).matcher( originString );

		return matcher.matches();
	}

	long lastEventTime = 0;

	void sendEvent( AndroidEvent event )
	{
		if ( callbackEvent != null )
		{
			callbackEvent.callback( event ,0);
			if ( lastEventTime != 0 )
			{
				callbackEvent.callback( new SleepEvent( System.currentTimeMillis() - lastEventTime ) ,0);

			}
			lastEventTime = System.currentTimeMillis();
		}

		if ( event instanceof TransActivityEvent )
		{
			return;
		}
		else
		{
			readCurrentActivityName();
		}
	}

	private boolean isClickDown( String originString )
	{
		Matcher matcher = Pattern.compile( RULER_IS_CLICK_DOWN ).matcher( originString );

		return matcher.matches();
	}

	private boolean isClickUp( String originString )
	{
		Matcher matcher = Pattern.compile( RULER_IS_CLICK_UP ).matcher( originString );

		return matcher.matches();
	}

	private int getPositionX( String originString )
	{
		int position = AndroidEvent.InvalidValue;

		try
		{
			Matcher matcher = Pattern.compile( RULER_IS_POSITION_X ).matcher( originString );

			if ( matcher.matches() && matcher.groupCount() == 2 )
			{
				position = Integer.parseInt( matcher.group( 2 ) , 16 );
			}
		}
		catch ( Exception e )
		{
			e.printStackTrace();
		}
		return position;
	}

	private int getPositionY( String originString )
	{
		int position = AndroidEvent.InvalidValue;

		try
		{
			Matcher matcher = Pattern.compile( RULER_IS_POSITION_Y ).matcher( originString );

			if ( matcher.matches() && matcher.groupCount() == 2 )
			{
				position = Integer.parseInt( matcher.group( 2 ) , 16 );
			}
		}
		catch ( Exception e )
		{
			e.printStackTrace();
		}
		return position;
	}

}
