package com.unicom.game.center.business;

import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.unicom.game.center.model.GameInfo;

/**
 * @author Alex Yin
 * 
 * @Date Jun 22, 2014
 */
@ContextConfiguration(locations = { "classpath:applicationContext_dao.xml" })
@RunWith(SpringJUnit4ClassRunner.class) 
public class GameTrafficBusinessTest {
	@Autowired
	private GameTrafficBusiness game;
	
	@Test
	public void testFetchBannerInfo(){
		List<GameInfo>  gameList =game.fetchBannerInfo(18082, 1);
		System.out.println((null != gameList) ? gameList.size() : 0);
	}	
	
}
