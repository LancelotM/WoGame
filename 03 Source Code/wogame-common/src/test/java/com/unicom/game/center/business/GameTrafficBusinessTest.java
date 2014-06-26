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
		List<GameInfo>  gameList =game.fetchGameInfoByDate(18082, true);
		System.out.println((null != gameList) ? gameList.size() : 0);
		
		gameList =game.fetchGameInfoByMonth(18082, true);
		System.out.println((null != gameList) ? gameList.size() : 0);		
	}
	
	@Test
	public void testFetchHotListInfo(){
		List<GameInfo>  gameList =game.fetchGameInfoByDate(18082, false);
		System.out.println((null != gameList) ? gameList.size() : 0);
		
		gameList =game.fetchGameInfoByMonth(18082, false);
		System.out.println((null != gameList) ? gameList.size() : 0);			
	}	
	
}
