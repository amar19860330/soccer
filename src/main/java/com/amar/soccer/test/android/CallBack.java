package com.amar.soccer.test.android;

public interface CallBack<T>
{
	public static int STATUS_WORK_COMPLETE = 0;
	public static int STATUS_WORK_ERROR = 1;
	
	void callback( T t ,int status);
}
