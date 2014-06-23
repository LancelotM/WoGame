package com.unicom.game.center.utils;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 * @author Alex Yin
 * 
 * @Date Jun 19, 2014
 */

public class DateUtils {
	
	
	/**
	 * converting date to user want format 
	 * @param parDate
	 * @param parFormat
	 * @return
	 */
	public static String formatDateToString(Date parDate, String parFormat)
	{
		String strDate = "";
		
		//check the parameter
		if(null == parDate)
			parDate = new Date();
		
		if(null == parFormat)
			parFormat = "MM/dd/yyyy";

		try
		{
			SimpleDateFormat simple = new SimpleDateFormat(parFormat);
			strDate = simple.format(parDate);
		}
		catch(IllegalArgumentException e)
		{
			parFormat = "MM/dd/yyyy";
			SimpleDateFormat simple = new SimpleDateFormat(parFormat);
			strDate = simple.format(parDate);
		}
		
		return strDate;
	}


    
    public static Date stringToDate(String parDate, String parFormat) 
    {
        Date dateTime = new Date();
		if(null == parFormat)
			parFormat = "MM/dd/yyyy";
		
        if (parDate != null && !parDate.isEmpty()) 
        {
            try {
                SimpleDateFormat format = new SimpleDateFormat(parFormat);
                dateTime = format.parse(parDate);
            } catch (Exception e) {
            	Logging.logError("Error occurs in string to date.", e);
            }
        }
        return dateTime;
    } 
    
    /**
     * get privious day's date
     * @param date
     * @param amount	--amount>0	next day	--amount<0 previousDay
     * @return
     */
    public static Date getDayByInterval(Date date, int amount){
    	Calendar calendar = Calendar.getInstance();
    	calendar.setTime(date);
    	calendar.add(Calendar.DAY_OF_MONTH, amount);
    	return calendar.getTime();
    }
      
    public static Date beginOfDate(Date date) {
        if (date != null) {
            Calendar calendar = Calendar.getInstance();
            calendar.setTime(date);
            calendar.set(Calendar.SECOND, 0);
            calendar.set(Calendar.MINUTE, 0);
            calendar.set(Calendar.HOUR, -12);

            return calendar.getTime();
		}
		return null;
	}
    
    public static Date endOfDate(Date date) {
        if (date != null) {
            Calendar calendar = Calendar.getInstance();
            calendar.setTime(date);
            calendar.set(Calendar.SECOND, 59);
            calendar.set(Calendar.MINUTE, 59);
            calendar.set(Calendar.HOUR, 11);

            return calendar.getTime();
		}
		return null;
	}
    
    public static int compareDate(String date1, String date2){
        DateFormat df = new SimpleDateFormat("yyyyMMddHHmmss");
        try {
            Date dt1 = df.parse(date1);
            Date dt2 = df.parse(date2);
            if (dt1.getTime() > dt2.getTime()) {
                return 1;
            } else if (dt1.getTime() < dt2.getTime()) {
                return -1;
            } else {
                return 0;
            }
        } catch (Exception ex) {
        	Logging.logError("Error occurs in compareDate.", ex);
        }
        return 0;    	
    }
}
