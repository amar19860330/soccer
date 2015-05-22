package com.amar.soccer.test.android.event;

public class InputEvent extends AndroidEvent
{
	String input;
	
	public InputEvent(String input)
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

	@Override
	public String toString()
	{
		return "InputEvent [input=" + input + "]";
	}
	
	
}
