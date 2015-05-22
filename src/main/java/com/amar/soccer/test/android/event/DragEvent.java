package com.amar.soccer.test.android.event;

public class DragEvent extends AndroidEvent
{
	int x;

	int y;

	public DragEvent( int x , int y )
	{
		this.eventType = EventType.DRAG;
		this.x = x;
		this.y = y;
	}

	public int [] getPosition()
	{
		return new int [] { x, y};
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
		return "DragEvent [x=" + x + ", y=" + y + "]";
	}
	
	
}
