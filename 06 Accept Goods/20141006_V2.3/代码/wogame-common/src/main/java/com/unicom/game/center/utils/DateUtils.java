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
     * @param amount	--amount>0	next day	--amount<0 previous day
     * @return
     */
    public static Date getDayByInterval(Date date, int amount){
    	Calendar calendar = Calendar.getInstance();
    	calendar.setTime(date);
    	calendar.add(Calendar.DAY_OF_MONTH, amount);
    	return calendar.getTime();
    }
    
    /**
     * get first day of previous month
     * @param date
     * @param amount	--amount>0	next month	--amount<0 previous month
     * @return
     */
    public static String getMonthFirstByInterval(Date date, int amount){
        String str = "";       
        SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd");           
       
        Calendar calendar = Calendar.getInstance();  
        calendar.setTime(date);
        calendar.set(Calendar.DATE,1);
        calendar.add(Calendar.MONTH,amount);

        str=sdf.format(calendar.getTime());       
        return str;    	
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


    public static int compareLogDate(String date1, String date2){
        DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
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

    /**
     *
     * @param startDay
     * @param endDay
     * @param stype
     *  0为多少天,1为多少个月,2为多少年
     * @return
     */
    public static int intervalDays(String startDay,String endDay,int stype){
        int n = 0;
        String formatStyle = stype==1?"yyyy-MM":"yyyy-MM-dd";

        endDay = endDay==null?getCurrentDate("yyyy-MM-dd"):endDay;

        DateFormat df = new SimpleDateFormat(formatStyle);
        Calendar c1 = Calendar.getInstance();
        Calendar c2 = Calendar.getInstance();
        try {
            c1.setTime(df.parse(startDay));
            c2.setTime(df.parse(endDay));
        } catch (Exception e3) {
            System.out.println("wrong occured");
        }
        //List list = new ArrayList();
        while (!c1.after(c2)) {                   // 循环对比，直到相等，n 就是所要的结果
            //list.add(df.format(c1.getTime()));    // 这里可以把间隔的日期存到数组中 打印出来
            n++;
            if(stype==1){
                c1.add(Calendar.MONTH, 1);          // 比较月份，月份+1
            }
            else{
                c1.add(Calendar.DATE, 1);           // 比较天数，日期+1
            }
        }
        n = n-1;
        if(stype==2){
            n = (int)n/365;
        }
        return n;
    }

    public static String getCurrentDate(String format){
        Calendar day=Calendar.getInstance();
        day.add(Calendar.DATE,0);
        SimpleDateFormat sdf=new SimpleDateFormat(format);//"yyyy-MM-dd"
        String date = sdf.format(day.getTime());
        return date;
    }
}
