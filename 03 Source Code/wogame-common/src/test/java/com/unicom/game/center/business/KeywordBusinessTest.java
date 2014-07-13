package com.unicom.game.center.business;

import java.util.Date;
import java.util.List;
import java.util.Random;

import com.unicom.game.center.db.dao.KeywordDao;
import com.unicom.game.center.db.domain.KeywordDomain;
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
		List<KeywordInfo> keywords = keyword.fetchTopSearchKeyword();
		System.out.println((null != keywords) ? keywords.size() : null);
	}

    @Test
    public void testSave(){
        Random r = new Random();
        for(int i = 0;i<50;i++){
            KeywordDomain hotword = new KeywordDomain();
            hotword.setKeyword("植物大战僵尸v"+i);
            hotword.setCount(r.nextInt(500));
            hotword.setDateCreated(new Date());
            hotword.setDateModified(new Date());
            dao.save(hotword);

        }


    }

}
