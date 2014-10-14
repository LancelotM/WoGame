package com.unicom.game.center.business;

import java.util.Date;
import java.util.List;
import java.util.Random;

import com.unicom.game.center.db.dao.UserCountDao;
import com.unicom.game.center.db.domain.UserCountDomain;
import com.unicom.game.center.model.JsonParent;
import com.unicom.game.center.utils.DateUtils;
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

    @Autowired
    private UserCountDao userCountDao;
	
	@Test
	public void testFetchNewUserCount(){
		long count = loginInfo.fetchNewUserCount(null);
		System.out.println(count);
		count = loginInfo.fetchNewUserCount(7);
		System.out.println(count);
	}
	
	@Test
	public void testFetchTotalUserCount(){
		long count = loginInfo.fetchTotalUserCount(null);
		System.out.println(count);
		count = loginInfo.fetchTotalUserCount(7);
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

    @Test
    public void save(){
        Random r = new Random();
        for(int i = 1;i<=30;i++){
            UserCountDomain domain = new UserCountDomain();
            domain.setChannelId(11);
            domain.setNewUserCount(r.nextInt(600));
            domain.setOldUserCount(r.nextInt(600));
            domain.setDateCreated(DateUtils.getDayByInterval(new Date(), -i));
            userCountDao.save(domain);
        }
    }
}
