package com.unicom.game.center.business;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.unicom.game.center.db.dao.ChannelInfoDao;
import com.unicom.game.center.db.domain.ChannelInfoDomain;
import com.unicom.game.center.model.ChannelInfo;
import com.unicom.game.center.utils.AESEncryptionHelper;
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
	
	
	public List<ChannelInfo> fetchActiveChannelInfos(){
		List<ChannelInfo> channelInfos = null;
		try{
			channelInfos = channelInfoDao.fetchActiveChannels();
		}catch(Exception e){
			Logging.logError("Error occur in fetchActiveChannelInfos", e);
		}
		
		return channelInfos;
	}
	
	public ChannelInfo fetchChannelInfoById(int channelId){
		ChannelInfo channel = null;
		
		try{
			channel = channelInfoDao.fetchChannelById(channelId);
		}catch(Exception e){
			Logging.logError("Error occur in fetchChannelInfoById", e);
		}
		
		return channel;
	}
		
	public ChannelInfo fetchChannelInfoByName(String channelName){
		ChannelInfo channel = null;
		
		try{
			channel = channelInfoDao.fetchChannelByName(channelName);
		}catch(Exception e){
			Logging.logError("Error occur in fetchChannelInfoByName", e);
		}
		
		return channel;
	}
	
	public ChannelInfo fetchChannelInfoByCode(String channelCode){
		ChannelInfo channel = null;
		
		try{
			channel = channelInfoDao.fetchChannelInfoByCode(channelCode);
		}catch(Exception e){
			Logging.logError("Error occur in fetchChannelInfoByCode", e);
		}
		
		return channel;
	}
	
	public boolean updateChannel(int channelId, String channelCode, String cpId){
		boolean flag = false;
		
		try{
			ChannelInfoDomain channel = channelInfoDao.getById(channelId);
			if(null != channel){
				channel.setStatus(true);
				String siteToken = AESEncryptionHelper.encrypt(channelCode, siteKey);
				String backendToken = AESEncryptionHelper.encrypt(channelCode, backendKey);
				channel.setWapToken(siteToken);
				channel.setLogToken(backendToken);
				channel.setDateModified(new Date());
				channel.setCpId(cpId);
				channel.setChannelCode(channelCode);
				channelInfoDao.update(channel);					
			}
		}catch(Exception ex){
			Logging.logError("Error occur in update channel", ex);
		}
		
		return flag;
	}
	
	public ChannelInfoDomain startChannel(String channelCode, String channelName, String cpId){
		ChannelInfoDomain channel = null;
		
		try{
			Date date = new Date();
			channel = channelInfoDao.fetchChannelByCode(channelCode);
			if(null != channel){
				if(!channel.isStatus()){
					channel.setStatus(true);
					String siteToken = AESEncryptionHelper.encrypt(channelCode, siteKey);
					String backendToken = AESEncryptionHelper.encrypt(channelCode, backendKey);
					channel.setWapToken(siteToken);
					channel.setLogToken(backendToken);
					channel.setDateModified(date);
					channel.setDateCreated(date);
					channel.setChannelName(channelName);
					channel.setCpId(cpId);
					channel.setChannelCode(channelCode);
					channelInfoDao.update(channel);					
				}
			}else{
				channel = new ChannelInfoDomain();
				channel.setStatus(true);
				String siteToken = AESEncryptionHelper.encrypt(channelCode, siteKey);
				String backendToken = AESEncryptionHelper.encrypt(channelCode, backendKey);
				channel.setWapToken(siteToken);
				channel.setLogToken(backendToken);
				channel.setDateModified(date);
				channel.setDateCreated(date);
				channel.setChannelName(channelName);
				channel.setCpId(cpId);
				channel.setChannelCode(channelCode);
				channelInfoDao.save(channel);					
			}
		}catch(Exception e){
			Logging.logError("Error occur in startChannel", e);
		}
		
		return channel;		
	}

}
