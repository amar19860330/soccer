package com.amar.soccer.test.android;

import java.util.List;

import com.amar.soccer.test.android.event.AndroidEvent;
import com.amar.soccer.test.android.event.ClickEvent;
import com.amar.soccer.test.android.event.DeleteEvent;
import com.amar.soccer.test.android.event.DragEvent;
import com.amar.soccer.test.android.event.InputEvent;
import com.amar.soccer.test.android.event.SleepEvent;
import com.amar.soccer.test.android.event.TransActivityEvent;

public class AndroidInput extends AndroidShell implements Runnable
{

	public static final String CMD_CLICK = "input tap %s %s";

	public static final String CMD_DELETE = "input keyevent KEYCODE_DEL";

	public static final String CMD_INPUT = "input text %s";

	public static final String CMD_DRAG = "input swipe %s %s %s %s";

	String device;

	List<AndroidEvent> androidEventList;

	CallBack<String> callBack;

	public AndroidInput( String device , List<AndroidEvent> androidEventList , CallBack<String> callBack )
	{
		this.device = device;
		this.androidEventList = androidEventList;
		this.callBack = callBack;
	}

	private void excute()
	{
		try
		{
			String perCommand = String.format( CMD_CONNECT , device );

			for( AndroidEvent event : androidEventList )
			{
				callBack( "即将执行:" + event.toString() );
				if ( event instanceof ClickEvent )
				{
					ClickEvent e = ( ClickEvent ) event;
					cmdNoWait( perCommand + String.format( CMD_CLICK , e.getX() + "" , e.getY() + "" ) );
				}
				else if ( event instanceof DeleteEvent )
				{
					cmdNoWait( perCommand + String.format( CMD_DELETE ) );
				}
				else if ( event instanceof TransActivityEvent )
				{
					TransActivityEvent e = ( TransActivityEvent ) event;
					waitForTrans( e );
				}
				else if ( event instanceof SleepEvent )
				{
					SleepEvent e = ( SleepEvent ) event;

					try
					{
						Thread.sleep( e.getSleep() );
					}
					catch ( InterruptedException e1 )
					{
						e1.printStackTrace();
					}

				}
				else if ( event instanceof InputEvent )
				{
					InputEvent e = ( InputEvent ) event;
					cmdNoWait( perCommand + String.format( CMD_INPUT , e.getInput() ) );
				}
				else if ( event instanceof DragEvent )
				{
					DragEvent e = ( DragEvent ) event;
					cmdNoWait( perCommand + String.format( CMD_CLICK , e.getStart_x() + "" , e.getStart_y() + "" , e.getEnd_x() + "" , e.getEnd_y() + "" ) );
				}
				else
				{
					callBack( "未匹配的事件类型" );
				}

				Thread.sleep( 50 );
			}

			callBack( "测试完毕" );
		}
		catch ( Exception e )
		{
			e.printStackTrace();
			callBack( "发生异常\n" + e.getMessage() );
		}
	}

	private void callBack( String info )
	{
		if ( callBack != null )
		{
			callBack.callback( info );
		}
	}

	public static long timeOut = 30000;

	public static long cycle_period = 300;

	private long startTime;

	private void waitForTrans( TransActivityEvent event )
	{
		String currentActivityName = "";
		startTime = System.currentTimeMillis();
		while ( true )
		{
			currentActivityName = new GetAndroidInfoImmediately( device , null ).getCurrentActivityName();
			if ( event.getToActivity().equals( currentActivityName ) || ( System.currentTimeMillis() - startTime ) > timeOut )
			{
				break;
			}

			try
			{
				Thread.sleep( cycle_period );
			}
			catch ( InterruptedException e1 )
			{
				e1.printStackTrace();
			}
		}
	}

	@Override
	public void run()
	{
		excute();
	}

}
