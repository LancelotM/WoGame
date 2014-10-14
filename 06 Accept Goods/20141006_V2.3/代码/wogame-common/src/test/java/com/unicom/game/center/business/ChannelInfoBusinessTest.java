package com.unicom.game.center.business;

import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.unicom.game.center.db.domain.ChannelInfoDomain;
import com.unicom.game.center.model.ChannelInfo;

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
	public void testFetchChannelInfo(){
		ChannelInfo channelInfo = channel.fetchChannelInfoByCode("18129");
		System.out.println((null != channelInfo) ? true : false);
	}
	
	@Test
	public void testStartChannel(){
		ChannelInfoDomain channelInfo = channel.startChannel("18129","浙江联通","qwert");
		System.out.println(channelInfo);
	}
	
	@Test
	public void testFetchActiveChannelInfos(){
		List<ChannelInfo> channelInfos = channel.fetchActiveChannelInfos();
		System.out.println((null != channelInfos) ? channelInfos.size() : 0);
	}
}
