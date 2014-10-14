package com.unicom.game.center.utils;

import org.junit.Test;

/**
 * Created with IntelliJ IDEA.
 * User: lenovo
 * Date: 14-7-7
 * Time: 下午1:54
 * To change this template use File | Settings | File Templates.
 */
public class DateUtilTest {


    private DateUtils dateUtils = new DateUtils();

    @Test
    public void testInterval() throws Throwable {
        int s = dateUtils.intervalDays("2014-03-09","2014-04-09",0);
        System.out.print("相差的天数："+s);
    }
}
