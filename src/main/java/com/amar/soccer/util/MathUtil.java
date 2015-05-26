package com.amar.soccer.util;

public class MathUtil
{
	/**
	 * x,y 组成的坐标是否在后续指定的区域中
	 * 
	 * @param x
	 * @param y
	 * @param left
	 * @param top
	 * @param width
	 * @param height
	 * @return
	 */
	public static boolean inThisArea( int x , int y , int left , int top , int width , int height )
	{
		boolean horizontalIn = x > left && x < ( left + width );
		boolean verticalIn = y > top && y < ( top + height );
		return horizontalIn && verticalIn;
	}

}
