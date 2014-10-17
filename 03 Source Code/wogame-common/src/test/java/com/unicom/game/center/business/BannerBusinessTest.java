package com.unicom.game.center.business;

import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.unicom.game.center.model.BannerInfo;
import com.unicom.game.center.model.BannerInfoList;

/**
 * @author Alex
 *
 */

@ContextConfiguration(locations = { "classpath:applicationContext_dao.xml" })
@RunWith(SpringJUnit4ClassRunner.class)
public class BannerBusinessTest {
	@Autowired
	private BannerBusiness banner;
	
	@Test
	public void testFetchBannerInfos(){
		List<BannerInfo> bannerList = banner.fetchBannerInfos(1);
		
		if(null != bannerList && !bannerList.isEmpty()){
			System.out.println(bannerList.size());
		}else{
			System.out.println(0);
		}
		
	}
	
	@Test
	public void testFetchAllBannerInfos(){
		BannerInfoList banners = banner.fetchAllBanner();
		if(null != banners){
			System.out.println("data");
		}else{
			System.out.println("null");
		}
	}
}
