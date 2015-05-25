package com.amar.soccer.test.android;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class GetAndroidInfoImmediately extends AndroidCommand implements Runnable
{
	private String device;

	public static final String CMD_QUERY_CURRENT_ACTIVITY = "dumpsys input | grep FocusedApplication";

	public static final String RULER_OF_CURRENT_ACTIVITY_NAME = "^(.*ActivityRecord\\{[0-9|A-Z|a-z]*\\s)(.*)(\\s.*\\}{3}.*\\n*)$";

	CallBack<String> callBack;

	public GetAndroidInfoImmediately( String device , CallBack<String> callBack )
	{
		this.device = device;
		this.callBack = callBack;
	}

	public static void main( String args[] )
	{
		GetAndroidInfoImmediately getAndroidInfoImmediately = new GetAndroidInfoImmediately( "192.168.112.101:5555" , null );
		System.out.println( getAndroidInfoImmediately.getCurrentActivityName() );

	}

	/**
	 * "  FocusedApplication: name='AppWindowToken{53914d90 token=Token{538a43bc ActivityRecord{538b0314 com.amar.hello2/.A3}}}', dispatchingTimeout=5000.000ms\n\n";
	 * "  FocusedApplication: name='AppWindowToken{3983a2ae token=Token{177a8f29 ActivityRecord{13ddc4b0 u0 com.libianc.android.ued/.lib.libued.activity.HomeActivity_ t539}}}', dispatchingTimeout=5000.000ms\n\n"
	 * 
	 * @param device
	 * @return
	 */
	public String getCurrentActivityName()
	{
		String activityInfo = "未查询到";
		try
		{
			String preCommand = String.format( CMD_CONNECT , device );
			String result = cmd( preCommand + CMD_QUERY_CURRENT_ACTIVITY );

			Matcher matcher = Pattern.compile( RULER_OF_CURRENT_ACTIVITY_NAME ).matcher( result );
			if ( matcher.matches() && matcher.groupCount() == 3 )
			{
				activityInfo = matcher.group( 2 );
				if ( activityInfo.contains( " " ) )
				{
					String [] array = activityInfo.split( " " );
					for( String data : array )
					{
						if ( data.contains( "." ) )
						{
							activityInfo = data;
							break;
						}
					}
				}
			}
		}
		catch ( Exception e )
		{
			e.printStackTrace();
		}
		return activityInfo;
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
		String activityInfo = "未查询到";
		try
		{

			String preCommand = String.format( CMD_CONNECT , device );
			String result = cmd( preCommand + CMD_QUERY_CURRENT_ACTIVITY );
			String startflag = "ActivityRecord";
			String endFlag = "}}}";

			int start = result.indexOf( startflag );
			int end = result.indexOf( endFlag );

			String first = result.substring( start + startflag.length() , end - 5 );
			int secondFirst = first.lastIndexOf( " " );

			activityInfo = first.substring( secondFirst + 1 , first.length() );
		}
		catch ( Exception e )
		{
			e.printStackTrace();
		}
		return activityInfo;
	}

	@Override
	public void run()
	{
		String currentActivity = getCurrentActivityName();

		if ( callBack != null )
		{
			callBack.callback( currentActivity ,0);
		}
	}

}
