package com.unicom.game.center.persistence.db.dao;

import java.sql.Date;

import org.junit.Test;
import org.springframework.context.support.AbstractApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.unicom.game.center.persistence.db.domain.AccountDomain;


public class TestDao {
	
	@Test
	public void test(){
		AccountDomain account = new AccountDomain();
		account.setAccountName("tttt");
		account.setPassword("111111");
		account.setDateCreated(new Date(System.currentTimeMillis()));
		
		AbstractApplicationContext ac = 
			new ClassPathXmlApplicationContext("classpath:applicationContext_dao.xml");
		AccountDao dao = (AccountDao)ac.getBean("accountDao");
		dao.save(account);
	}

}
