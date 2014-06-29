package com.unicom.game.center.business;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.unicom.game.center.db.dao.ChannelInfoDao;
import com.unicom.game.center.db.domain.ChannelInfoDomain;
import com.unicom.game.center.db.domain.StatusMapDomain;
import com.unicom.game.center.utils.AESEncryptionHelper;
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
	
	@Value("#{properties['site.secret.key']}")
	private String siteKey;
	
	@Value("#{properties['backend.secret.key']}")
	private String backendKey;
	
	@Value("#{properties['wogame.wap.link']}")
	private String wapLink;
	
	@Value("#{properties['wogame.log.link']}")
	private String logLink;	
	
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
	
	public ChannelInfoDomain fetchChannelInfo(int channelId){
		ChannelInfoDomain channel = null;
		
		try{
			channel = channelInfoDao.getById(channelId);
		}catch(Exception e){
			Logging.logError("Error occur in fetchChannelInfo", e);
		}
		
		return channel;
	}
	
	public ChannelInfoDomain startChannel(int channelId){
		ChannelInfoDomain channel = null;
		
		try{
			channel = channelInfoDao.getById(channelId);
			if(null != channel && Constant.ACTIVE_STATUS_ID != channel.getStatus().getStatusId()){
				StatusMapDomain status = new StatusMapDomain();
				status.setStatusId(Constant.ACTIVE_STATUS_ID);
				channel.setStatus(status);
				String siteToken = AESEncryptionHelper.encrypt(String.valueOf(channelId), siteKey);
				String backendToken = AESEncryptionHelper.encrypt(String.valueOf(channelId), backendKey);
				channel.setWapURL(wapLink + siteToken);
				channel.setLogURL(logLink + backendToken);
				channel.setDateModified(new Date());
				channelInfoDao.update(channel);
			}
		}catch(Exception e){
			Logging.logError("Error occur in startChannel", e);
		}
		
		return channel;		
	}

}
