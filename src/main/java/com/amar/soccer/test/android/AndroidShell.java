package com.amar.soccer.test.android;

import com.amar.soccer.util.Shell;

public class AndroidShell extends Shell
{
	public static final String CMD_CLICK = "input tap ";

	public static final String CMD_DELETE = "input keyevent KEYCODE_DEL";

	public static final String CMD_INPUT = "input text ";

	public static final String CMD_QUERY_CURRENT_ACTIVITY = "dumpsys input | grep FocusedApplication";

	public static final String CMD_CONNECT = "adb -s %s shell ";

	public static final String CMD_EVENT = "getevent";

	public static final String CMD_ALL_DEVICES = "adb devices";

	public void deleteText( String device )
	{
		String preCommand = String.format( CMD_CONNECT , device );
		for( int i = 0 ; i < 10 ; i ++ )
		{
			cmd( preCommand + CMD_DELETE );
		}
	}

	/**
	 * 原始数据 ____FocusedApplication:__name='AppWindowToken{290b2209__token=Token{1c9b5710__ActivityRecord{293cafd3__u0__com.amar.hello2/.TabHostDemoActivity__t361}}}',__dispatchingTimeout=5000.000ms
	 * ____FocusedApplication:__name='AppWindowToken{45627e3__token=Token{261dc412__ActivityRecord{634879d__u0__com.libianc.android.sportsbook
	 * activity.TodayOddsActivity___t309}}}',__dispatchingTimeout=5000.000ms
	 * 第一次过滤后 {293cafd3 u0 com.amar.hello2/.TabHostDemoActivity
	 * 第二次过滤得 com.amar.hello2/.TabHostDemoActivity
	 */
	public String getCurrentActivity( String device )
	{
		String preCommand = String.format( CMD_CONNECT , device );
		String result = cmd( preCommand + CMD_QUERY_CURRENT_ACTIVITY );
		String startflag = "ActivityRecord";
		String endFlag = "}}}";

		int start = result.indexOf( startflag );
		int end = result.indexOf( endFlag );

		String first = result.substring( start + startflag.length() , end - 5 );
		int secondFirst = first.lastIndexOf( " " );

		String activityInfo = first.substring( secondFirst + 1 , first.length() );
		return activityInfo;
	}

}
