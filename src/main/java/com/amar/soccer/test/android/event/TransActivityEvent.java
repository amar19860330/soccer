package com.amar.soccer.test.android.event;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "TransActivityEvent")
@XmlAccessorType (XmlAccessType.FIELD)
public class TransActivityEvent extends AndroidEvent
{
	private static final long serialVersionUID = - 2567250874557673524L;

	private String formActivity;

	private String toActivity;

	public TransActivityEvent()
	{
		this.eventType = EventType.TRANS_ACTIVITY;
	}
	public TransActivityEvent( String formActivity , String toActivity )
	{
		this.eventType = EventType.TRANS_ACTIVITY;
		this.formActivity = formActivity;
		this.toActivity = toActivity;
	}

	public String getFormActivity()
	{
		return formActivity;
	}

	public void setFormActivity( String formActivity )
	{
		this.formActivity = formActivity;
	}

	public String getToActivity()
	{
		return toActivity;
	}

	public void setToActivity( String toActivity )
	{
		this.toActivity = toActivity;
	}

	@Override
	public String toString()
	{
		return "从：" + formActivity + " 跳转到：" + toActivity + "";
	}

}
