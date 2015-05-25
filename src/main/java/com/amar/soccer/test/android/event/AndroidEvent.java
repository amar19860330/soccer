package com.amar.soccer.test.android.event;

import java.io.Serializable;

import javax.xml.bind.annotation.XmlAttribute;

public class AndroidEvent implements Serializable
{
	private static final long serialVersionUID = 6610026210920113208L;

	/**
	 * 判断无效值的方法
	 */
	public static final int InvalidValue = - 99999;

	public enum EventType
	{
		CLICK, DRAG, DELETE, INPUT, TRANS_ACTIVITY, SLEEP
	}

	@XmlAttribute
	protected EventType eventType;

	public EventType getEventType()
	{
		return eventType;
	}
}
