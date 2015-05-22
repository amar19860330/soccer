package com.amar.soccer.test.android;

import java.util.ArrayList;
import java.util.List;

import com.amar.soccer.util.Shell;

public class AndroidShell extends Shell
{
	public static final String CMD_CLICK = "input tap ";

	public static final String CMD_DELETE = "input keyevent KEYCODE_DEL";

	public static final String CMD_INPUT = "input text ";

	public static final String CMD_QUERY_CURRENT_ACTIVITY = "dumpsys input | grep FocusedApplication";

	public static final String CMD_CONNECT = "adb -s %s shell ";

	public static final String CMD_EVENT = "getevent";

	public static final String CMD_EVENT_POSITION_SCREEN = "getevent -p | grep -e \"0035\" -e \"0036\" ";

	public static final String CMD_ALL_DEVICES = "adb devices";

	public static final String CMD_EVENT_MONITOR_TOUCH = "getevent | grep -e \"0035\" -e \"0036\"";

	public void monitorPosition( String device )
	{
		originShell( getCommandByDevice( device , CMD_EVENT_MONITOR_TOUCH ) );
	}

	public String getCommandByDevice( String device , String commandDetail )
	{
		return String.format( CMD_CONNECT , device ) + commandDetail;
	}

	public float [] getScreenSize( String device )
	{
		float [] screenSize = { 0, 0, 0, 0 };

		String cmd = String.format( CMD_CONNECT , device ) + CMD_EVENT_POSITION_SCREEN;

		String outPut = cmdNoWait( cmd );

		String [] outPutArray = outPut.split( "\n" );

		for( String originOutput : outPutArray )
		{
			if ( originOutput.contains( "0035" ) && originOutput.contains( "resolution" ) )
			{
				int start = originOutput.indexOf( "max" );
				String first = originOutput.substring( start , originOutput.length() ).trim();
				int end = first.indexOf( "," ) + start;
				screenSize[ 0 ] = Integer.parseInt( originOutput.substring( start + 3 , end ).trim() );
			}
			if ( originOutput.contains( "0036" ) && originOutput.contains( "resolution" ) )
			{
				int start = originOutput.indexOf( "max" );
				String first = originOutput.substring( start , originOutput.length() ).trim();
				int end = first.indexOf( "," ) + start;
				screenSize[ 1 ] = Float.parseFloat( originOutput.substring( start + 3 , end ).trim() );
			}
		}

		if ( screenSize[ 0 ] != 0 && screenSize[ 1 ] != 0 )
		{
			screenSize[ 2 ] = screenSize[ 0 ] / screenSize[ 1 ];
			screenSize[ 3 ] = screenSize[ 1 ] / screenSize[ 0 ];
		}

		return screenSize;
	}

	public List<String> getDevices()
	{
		List<String> devices = new ArrayList<String>();

		String result = cmd( CMD_ALL_DEVICES );
		String [] originArray = result.split( "\n" );

		if ( originArray != null && originArray.length > 0 )
		{
			for( String originString : originArray )
			{
				if ( originString == null )
					continue;

				if ( originString.toLowerCase().startsWith( "list" ) )
				{
					continue;
				}
				else
				{
					int index = originString.toLowerCase().indexOf( "device" );
					devices.add( originString.substring( 0 , index ).trim() );
				}
			}
		}

		return devices;
	}

	public void deleteText( String device )
	{
		String preCommand = String.format( CMD_CONNECT , device );
		for( int i = 0 ; i < 10 ; i ++ )
		{
			cmd( preCommand + CMD_DELETE );
		}
	}

	

}
