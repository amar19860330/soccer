package com.amar.soccer.test.android.event;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement()
public class DeleteEvent extends AndroidEvent
{
	private static final long serialVersionUID = - 4798346815051587276L;

	public DeleteEvent()
	{
		this.eventType = EventType.INPUT;
	}

	@Override
	public String toString()
	{
		return "按了一下删除键";
	}

}
