package com.amar.soccer.test.android.event;

import java.io.Serializable;

import javax.xml.bind.annotation.XmlElement;

public class AndroidEvent implements Serializable
{
	private static final long serialVersionUID = 6610026210920113208L;

	public enum EventType
	{
		CLICK, DRAG, DELETE, INPUT, TRANS_ACTIVITY,SLEEP
	}

	@XmlElement
	protected EventType eventType;
	
	public EventType getEventType()
	{
		return eventType;
	}
}
