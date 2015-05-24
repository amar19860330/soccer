package com.amar.soccer.test.android.event;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "SleepEvent")
@XmlAccessorType (XmlAccessType.FIELD)
public class SleepEvent extends AndroidEvent
{
	private static final long serialVersionUID = - 7765948320020014486L;
	private long sleep;
	
	public SleepEvent()
	{
		this.eventType = EventType.SLEEP;
	}
	public SleepEvent(long sleep)
	{
		this.eventType = EventType.SLEEP;
		this.sleep = sleep;
	}

	@Override
	public String toString()
	{
		return "停顿了：" + sleep + "毫秒";
	}
	public long getSleep()
	{
		return sleep;
	}
	public void setSleep( long sleep )
	{
		this.sleep = sleep;
	}
	
}
