package com.unicom.game.center.business;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

/**
 * @author Alex Yin
 * 
 * @Date 2014-6-20
 */

@ContextConfiguration(locations = { "classpath:applicationContext_dao.xml" })
@RunWith(SpringJUnit4ClassRunner.class)  
public class AccountBusinessTest {

	@Autowired
	private AccountBusiness account;
	
	@Test
	public void testSignUp(){
		int flag = account.signup("test2", "123");
		System.out.println(flag);
	}
	
	@Test
	public void testLogin(){
		int flag = account.login("admin", "hello123");
		System.out.println(flag);
	}
}
