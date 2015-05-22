package com.amar.soccer.test.android.event;


public class DeleteEvent extends AndroidEvent
{
	public DeleteEvent()
	{
		this.eventType = EventType.INPUT;
	}

	@Override
	public String toString()
	{
		return "DeleteEvent []";
	}
	
	
}
