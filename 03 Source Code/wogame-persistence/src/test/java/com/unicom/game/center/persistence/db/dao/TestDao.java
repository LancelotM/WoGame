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
		//account.setAccountId(3);
		account.setAccountName("test3");
		account.setPassword("123456");
		account.setDateCreated(new Date(System.currentTimeMillis()));
		
		AbstractApplicationContext ac = 
			new ClassPathXmlApplicationContext("classpath:applicationContext_dao.xml");
		AccountDao dao = (AccountDao)ac.getBean("accountDao");
		//dao.save(account);
//		java.util.List<AccountDomain> list = dao.getAll();
//		for(AccountDomain obj : list){
//			System.out.println(obj.getAccountName());
//		}
		dao.delete(1);
//		AccountDomain domain = dao.getById(1);
//		System.out.println(domain.getAccountName());
	}

}
