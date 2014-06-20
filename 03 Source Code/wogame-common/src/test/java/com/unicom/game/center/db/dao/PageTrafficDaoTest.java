package com.unicom.game.center.db.dao;

import java.util.Date;

import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.unicom.game.center.db.dao.PageTrafficDao;
import com.unicom.game.center.db.domain.PageTrafficDomain;

/**
 * @author Alex.Yin
 * @Date Jun 09, 2014
 */

@ContextConfiguration(locations = { "classpath:applicationContext_dao.xml" })
@RunWith(SpringJUnit4ClassRunner.class)  
public class PageTrafficDaoTest {
	
	@Autowired
	private PageTrafficDao dao;
	
	@Test
	public void testSave(){
		PageTrafficDomain domain = new PageTrafficDomain();
		domain.setChannelId(2);
		domain.setClickThrough(1);
		domain.setPageId(1);
		
		dao.save(domain);		
	}
	
	@Test
	@Ignore
	public void testGetByPageAndChannel(){		
		PageTrafficDomain domain = dao.getByPageAndChannel(1, 2, new Date());
		System.out.print(domain.getClickThrough());
	}

}
