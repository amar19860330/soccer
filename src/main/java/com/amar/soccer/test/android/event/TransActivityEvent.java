package com.amar.soccer.test.android.event;

public class TransActivityEvent extends AndroidEvent
{
	String formActivity;
	String toActivity;
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
		return "TransActivityEvent [formActivity=" + formActivity + ", toActivity=" + toActivity + "]";
	}
	
	
}
