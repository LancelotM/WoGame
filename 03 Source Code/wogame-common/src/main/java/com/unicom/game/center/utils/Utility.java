package com.unicom.game.center.utils;

import java.util.ArrayList;
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

    /**
     * get rest of list by index
     *
     * @param list
     * @param indexFile
     * @return
     */
    public static List<String> getSubStringList (List<String> list, String indexFile) {
        list = sortStringList(list);
        int index = list.indexOf(indexFile);

        if (index == -1) {
            return list;
        }

        return list.subList(index+1, list.size());
    }

    public static String[] splitString (String str, String regex) {

        List<String> result = new ArrayList<String>();
        StringBuffer sb = new StringBuffer();

        for (char c :str.toCharArray()) {
            if (regex.equals(String.valueOf(c))) {
                if (sb.length() == 0) {
                    result.add("");
                } else {
                    result.add(sb.toString());
                    sb = new StringBuffer();
                }
            } else {
                sb.append(c);
            }
        }
        if (sb.length() == 0) {
            result.add("");
        } else {
            result.add(sb.toString());
        }

        return (String[])result.toArray(new String[result.size()]);
    }
	
}
