package com.amar.soccer.test.android.event;

public class ClickEvent extends AndroidEvent
{
	int x;
	int y;

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
		return "ClickEvent [x=" + x + ", y=" + y + "]";
	}
	
	
}
