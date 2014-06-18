package com.unicom.game.center.persistence.db.dao;

import java.sql.Date;

import org.junit.Test;
import org.springframework.context.support.AbstractApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.unicom.game.center.persistence.db.domain.AccountDomain;
import com.unicom.game.center.persistence.db.domain.ChannelInfoDomain;
import com.unicom.game.center.persistence.db.domain.DownloadInfoDomain;
import com.unicom.game.center.persistence.db.domain.GameTrafficDomain;
import com.unicom.game.center.persistence.db.domain.KeyWordDomain;
import com.unicom.game.center.persistence.db.domain.PackageInfoDomain;
import com.unicom.game.center.persistence.db.domain.PackageInfoKey;
import com.unicom.game.center.persistence.db.domain.PageTrafficDomain;
import com.unicom.game.center.persistence.db.domain.ProductDomain;
import com.unicom.game.center.persistence.db.domain.UserCountDomain;


public class TestDao {
/*	
	@Test
	public void test1(){
		AccountDomain account = new AccountDomain();
		account.setAccountId(2);
		account.setAccountName("test4");
		account.setPassword("123456");
		//account.setDateCreated(new Date(System.currentTimeMillis()));
		
		AbstractApplicationContext ac = 
			new ClassPathXmlApplicationContext("classpath:applicationContext_dao.xml");
		AccountDao dao = (AccountDao)ac.getBean("accountDao");
		//dao.save(account);
//		java.util.List<AccountDomain> list = dao.getAll();
//		for(AccountDomain obj : list){
//			System.out.println(obj.getAccountName());
//		}
		dao.update(account);
//		AccountDomain domain = dao.getById(6);
//		System.out.println(domain.getAccountName());
	}
	
	@Test
	public void test2(){
		ChannelInfoDomain domain = new ChannelInfoDomain();
		domain.setChannelId(3);
		domain.setChannelName("fea");
		domain.setCpId("fjdkla");
		domain.setDateCreated(new Date(System.currentTimeMillis()));
		domain.setStatusId(70);
		
		AbstractApplicationContext ac = 
			new ClassPathXmlApplicationContext("classpath:applicationContext_dao.xml");
		ChannelInfoDao dao = (ChannelInfoDao)ac.getBean("channelInfoDao");
		dao.save(domain);
//		for(ChannelInfoDomain d : dao.getAll()){
//			System.out.println(d.getChannelName());
//		}
//		
//		System.out.println(dao.getById(2).getChannelName());
	}
	@Test
	public void test3(){
		DownloadInfoDomain domain = new DownloadInfoDomain();
		domain.setDateCreated(new Date(System.currentTimeMillis()));
		domain.setDownloadCount(1);
		domain.setProductId(1);
		domain.setChannelId(2);
		
		AbstractApplicationContext ac = 
			new ClassPathXmlApplicationContext("classpath:applicationContext_dao.xml");
		DownloadInfoDao dao = (DownloadInfoDao)ac.getBean("downloadInfoDao");
		dao.save(domain);
//		for(ChannelInfoDomain d : dao.getAll()){
//			System.out.println(d.getChannelName());
//		}
//		
//		System.out.println(dao.getById(2).getChannelName());
	}
	@Test
	public void test4(){
		ProductDomain domain = new ProductDomain();
		domain.setProductId(1);
		domain.setProductIcon("fafa");
		domain.setProductName("ֲ���ս��ʬ");
		
		AbstractApplicationContext ac = 
			new ClassPathXmlApplicationContext("classpath:applicationContext_dao.xml");
		ProductDao dao = (ProductDao)ac.getBean("productDao");
		dao.save(domain);
//		for(ChannelInfoDomain d : dao.getAll()){
//			System.out.println(d.getChannelName());
//		}
//		
//		System.out.println(dao.getById(2).getChannelName());
	}
	
	@Test
	public void test5(){
		GameTrafficDomain domain = new GameTrafficDomain();
		domain.setClickThrough(1);
		domain.setFlag(true);
		domain.setChannelId(2);
		domain.setProductId(1);
		
		AbstractApplicationContext ac = 
			new ClassPathXmlApplicationContext("classpath:applicationContext_dao.xml");
		GameTrafficDao dao = (GameTrafficDao)ac.getBean("hotGameTrafficDao");
		dao.save(domain);
//		for(ChannelInfoDomain d : dao.getAll()){
//			System.out.println(d.getChannelName());
//		}
//		
//		System.out.println(dao.getById(2).getChannelName());
	}
	
	@Test
	public void test6(){
		KeyWordDomain d = new KeyWordDomain();
		d.setCount(1);
		d.setKeyword("fjaklfje");
		
		AbstractApplicationContext ac = 
			new ClassPathXmlApplicationContext("classpath:applicationContext_dao.xml");
		KeyWordDao dao = (KeyWordDao)ac.getBean("keyWordDao");
		dao.save(d);
//		for(ChannelInfoDomain d : dao.getAll()){
//			System.out.println(d.getChannelName());
//		}
//		
//		System.out.println(dao.getById(2).getChannelName());
	}
	
	@Test
	public void test7(){
		PackageInfoKey key = new PackageInfoKey();
		key.setChannelId(2);
		key.setProductId(1);
		PackageInfoDomain d = new PackageInfoDomain();
		d.setKey(key);
		
		AbstractApplicationContext ac = 
			new ClassPathXmlApplicationContext("classpath:applicationContext_dao.xml");
		PackageInfoDao dao = (PackageInfoDao)ac.getBean("packageInfoDao");
		dao.save(d);
//		for(ChannelInfoDomain d : dao.getAll()){
//			System.out.println(d.getChannelName());
//		}
//		
//		System.out.println(dao.getById(2).getChannelName());
	}
	
	@Test
	public void test8(){
		PageTrafficDomain d = new PageTrafficDomain();
		d.setChannelId(2);
		d.setClickThrough(1);
		d.setPageId(1);
		
		AbstractApplicationContext ac = 
			new ClassPathXmlApplicationContext("classpath:applicationContext_dao.xml");
		PageTrafficDao dao = (PageTrafficDao)ac.getBean("pageTrafficDao");
		dao.save(d);
//		for(ChannelInfoDomain d : dao.getAll()){
//			System.out.println(d.getChannelName());
//		}
//		
//		System.out.println(dao.getById(2).getChannelName());
	}
	
	@Test
	public void test9(){
		ProductDomain d = new ProductDomain();
		d.setProductIcon("faeae");
		d.setProductName("fawef");
		
		AbstractApplicationContext ac = 
			new ClassPathXmlApplicationContext("classpath:applicationContext_dao.xml");
		ProductDao dao = (ProductDao)ac.getBean("productDao");
		dao.save(d);
//		for(ChannelInfoDomain d : dao.getAll()){
//			System.out.println(d.getChannelName());
//		}
//		
//		System.out.println(dao.getById(2).getChannelName());
	}
	
	@Test
	public void test10(){
		UserCountDomain d = new UserCountDomain();
		d.setNewUserCount(1);
		//d.setOldUserCount();
		d.setChannelId(2);
		d.setDateCreated(new Date(System.currentTimeMillis()));
		AbstractApplicationContext ac = 
			new ClassPathXmlApplicationContext("classpath:applicationContext_dao.xml");
		UserCountDao dao = (UserCountDao)ac.getBean("userCountDao");
		dao.save(d);
		//UserCountDomain domain = dao.getByDate(new Date(System.currentTimeMillis()));
		//System.out.println(domain.getNewUserCount());
//		for(ChannelInfoDomain d : dao.getAll()){
//			System.out.println(d.getChannelName());
//		}
//		
//		System.out.println(dao.getById(2).getChannelName());
	}
	
	@Test
	public void test11(){
		GameTrafficDomain domain = new GameTrafficDomain();
		domain.setClickThrough(1);
		domain.setFlag(true);
		domain.setChannelId(2);
		domain.setProductId(1);
		
		AbstractApplicationContext ac = 
			new ClassPathXmlApplicationContext("classpath:applicationContext_dao.xml");
		GameTrafficDao dao = (GameTrafficDao)ac.getBean("hotGameTrafficDao");
		dao.save(domain);
//		for(ChannelInfoDomain d : dao.getAll()){
//			System.out.println(d.getChannelName());
//		}
//		
//		System.out.println(dao.getById(2).getChannelName());
	}
*/
}
