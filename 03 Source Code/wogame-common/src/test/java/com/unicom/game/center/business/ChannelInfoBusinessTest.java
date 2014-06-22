package com.unicom.game.center.business;

import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.unicom.game.center.db.domain.ChannelInfoDomain;

/**
 * @author Alex Yin
 * 
 * @Date Jun 21, 2014
 */

@ContextConfiguration(locations = { "classpath:applicationContext_dao.xml" })
@RunWith(SpringJUnit4ClassRunner.class) 
public class ChannelInfoBusinessTest {

	@Autowired
	private ChannelInfoBusiness channel;
	
	@Test
	public void testFetchAllChannelInfos(){
		List<ChannelInfoDomain> channelInfos = channel.fetchAllChannelInfos();
		System.out.println((null != channelInfos) ? channelInfos.size() : 0);
	}
	
	@Test
	public void testCheckChannelIsActive(){
		boolean flag = channel.checkChannelIsActive(18129);
		System.out.println(flag);
	}
	
	@Test
	public void testStartChannel(){
		boolean flag = channel.startChannel(18129);
		System.out.println(flag);
	}
	
	@Test
	public void testFetchActiveChannelInfos(){
		List<ChannelInfoDomain> channelInfos = channel.fetchActiveChannelInfos();
		System.out.println((null != channelInfos) ? channelInfos.size() : 0);
	}
}
