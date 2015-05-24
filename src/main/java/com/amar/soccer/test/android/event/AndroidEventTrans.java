package com.amar.soccer.test.android.event;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import com.amar.soccer.util.PropertiesUtil;
import javax.xml.bind.annotation.XmlElements;

@XmlRootElement( name = "AndroidEvents" )
@XmlAccessorType( XmlAccessType.FIELD )
public class AndroidEventTrans
{
	@XmlElements( { @XmlElement( name = "ClickEvent" , type = ClickEvent.class ), @XmlElement( name = "InputEvent" , type = InputEvent.class ),
			@XmlElement( name = "DeleteEventv" , type = DeleteEvent.class ), @XmlElement( name = "DragEvent" , type = DragEvent.class ), @XmlElement( name = "SleepEvent" , type = SleepEvent.class ),
			@XmlElement( name = "TransActivityEvent" , type = TransActivityEvent.class ) } )
	private List<AndroidEvent> androidEventList;

	public List<AndroidEvent> getAndroidEventList()
	{
		if ( androidEventList == null )
		{
			androidEventList = new ArrayList<AndroidEvent>();
		}
		return androidEventList;
	}

	public void setAndroidEventList( List<AndroidEvent> androidEventList )
	{
		this.androidEventList = androidEventList;
	}

	public void listToXml( String fileName )
	{
		try
		{
			JAXBContext jaxbContext = JAXBContext.newInstance( AndroidEventTrans.class );
			Marshaller jaxbMarshaller = jaxbContext.createMarshaller();
			jaxbMarshaller.setProperty( Marshaller.JAXB_FORMATTED_OUTPUT , true );// format the xml
			jaxbMarshaller.setProperty( Marshaller.JAXB_ENCODING , "UTF-8" );

			jaxbMarshaller.marshal( this , new File( fileName ) );
		}
		catch ( JAXBException e )
		{
			e.printStackTrace();
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
				for( AndroidEvent event : list )
				{
					System.out.println( event.toString() );
				}
			}
		}
		catch ( JAXBException e )
		{
			e.printStackTrace();
		}
		return list;
	}

	public static void main( String args[] )
	{
		// java to xml
		AndroidEventTrans AndroidEvents = new AndroidEventTrans();
		// AndroidEvents.testListToXml();
		AndroidEvents.testXmlToList();

	}
	
	// only test for myself
	public void testXmlToList()
	{

		try
		{
			AndroidEventTrans androidEvents = null;
			File file = new File( PropertiesUtil.getRootPath() + "test2.xml" );
			JAXBContext jaxbContext = JAXBContext.newInstance( AndroidEventTrans.class );
			Unmarshaller um = jaxbContext.createUnmarshaller();
			androidEvents = ( AndroidEventTrans ) um.unmarshal( file );
			if ( androidEvents != null && androidEvents.getAndroidEventList() != null )
			{
				List<AndroidEvent> list = androidEvents.getAndroidEventList();
				for( AndroidEvent event : list )
				{
					System.out.println( event.toString() );
				}
			}
		}
		catch ( JAXBException e )
		{
			e.printStackTrace();
		}
	}

	// only test for myself
	public void testListToXml()
	{
		ClickEvent clickEvent_1 = new ClickEvent( 331 , 220 );
		ClickEvent clickEvent_2 = new ClickEvent( 331 , 220 );
		InputEvent input_1 = new InputEvent( "aaaa" );
		InputEvent input_2 = new InputEvent( "bbb" );
		TransActivityEvent transActivityEvent_1 = new TransActivityEvent( "A.java" , "B.java" );
		TransActivityEvent transActivityEvent_2 = new TransActivityEvent( "C.java" , "d.java" );
		SleepEvent sleepEvent_1 = new SleepEvent( 200 );
		SleepEvent sleepEvent_2 = new SleepEvent( 300 );

		AndroidEventTrans androidEvents = new AndroidEventTrans();
		androidEvents.getAndroidEventList().add( clickEvent_1 );
		androidEvents.getAndroidEventList().add( clickEvent_2 );
		androidEvents.getAndroidEventList().add( input_1 );
		androidEvents.getAndroidEventList().add( input_2 );
		androidEvents.getAndroidEventList().add( transActivityEvent_1 );
		androidEvents.getAndroidEventList().add( transActivityEvent_2 );
		androidEvents.getAndroidEventList().add( sleepEvent_1 );
		androidEvents.getAndroidEventList().add( sleepEvent_2 );

		try
		{
			JAXBContext jaxbContext = JAXBContext.newInstance( AndroidEventTrans.class );
			Marshaller jaxbMarshaller = jaxbContext.createMarshaller();
			jaxbMarshaller.setProperty( Marshaller.JAXB_FORMATTED_OUTPUT , true );// format the xml
			jaxbMarshaller.setProperty( Marshaller.JAXB_ENCODING , "UTF-8" );

			jaxbMarshaller.marshal( androidEvents , new File( PropertiesUtil.getRootPath() + "test2.xml" ) );
		}
		catch ( JAXBException e )
		{
			e.printStackTrace();
		}
	}
}
