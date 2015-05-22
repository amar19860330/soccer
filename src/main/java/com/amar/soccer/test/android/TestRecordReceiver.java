package com.amar.soccer.test.android;

import com.amar.soccer.test.android.event.AndroidEvent;

public class TestRecordReceiver implements CallBack<AndroidEvent>
{

	public static void main( String [] args )
	{
		TestRecordReceiver testRecordReceiver = new TestRecordReceiver("192.168.112.102:5555");
		testRecordReceiver.start();
		
		try
		{
			Thread.sleep( 10000 );
			testRecordReceiver.stop();
		}
		catch ( InterruptedException e )
		{
			e.printStackTrace();
		}
		
	}

	@Override
	public void callback( AndroidEvent t )
	{
		System.out.println(t.toString());
	}

	String device;

	public TestRecordReceiver( String device )
	{
		this.device = device;

	}

	AndroidMonitor androidMonitor;

	Thread monitorThread;

	public void start()
	{
		stop();
		androidMonitor = new AndroidMonitor( device , this );
		monitorThread = new Thread( androidMonitor );
		monitorThread.start();
	}

	public void stop()
	{
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
