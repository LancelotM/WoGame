package com.unicom.game.center.business;

import java.util.Date;
import java.util.List;
import java.util.Random;

import com.unicom.game.center.db.dao.KeywordDao;
import com.unicom.game.center.db.domain.KeywordDomain;
import com.unicom.game.center.utils.DateUtils;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.unicom.game.center.model.KeywordInfo;

/**
 * @author Alex Yin
 * 
 * @Date 2014-6-24
 */
@ContextConfiguration(locations = { "classpath:applicationContext_dao.xml" })
@RunWith(SpringJUnit4ClassRunner.class) 
public class KeywordBusinessTest {
	@Autowired
	private KeywordBusiness keyword;

    @Autowired
    private KeywordDao dao;
	
	@Test
	public void testFetchTopSearchKeyword(){
		List<KeywordInfo> keywords = keyword.fetchTopSearchKeyword(34);
		System.out.println((null != keywords) ? keywords.size() : null);
	}

    @Test
    public void testSave(){
        Random r = new Random();
        for(int i = 0;i<50;i++){
            KeywordDomain hotword = new KeywordDomain();
            if(i%2 == 0){
                hotword.setKeyword("天天跑酷植物大战僵尸v"+i);
            }else {
                hotword.setKeyword("天天跑酷v"+i);
            }

            hotword.setCount(r.nextInt(300));
            hotword.setDateCreated(DateUtils.getDayByInterval(new Date(), -i));
            hotword.setChannelId(39);
            hotword.setDateModified(new Date());
            dao.save(hotword);

        }
    }

    @Test
    public void get(){
        int todayCount = keyword.getDayCount(1,DateUtils.formatDateToString(new Date(),"yyyy-MM-dd"));
        int yesterdayCount = keyword.getDayCount(1,DateUtils.formatDateToString(DateUtils.getDayByInterval(new Date(), -1),"yyyy-MM-dd"));
        int totalCount = keyword.getThirtyDayCount(1);
        System.out.println(todayCount+"   "+yesterdayCount+"  "+totalCount);
    }

}
