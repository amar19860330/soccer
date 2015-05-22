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
import com.amar.soccer.test.android.event.TransActivityEvent;

public class AndroidMonitor extends AndroidCommand implements CallBack<String>, Runnable
{
	public static final String CMD_ALL_EVENT = "getevent -lt";

	public static final String RULER_IS_CLICK_DOWN = "^.*BTN_TOUCH.*DOWN$";

	public static final String RULER_IS_CLICK_UP = "^.*BTN_TOUCH.*UP$";

	public static final String RULER_IS_POSITION_X = "^(.*ABS_MT_POSITION_X.*)([0-9|abcdef|ABCDEF]{8})$";

	public static final String RULER_IS_POSITION_Y = "^(.*ABS_MT_POSITION_Y.*)([0-9|abcdef|ABCDEF]{8})$";

	public static final String RULER_IS_KEY_SHIFT_DOWN = "^。*SHIFT.*DOWN$";

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
	ExecutorService singleThreadExecutor;

	private String device;

	/**
	 * 判断无效值的方法
	 */
	private int invalidPosition = - 99999;

	/**
	 * 用于接收点击按下之后的坐标值
	 */
	private boolean isClickDown = false;

	/**
	 * 用于接收抬起之后的坐标值
	 */
	private boolean isClickUp = false;

	/**
	 * 用于判断点击之后何时抬起，是否有拖拽操作
	 */
	private boolean clickDown = false;

	private int position_x = invalidPosition;

	private int position_y = invalidPosition;

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

	public AndroidMonitor( String device , CallBack<AndroidEvent> callbackEvent )
	{
		init();
		this.device = device;
		this.callbackEvent = callbackEvent;
	}

	@Override
	public void callback( String info )
	{
		if ( ! currentActivityName.equals( info ) )
		{
			sendEvent( new TransActivityEvent( currentActivityName , info ) );
		}
		currentActivityName = info;
	}

	private void readCurrentActivityName()
	{
		singleThreadExecutor.execute( new GetAndroidInfoImmediately( device , this ) );
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
			
			if(singleThreadExecutor!=null)
			{
				singleThreadExecutor.shutdownNow();
				singleThreadExecutor = null;
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

					readCurrentActivityName();// 读取当前 activity name

					/************ 判断点击开始 ********/
					if ( isClickDown( line ) )
					{
						isClickDown = true;
						clickDown = true;
						continue;
					}

					if ( isClickUp( line ) )
					{
						isClickUp = true;
						clickDown = false;
						continue;
					}

					if ( isClickDown ) // 采集按下时的坐标
					{
						int position = getPositionX( line );
						if ( position != invalidPosition )
						{
							position_x = position;
							continue;
						}
						position = getPositionY( line );
						if ( position != invalidPosition )
						{
							position_y = position;
							isClickDown = false;
							continue;
						}

					}

					if ( isClickUp )// 采集抬起时的坐标
					{
						int x = getPositionX( line );
						if ( x != invalidPosition )
						{
							position_x = x;
							continue;
						}
						int y = getPositionY( line );
						if ( y != invalidPosition )
						{
							position_y = y;

							if ( ! isDrag )
							{
								AndroidEvent event = new ClickEvent( position_x , position_y );
								sendEvent( event );
							}
							isClickUp = false;
							clickDown = false;
							isDrag = false;
							continue;
						}
					}

					if ( clickDown )
					{
						int x = getPositionX( line );
						if ( x != invalidPosition && x != position_x )
						{
							position_x = x;
							isDrag = true;
							continue;
						}
						int y = getPositionY( line );
						if ( y != invalidPosition && y != position_y )
						{
							isDrag = true;
							position_y = y;
							AndroidEvent event = new DragEvent( position_x , position_y );
							sendEvent( event );
							continue;
						}
					}

					/************ 判断点击结束 ********/

					/** **/
					if ( isKeyShiftDown( line ) )
					{
						shiftIsDown = true;
					}
					else if ( isKeyShiftUp( line ) )
					{
						shiftIsDown = false;
					}

					if ( isKeyDeleteDown( line ) )
					{
						AndroidEvent event = new DeleteEvent();
						sendEvent( event );
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
		singleThreadExecutor = Executors.newSingleThreadExecutor();
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

	void sendEvent( AndroidEvent event )
	{
		// System.out.println( event.toString() );

		if ( callbackEvent != null )
		{
			callbackEvent.callback( event );
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
		int position = invalidPosition;

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
		int position = invalidPosition;

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
