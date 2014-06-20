package com.unicom.game.center.utils;

/**
 * @author Alex Yin
 * 
 * @Date Jun 11, 2014
 */

public class Utility {
	
	public static boolean isEmpty(String str)
	{
		if(null != str && !(str.trim()).isEmpty())
			return false;
		return true;
	}
	
}
