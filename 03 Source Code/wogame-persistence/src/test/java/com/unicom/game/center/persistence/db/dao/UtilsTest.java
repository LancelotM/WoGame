package com.unicom.game.center.persistence.db.dao;

import java.util.Date;

import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.unicom.game.center.utils.AESEncryptionHelper;
import com.unicom.game.center.utils.DateUtils;

/**
 * @author Alex Yin
 * 
 * @Date Jun 19, 2014
 */
@ContextConfiguration(locations = { "classpath:applicationContext_dao.xml" })
@RunWith(SpringJUnit4ClassRunner.class)
public class UtilsTest {

	@Value("#{properties['secret.key']}")
	private String secretKey;
	
	@Test
	@Ignore
	public void dateUtilsTest(){
		Date today = new Date();
		Date beginDate = DateUtils.beginOfDate(today);
		System.out.println(beginDate);
		
		Date endDate = DateUtils.endOfDate(today);
		System.out.println(endDate);

		Date previousDate = DateUtils.getPreviousDay(today);
		System.out.println(previousDate);
		
		Date nextDate = DateUtils.getNextDay(today);
		System.out.println(nextDate);
	}
	
	@Test
	public void encryptionUtilsTest() throws Throwable{
		String channelId = "18150";
		String encyption = AESEncryptionHelper.encrypt(channelId, secretKey);
		System.out.println(encyption);
		
		String decryption = AESEncryptionHelper.decrypt(encyption, secretKey);
		System.out.println(decryption);
	}
}