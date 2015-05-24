package com.amar.soccer.test.android.event;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "AndroidEvent")
@XmlAccessorType (XmlAccessType.NONE)
public class InputEvent extends AndroidEvent
{
	private static final long serialVersionUID = 3608304206705269091L;
	@XmlAttribute
	private String input;
	public InputEvent()
	{
		this.eventType = EventType.INPUT;
	}
	public InputEvent( String input )
	{
		this.input = input;
		this.eventType = EventType.INPUT;
	}

	public String getInput()
	{
		return input;
	}
	public void setInput( String input )
	{
		this.input = input;
	}

	public String toString()
	{
		return "输入：" + input + "";
	}

}
