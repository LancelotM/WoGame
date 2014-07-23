package com.unicom.game.center.business;

import java.util.List;
import java.util.Random;

import com.unicom.game.center.db.domain.AdTrafficDomain;
import com.unicom.game.center.utils.DateUtils;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.unicom.game.center.db.dao.AdTrafficDao;
import com.unicom.game.center.model.AdInfo;

/**
 * @author Alex Yin
 * 
 * @Date Jun 22, 2014
 */
@ContextConfiguration(locations = { "classpath:applicationContext_dao.xml" })
@RunWith(SpringJUnit4ClassRunner.class) 
public class GameTrafficBusinessTest {
	@Autowired
	private AdTrafficBusiness game;

    @Autowired
    private AdTrafficDao gameTrafficDao;
	
	@Test
	public void testFetchBannerInfo(){
        List<AdInfo> gameList =game.fetchGameInfoByDate(18082, true);
		System.out.println((null != gameList) ? gameList.size() : 0);
		
		gameList =game.fetchGameInfoByMonth(18082, true);
		System.out.println((null != gameList) ? gameList.size() : 0);		
	}
	
	@Test
	public void testFetchHotListInfo(){
        List<AdInfo>  gameList =game.fetchGameInfoByDate(18082, false);
		System.out.println((null != gameList) ? gameList.size() : 0);
		
		gameList =game.fetchGameInfoByMonth(18082, false);
		System.out.println((null != gameList) ? gameList.size() : 0);			
	}

    @Test
    public void save(){
        AdTrafficDomain adTrafficDomain = null;
        Random r = new Random();
        for(int i = 0;i<4;i++){
            adTrafficDomain = new AdTrafficDomain();
            adTrafficDomain.setDateCreated(DateUtils.stringToDate("2014-07-21","yyyy-MM-dd"));
            adTrafficDomain.setChannelId(17);
            adTrafficDomain.setAdId(0);
            adTrafficDomain.setAdType(0);
            adTrafficDomain.setSort(i+1);
            adTrafficDomain.setClickThrough(r.nextInt(700));
            gameTrafficDao.save(adTrafficDomain);
        }
    }

}
