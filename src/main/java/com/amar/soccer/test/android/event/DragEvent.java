package com.amar.soccer.test.android.event;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement( name = "DragEvent" )
@XmlAccessorType( XmlAccessType.NONE )
public class DragEvent extends AndroidEvent
{
	private static final long serialVersionUID = - 8691343978645366569L;

	@XmlAttribute
	private int start_x;

	@XmlAttribute
	private int start_y;

	@XmlAttribute
	private int end_x;

	@XmlAttribute
	private int end_y;

	public DragEvent()
	{
		this.eventType = EventType.DRAG;
	}

	public DragEvent( int start_x , int start_y , int end_x , int end_y )
	{
		this.eventType = EventType.DRAG;
		this.start_x = start_x;
		this.start_y = start_y;
		this.end_x = end_x;
		this.end_y = end_y;

		if ( end_x == InvalidValue )
			end_x = start_x;
		if ( end_y == InvalidValue )
			end_y = start_y;
	}

	public int [] getPosition()
	{
		return new int [] { start_x, start_y, end_x, end_y };
	}

	@Override
	public String toString()
	{
		return "拖拽到：" + start_x + "," + start_y + " to " + end_x + "," + end_y;
	}

	public int getStart_x()
	{
		return start_x;
	}

	public void setStart_x( int start_x )
	{
		this.start_x = start_x;
	}

	public int getStart_y()
	{
		return start_y;
	}

	public void setStart_y( int start_y )
	{
		this.start_y = start_y;
	}

	public int getEnd_x()
	{
		return end_x;
	}

	public void setEnd_x( int end_x )
	{
		this.end_x = end_x;
	}

	public int getEnd_y()
	{
		return end_y;
	}

	public void setEnd_y( int end_y )
	{
		this.end_y = end_y;
	}

}
