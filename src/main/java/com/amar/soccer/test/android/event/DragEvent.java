package com.amar.soccer.test.android.event;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "DragEvent")
@XmlAccessorType (XmlAccessType.NONE)
public class DragEvent extends AndroidEvent
{
	private static final long serialVersionUID = - 8691343978645366569L;
	@XmlAttribute
	private int x;
	@XmlAttribute
	private int y;

	public DragEvent()
	{
		this.eventType = EventType.DRAG;
	}
	
	public DragEvent( int x , int y )
	{
		this.eventType = EventType.DRAG;
		this.x = x;
		this.y = y;
	}

	public int [] getPosition()
	{
		return new int [] { x, y };
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

	@Override
	public String toString()
	{
		return "拖拽到：" + x + "," + y + "";
	}

}
