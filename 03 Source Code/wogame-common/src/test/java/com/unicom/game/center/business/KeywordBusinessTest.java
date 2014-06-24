package com.unicom.game.center.business;

import java.util.List;

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
	
	@Test
	public void testFetchTopSearchKeyword(){
		List<KeywordInfo> keywords = keyword.fetchTopSearchKeyword();
		System.out.println((null != keywords) ? keywords.size() : null);
	}

}
