package com.unicom.game.center.utils;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

/**
 * @author Alex
 *
 */
@ContextConfiguration(locations = { "classpath:applicationContext_dao.xml" })
@RunWith(SpringJUnit4ClassRunner.class)
public class SFTPHelperTest {
	
	@Autowired
	private SFTPHelper sftpHelper;	
	
    @Test
   	public void uploadFileTest() throws Throwable{
   		String src = "C:\\Users\\Alex\\Downloads\\doudizhu.jpg";
   		long timestamp = System.currentTimeMillis();
   		System.out.println(timestamp);
   		String desc = "/unicom/images/" + String.valueOf(timestamp);
        boolean result = sftpHelper.uploadFile(src, desc);

        System.out.println(result);
   	}
}
