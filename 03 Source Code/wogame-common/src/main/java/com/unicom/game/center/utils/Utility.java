package com.unicom.game.center.utils;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;

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

    /**
     * sort String list
     *
     * @param list
     * @return
     */
    public static List<String> sortStringList(List<String> list)
   	{
        Collections.sort(list, new Comparator<String>() {
            public int compare(String obj1, String obj2) {
                return obj1.compareToIgnoreCase(obj2);
            }
        });

        return list;
   	}
	
}
