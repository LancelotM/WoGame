package com.unicom.game.center.business;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.unicom.game.center.db.dao.ChannelInfoDao;
import com.unicom.game.center.db.domain.ChannelInfoDomain;
import com.unicom.game.center.db.domain.StatusMapDomain;
import com.unicom.game.center.utils.Constant;
import com.unicom.game.center.utils.Logging;

/**
 * @author Alex Yin
 * 
 * @Date Jun 21, 2014
 */
@Component
@Transactional
public class ChannelInfoBusiness {
	
	@Autowired
	private ChannelInfoDao channelInfoDao;
	
	public List<ChannelInfoDomain> fetchAllChannelInfos(){
		List<ChannelInfoDomain> channelInfos = null;
		
		try{
			channelInfos = channelInfoDao.getAll();
		}catch(Exception e){
			Logging.logError("Error occur in fetchAllChannelInfos", e);
		}
		
		return channelInfos;
	}
	
	public List<ChannelInfoDomain> fetchActiveChannelInfos(){
		List<ChannelInfoDomain> channelInfos = null;
		
		try{
			channelInfos = channelInfoDao.fetchActiveChannels();
		}catch(Exception e){
			Logging.logError("Error occur in fetchAllChannelInfos", e);
		}
		
		return channelInfos;
	}
	
	public boolean checkChannelIsActive(int channelId){
		boolean flag = false;
		
		try{
			ChannelInfoDomain channel = channelInfoDao.getById(channelId);
			if(null != channel && Constant.ACTIVE_STATUS_ID == channel.getStatus().getStatusId()){
				flag = true;
			}
		}catch(Exception e){
			Logging.logError("Error occur in checkChannelIsActive", e);
		}
		
		return flag;		
		
	}
	
	public boolean startChannel(int channelId){
		boolean flag = false;
		
		try{
			ChannelInfoDomain channel = channelInfoDao.getById(channelId);
			if(null != channel && Constant.ACTIVE_STATUS_ID != channel.getStatus().getStatusId()){
				StatusMapDomain status = new StatusMapDomain();
				status.setStatusId(Constant.ACTIVE_STATUS_ID);
				channel.setStatus(status);
				channel.setDateModified(new Date());
				channelInfoDao.update(channel);
				flag = true;
			}
		}catch(Exception e){
			Logging.logError("Error occur in startChannel", e);
		}
		
		return flag;		
	}

}
