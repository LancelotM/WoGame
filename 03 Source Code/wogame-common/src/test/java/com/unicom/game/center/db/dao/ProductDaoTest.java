package com.unicom.game.center.db.dao;

import java.util.Date;

import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.unicom.game.center.db.domain.ProductDomain;
import com.unicom.game.center.utils.DateUtils;

/**
 * @author Alex Yin
 * 
 * @Date Jun 22, 2014
 */

@ContextConfiguration(locations = { "classpath:applicationContext_dao.xml" })
@RunWith(SpringJUnit4ClassRunner.class) 
public class ProductDaoTest {
	@Autowired
	private ProductDao dao;
	
	@Test
	@Ignore("org.hibernate.HibernateException: No Session found for current thread")
	public void saveProductTest(){
		Date date = DateUtils.beginOfDate(new Date());
		String productName = "game";
		String productIcon = "icon";
		
		ProductDomain product = null;
		
		for(int i = 1; i < 2; i++){
			product = new ProductDomain();
			product.setProductId(String.valueOf(i));
			product.setProductName(productName + String.valueOf(i));
			product.setProductIcon(productIcon + String.valueOf(i));
			
			date = DateUtils.getDayByInterval(date, -1);
			product.setDateCreated(date);
			
			dao.save(product);
		}
	}
	
	@Test
	public void fetchGameInfoByDateTest(){
		ProductDomain product = dao.getByProductId("1");
		System.out.println((null != product) ? product.getProductName() : 0);
	}
	
}
