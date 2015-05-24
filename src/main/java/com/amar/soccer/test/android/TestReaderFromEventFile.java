package com.amar.soccer.test.android;

import java.io.EOFException;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.util.ArrayList;
import java.util.List;

import com.amar.soccer.test.android.event.AndroidEvent;
import com.amar.soccer.util.PropertiesUtil;

public class TestReaderFromEventFile
{

	public static void main( String [] args )
	{
		TestReaderFromEventFile testReader= new TestReaderFromEventFile();
		testReader.readEventFile( TestRecordReceiver.DEFAULT_FILE_NAME );
	}

	private List<AndroidEvent> eventList;

	private void readEventFile( String eventFileName )
	{
		if ( eventList == null )
		{
			eventList = new ArrayList<AndroidEvent>();
		}
		ObjectInputStream objectInputStream = PropertiesUtil.getObjectInputStream( eventFileName );

		try
		{
			Object obj = null;

			while ( ( obj = objectInputStream.readObject() ) != null )
			{
				if ( obj != null )
				{
					AndroidEvent androidEvent = ( AndroidEvent ) obj;
					eventList.add( androidEvent );
				}
			}
		}
		catch(EOFException e)
		{
			e.printStackTrace();
		}
		catch ( IOException e )
		{
			e.printStackTrace();
		}
		catch ( ClassNotFoundException e )
		{
			e.printStackTrace();
		}
		
		System.out.println( "finish" );
		for( AndroidEvent androidEvent : eventList )
		{
			System.out.println( androidEvent );
		}
	}
}
