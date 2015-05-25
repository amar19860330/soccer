package com.amar.soccer.test.android;

import java.util.List;

import com.amar.soccer.test.android.event.AndroidEvent;
import com.amar.soccer.test.android.event.AndroidEventTrans;
import com.amar.soccer.util.PropertiesUtil;

public class TestRecordReceiver implements CallBack<AndroidEvent>
{

	public static void main( String [] args )
	{
		TestRecordReceiver testRecordReceiver = new TestRecordReceiver( "192.168.112.101:5555" );
		testRecordReceiver.start();
	}

	public static String DEFAULT_FILE_PATH = PropertiesUtil.getRootPath() + "test/";

	public static String DEFAULT_FILE_NAME = DEFAULT_FILE_PATH + "TestRecord.xml";

	AndroidEventTrans androidEventTrans;

	public void saveRecord( String fileName )
	{
		if ( androidEventTrans == null )
		{
			androidEventTrans = new AndroidEventTrans();
		}
		androidEventTrans.listToXml( fileName );
	}

	private CallBack<AndroidEvent> uiCallBack;

	public void setUiCallBack( CallBack<AndroidEvent> uiCallBack )
	{
		this.uiCallBack = uiCallBack;
	}

	public List<AndroidEvent> getEventList()
	{
		return androidEventTrans == null ? null : androidEventTrans.getAndroidEventList();
	}

	@Override
	public void callback( AndroidEvent event ,int status)
	{
		if ( androidEventTrans == null )
		{
			androidEventTrans = new AndroidEventTrans();
		}

		if ( event != null )
		{
			androidEventTrans.getAndroidEventList().add( event );
		}

		if ( uiCallBack != null )
		{
			uiCallBack.callback( event ,0);
		}
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
