package com.unicom.game.center.business;

import java.util.List;

import com.unicom.game.center.model.JsonParent;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.unicom.game.center.model.LoginInfo;

/**
 * @author Alex Yin
 * 
 * @Date 2014-6-24
 */
@ContextConfiguration(locations = { "classpath:applicationContext_dao.xml" })
@RunWith(SpringJUnit4ClassRunner.class) 
public class LoginInfoBusinessTest {
	
	@Autowired
	private LoginInfoBusiness loginInfo;
	
	@Test
	public void testFetchNewUserCount(){
		long count = loginInfo.fetchNewUserCount(null);
		System.out.println(count);
		count = loginInfo.fetchNewUserCount(18085);
		System.out.println(count);
	}
	
	@Test
	public void testFetchTotalUserCount(){
		long count = loginInfo.fetchTotalUserCount(null);
		System.out.println(count);
		count = loginInfo.fetchTotalUserCount(18082);
		System.out.println(count);
	}
	
	@Test
	public void testFetchLoginInfoByDate(){
        JsonParent loginInfoList = loginInfo.fetchLoginInfoByDate(null);
		System.out.println((null != loginInfoList) ? 1 : 0);
		loginInfoList = loginInfo.fetchLoginInfoByDate(18082);
		System.out.println((null != loginInfoList) ? 1 : 0);
	}

	@Test
	public void testFetchLoginInfoByMonth(){
        JsonParent loginInfoList = loginInfo.fetchLoginInfoByMonth(null);
		System.out.println((null != loginInfoList) ? 1 : 0);
		loginInfoList = loginInfo.fetchLoginInfoByMonth(18082);
		System.out.println((null != loginInfoList) ? 1 : 0);
	}	
}
