package com.unicom.game.center.business;

import com.unicom.game.center.db.domain.PackageInfoDomain;
import com.unicom.game.center.db.domain.PackageInfoKey;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

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

    @Test
   	public void testSavePackageInfoList(){
        List<PackageInfoDomain> list = new ArrayList<PackageInfoDomain>();
        PackageInfoDomain entity = new PackageInfoDomain();
        PackageInfoKey key = new PackageInfoKey();

        key.setChannelId("1");
        key.setAppId("2");
        entity.setKey(key);
        entity.setCpId("3");
        entity.setAppName("4");
        entity.setDateCreated(new Date());
        entity.setDateModified(new Date());
        list.add(entity);

        packageInfo.savePackageInfoList(list, 20);
   	}
}
