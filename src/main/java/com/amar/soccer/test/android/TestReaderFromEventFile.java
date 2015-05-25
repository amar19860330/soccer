package com.amar.soccer.test.android;

import java.io.EOFException;
import java.io.File;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.util.ArrayList;
import java.util.List;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;

import com.amar.soccer.test.android.event.AndroidEvent;
import com.amar.soccer.test.android.event.AndroidEventTrans;
import com.amar.soccer.util.PropertiesUtil;

public class TestReaderFromEventFile
{
	String device;

	AndroidInput androidInput;

	Thread androidInputThread;

	public void excuteScript( String device , List<AndroidEvent> eventList , CallBack<String> callBack )
	{
		this.device = device;
		androidInput = new AndroidInput( device , eventList , callBack );
		androidInputThread = new Thread( androidInput );
		androidInputThread.start();
	}

	public void stopTest()
	{
		if ( androidInputThread != null )
		{
			androidInputThread.interrupt();
			androidInputThread = null;
			androidInput = null;
		}
	}

	public List<AndroidEvent> xmlToList( String fileName )
	{
		List<AndroidEvent> list = null;
		try
		{
			AndroidEventTrans androidEvents = null;

			File file = new File( fileName );
			JAXBContext jaxbContext = JAXBContext.newInstance( AndroidEventTrans.class );
			Unmarshaller um = jaxbContext.createUnmarshaller();
			androidEvents = ( AndroidEventTrans ) um.unmarshal( file );
			if ( androidEvents != null && androidEvents.getAndroidEventList() != null )
			{
				list = androidEvents.getAndroidEventList();
			}
		}
		catch ( JAXBException e )
		{
			e.printStackTrace();
			list = null;
		}
		return list;
	}

	private List<AndroidEvent> eventList;

	@SuppressWarnings( { "unused" } )
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
		catch ( EOFException e )
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
