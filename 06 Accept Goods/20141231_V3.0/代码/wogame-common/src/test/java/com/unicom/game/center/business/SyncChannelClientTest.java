package com.unicom.game.center.business;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

/**
 * @author Alex Yin
 * 
 * @Date 2014-7-25
 */
@ContextConfiguration(locations = { "classpath:applicationContext_dao.xml" })
@RunWith(SpringJUnit4ClassRunner.class)  
public class SyncChannelClientTest {
	
	@Autowired
	private SyncChannelClient sync;
	
	@Test
	public void testSyncFailureChannels(){
		sync.syncFailureChannels();
	}

}
