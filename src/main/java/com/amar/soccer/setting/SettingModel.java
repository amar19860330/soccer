package com.amar.soccer.setting;

import java.util.Properties;

public class SettingModel
{
	private String serverIp;
	private int serverPort;
	private Properties properties;
	
	public String getServerIp()
	{
		return serverIp;
	}
	public void setServerIp( String serverIp )
	{
		this.serverIp = serverIp;
	}
	public int getServerPort()
	{
		return serverPort;
	}
	public void setServerPort( int serverPort )
	{
		this.serverPort = serverPort;
	}
	public SettingModel( String serverIp , int serverPort )
	{
		super();
		this.serverIp = serverIp;
		this.serverPort = serverPort;
	}
	public SettingModel()
	{
		super();
	}
	public Properties getProperties()
	{
		return properties;
	}
	public void setProperties( Properties properties )
	{
		this.properties = properties;
	}
	
}
