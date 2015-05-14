package com.amar.soccer.test;

import java.io.BufferedReader;
import java.io.InputStreamReader;

import com.amar.soccer.test.android.AndroidShell;

public class MonitorMobile implements Runnable
{

	public interface CallBack
	{
		void event( int type , int x , int y );
	}

	public CallBack callBack;

	public static void main( String [] args )
	{
		// String device = "192.168.56.101:5555";
		// MonitorMobile monitorMobile = new MonitorMobile();
		// monitorMobile.monitorTouch( device );

	}

	String device;

	public void setDevice( String device )
	{
		this.device = device;
	}

	public void run()
	{
		monitorTouch( device );
	}

	AndroidShell androidShell;

	public void stop()
	{
		if ( androidShell != null )
			androidShell.getProcess().destroy();
	}

	public void monitorTouch( String device )
	{
		androidShell = new AndroidShell();
		androidShell.monitorPosition( device );

		String line;

		try
		{
			int [] position = { - 1, - 1 };
			BufferedReader br = new BufferedReader( new InputStreamReader( androidShell.getInput() ) );

			while ( ( line = br.readLine() ) != null )
			{
				if ( line.contains( "0035" ) )
				{
					int start = line.indexOf( "0035" ) + 5;
					int x = getIntFormInt16String( line.substring( start ) , line );
					position[ 0 ] = x;// ( int ) ( x * size[ 2 ] );
				}
				if ( line.contains( "0036" ) )
				{
					int start = line.indexOf( "0036" ) + 5;
					int y = getIntFormInt16String( line.substring( start ) , line );
					position[ 1 ] = y;// ( int ) ( y * size[ 3 ] );
				}

				if ( position[ 0 ] != - 1 && position[ 1 ] != - 1 )
				{
					// System.out.println( "position:" + position[ 0 ] + "," + position[ 1 ] );
					if ( callBack != null )
						callBack.event( 1 , position[ 0 ] , position[ 1 ] );
					position[ 0 ] = - 1;
					position[ 1 ] = - 1;
				}
			}

			System.out.println( "over" );
		}
		catch ( Exception e )
		{
			e.printStackTrace();
		}
	}

	public int getIntFormInt16String( String int16String , String line )
	{
		int data = - 1;
		try
		{
			data = Integer.parseInt( int16String , 16 );
		}
		catch ( Exception e )
		{
			e.printStackTrace();
			System.out.println( "error:" + int16String + "..." + line );
		}
		return data;
	}

	public void MonitorMobile()
	{

	}

	public CallBack getCallBack()
	{
		return callBack;
	}

	public void setCallBack( CallBack callBack )
	{
		this.callBack = callBack;
	}

}
