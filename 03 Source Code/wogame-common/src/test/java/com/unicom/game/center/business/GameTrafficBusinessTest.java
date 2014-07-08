package com.unicom.game.center.business;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.List;
import java.util.Random;

import com.unicom.game.center.db.dao.GameTrafficDao;
import com.unicom.game.center.db.domain.GameTrafficDomain;
import com.unicom.game.center.db.domain.ProductDomain;
import com.unicom.game.center.model.GameDisplayModel;
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

    @Autowired
    private GameTrafficDao gameTrafficDao;
	
	@Test
	public void testFetchBannerInfo(){
        List<GameInfo> gameList =game.fetchGameInfoByDate(18082, true);
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

    @Test
    public void testSave(){
        GameTrafficDomain gameTraffic = null;
        ProductDomain product = null;
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        Random r = new Random();
        for(int i = 31;i<=36;i++){
            product = new ProductDomain();
            if(i<10){
                product.setProductId("00"+i);
            }else {
                product.setProductId("0"+i);
            }
            gameTraffic = new GameTrafficDomain();
            gameTraffic.setChannelId(1);
            gameTraffic.setProduct(product);
            try {
                gameTraffic.setDateCreated(sdf.parse("2014-01-07"));
            } catch (ParseException e) {
                e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
            }
            gameTraffic.setFlag(true);
            gameTraffic.setDownloadCount(r.nextInt(500));
            gameTraffic.setClickThrough(r.nextInt(500));
            gameTrafficDao.save(gameTraffic);
        }

    }

}
