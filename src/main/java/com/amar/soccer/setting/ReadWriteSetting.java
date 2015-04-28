package com.amar.soccer.setting;

import java.util.Properties;

import com.amar.soccer.constant.AppConstant;
import com.amar.soccer.util.PropertiesUtil;

public class ReadWriteSetting
{
	public SettingModel getSetting()
	{
		SettingModel settingModel = new SettingModel();

		Properties properties = PropertiesUtil.readProperties( AppConstant.Config_File );

		settingModel.setServerIp( properties.getProperty( AppConstant.Setting_Server_Ip ) );
		settingModel.setServerPort( Integer.parseInt( properties.getProperty( AppConstant.Setting_Server_Port ) ) );
		settingModel.setProperties( properties );
		return settingModel;
	}

	public boolean setting( SettingModel settingModel )
	{
		boolean result = false;
		if ( settingModel.getProperties() != null )
		{
			settingModel.getProperties().setProperty( AppConstant.Setting_Server_Ip , settingModel.getServerIp() );
			settingModel.getProperties().setProperty( AppConstant.Setting_Server_Port , settingModel.getServerPort() + "" );

			PropertiesUtil.writeProperties( settingModel.getProperties() , AppConstant.Config_File );
			result = true;
		}
		return result;
	}
}
