package com.amar.soccer.test.android.event;

public class AndroidEvent
{
	public enum EventType
	{
		CLICK, DRAG, DELETE, INPUT, TRANS_ACTIVITY
	}

	protected EventType eventType;
	
	public EventType getEventType()
	{
		return eventType;
	}
}
