package com.amar.soccer.test.android;

import com.amar.soccer.test.android.event.AndroidEvent;
import com.amar.soccer.test.android.event.AndroidEventTrans;

public class TestRecordReceiver implements CallBack<AndroidEvent>
{

	public static void main( String [] args )
	{
		TestRecordReceiver testRecordReceiver = new TestRecordReceiver( "192.168.112.101:5555" );
		testRecordReceiver.start();
	}

	public static String DEFAULT_FILE_PATH = "test/";

	public static String DEFAULT_FILE_NAME = DEFAULT_FILE_PATH + "TestRecord.xml";

	AndroidEventTrans androidEventTrans;

	public void saveRecord()
	{

	}

	private CallBack<AndroidEvent> uiCallBack;

	public void setUiCallBack( CallBack<AndroidEvent> uiCallBack )
	{
		this.uiCallBack = uiCallBack;
	}

	@Override
	public void callback( AndroidEvent event )
	{
		System.out.println( "receiver:" + event.toString() );
//		if ( androidEventTrans == null )
//		{
//			androidEventTrans = new AndroidEventTrans();
//		}
//
//		if ( event != null )
//		{
//			androidEventTrans.getAndroidEventList().add( event );
//		}
//
//		if ( uiCallBack != null )
//		{
//			uiCallBack.callback( event );
//		}
	}

	String device;

	public TestRecordReceiver( String device )
	{
		this.device = device;

	}

	AndroidMonitor androidMonitor;

	Thread monitorThread;

	public boolean isRunning = false;

	public void start()
	{
		stop();
		isRunning = true;
		androidMonitor = new AndroidMonitor( device , this );
		monitorThread = new Thread( androidMonitor );
		monitorThread.start();
	}

	public void stop()
	{
		isRunning = false;
		if ( androidMonitor != null )
		{
			androidMonitor.removeListener();
			androidMonitor = null;
		}

		if ( monitorThread != null )
		{
			monitorThread.interrupt();
			monitorThread = null;
		}
	}
}
