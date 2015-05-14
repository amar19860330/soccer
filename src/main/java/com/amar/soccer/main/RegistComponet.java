package com.amar.soccer.main;

import java.util.HashMap;
import java.util.Map;

import javax.swing.JComponent;

import com.amar.soccer.dinner.BuyForm;
import com.amar.soccer.dinner.LaunchForm;
import com.amar.soccer.setting.SettingForm;
import com.amar.soccer.soccer.LineupForm;
import com.amar.soccer.test.TestForm;
import com.amar.soccer.user.ForgetPwForm;
import com.amar.soccer.user.LoginForm;
import com.amar.soccer.user.LogoutForm;
import com.amar.soccer.user.ModifyPwForm;
import com.amar.soccer.user.RegistForm;
import com.amar.soccer.user.UserForm;

public class RegistComponet
{
	private static Map<String,JComponent> registMap;
	
	public static void init()
	{
		registMap = new HashMap<String,JComponent>();
		
		registMap.put( Form_ForgetPwForm , new ForgetPwForm() ); 		
		registMap.put( Form_LoginForm , new LoginForm() );		
		registMap.put( Form_ModifyPwForm , new ModifyPwForm() );		
		registMap.put( Form_RegistForm , new RegistForm() );		
		registMap.put( Form_UserForm , new UserForm() );			
		registMap.put( Form_LogoutForm , new LogoutForm() );			
		registMap.put( Form_LineupForm , new LineupForm() );		
		registMap.put( Form_BuyForm , new BuyForm() );			
		registMap.put( Form_LaunchForm , new LaunchForm() );
		registMap.put( Form_SettingForm , new SettingForm() );
		registMap.put( Form_TestForm , new TestForm() );	
	}
	
	public static JComponent getComponet(String title)
	{
		return registMap.get( title );
	}
	
	public static final String Form_ForgetPwForm = "忘记密码";
	public static final String Form_LoginForm = "用户登录";
	public static final String Form_ModifyPwForm = "修改密码";
	public static final String Form_RegistForm = "注册";
	public static final String Form_UserForm = "用户信息";
	public static final String Form_LogoutForm = "登出";
	public static final String Form_LineupForm = "阵容";
	public static final String Form_BuyForm = "下单";
	public static final String Form_LaunchForm = "发起团购";
	public static final String Form_SettingForm = "参数设置";
	public static final String Form_TestForm = "手机测试";
}
