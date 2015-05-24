package com.amar.soccer.test.android.event;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlRootElement;


@XmlRootElement(name = "AndroidEvent")
@XmlAccessorType (XmlAccessType.NONE)
public class ClickEvent extends AndroidEvent
{
	private static final long serialVersionUID = - 2509287324925453211L;
	@XmlAttribute
	private int x;
	@XmlAttribute
	private int y;

	public ClickEvent()
	{
		this.eventType = EventType.CLICK;
	}
	public ClickEvent( int x , int y )
	{
		this.eventType = EventType.CLICK;
		this.x = x;
		this.y = y;
	}

	public int getX()
	{
		return x;
	}

	public void setX( int x )
	{
		this.x = x;
	}

	public int getY()
	{
		return y;
	}

	public void setY( int y )
	{
		this.y = y;
	}

	public int [] getPosition()
	{
		return new int [] { x, y };
	}

	@Override
	public String toString()
	{
		return "点击了：" + x + "," + y + "";
	}

}
