package com.amar.soccer.test.android;

import java.util.ArrayList;
import java.util.List;

import com.amar.soccer.util.Shell;

public class AndroidCommand extends Shell
{
	public static final String CMD_CONNECT = "adb -s %s shell ";
	public static final String CMD_ALL_DEVICES = "adb devices";
	
	public String getCommandToDevice( String device , String commandDetail )
	{
		return String.format( CMD_CONNECT , device ) + commandDetail;
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
}
