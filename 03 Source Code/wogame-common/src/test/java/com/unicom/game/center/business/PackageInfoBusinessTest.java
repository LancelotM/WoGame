package com.unicom.game.center.business;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

/**
 * @author Alex Yin
 * 
 * @Date 2014-6-23
 */
@ContextConfiguration(locations = { "classpath:applicationContext_dao.xml" })
@RunWith(SpringJUnit4ClassRunner.class) 
public class PackageInfoBusinessTest {
	@Autowired
	private PackageInfoBusiness packageInfo;
	
	@Test
	public void testCheckPackageExist(){
		String channelCode = packageInfo.checkPackageExist("18144", "1","20140630193859");
		System.out.println(channelCode);
	}
}
