package com.unicom.game.center.business;

import java.util.List;

import com.unicom.game.center.model.JsonParent;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.unicom.game.center.model.PageTrafficInfo;

/**
 * @author Alex Yin
 * 
 * @Date 2014-6-24
 */
@ContextConfiguration(locations = { "classpath:applicationContext_dao.xml" })
@RunWith(SpringJUnit4ClassRunner.class) 
public class PageTrafficBusinessTest {

	@Autowired
	private PageTrafficBusiness pageTraffic;
	
	@Test
	public void testFetchTrafficInfoByDate(){
        JsonParent trafficInfoList = pageTraffic.fetchTrafficInfoByDate(null);
		System.out.println((null != trafficInfoList) ? 1 : 0);
		trafficInfoList = pageTraffic.fetchTrafficInfoByDate(18082);
		System.out.println((null != trafficInfoList) ? 1 : 0);
	}

	@Test
	public void testFetchTrafficInfoByMonth(){
        JsonParent trafficInfoList = pageTraffic.fetchTrafficInfoByMonth(null);
		System.out.println((null != trafficInfoList) ? 1 : 0);
		trafficInfoList = pageTraffic.fetchTrafficInfoByMonth(18082);
		System.out.println((null != trafficInfoList) ? 1 : 0);
	}	
}
