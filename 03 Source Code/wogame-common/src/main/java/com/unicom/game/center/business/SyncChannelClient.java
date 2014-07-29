package com.unicom.game.center.business;

import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import javax.xml.namespace.QName;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.cm.ChannelInfo;
import com.cm.SyncChannels;
import com.cm.SyncChannels_Service;
import com.unicom.game.center.db.dao.ChannelInfoDao;
import com.unicom.game.center.db.domain.ChannelInfoDomain;
import com.unicom.game.center.utils.Logging;


/**
 * @author Alex Yin
 * 
 * @Date 2014-7-17
 */
@Component
@Transactional
public class SyncChannelClient {

    @Value("#{properties['sync.channel.wsdl']}")
    private String syncChannelWSDL;

	@Autowired
	private ChannelInfoDao channelInfoDao;
	
	/**
	 * 
	 * @param type	// 0 :add 1:update
	 * @param id
	 * @param channelCode
	 * @param channelName
	 */
	public void syncChannel (int type, int id, String channelCode, String channelName){
        SyncChannels_Service syc;
		try {
			syc = new SyncChannels_Service(new URL(syncChannelWSDL),
					new QName("http://cm.com/", "SyncChannels"));
			SyncChannels msyn = syc.getSyncChannelsPort();

	        if(channelCode.trim().length() != 8 && channelCode.trim().length() == 5){
	            channelCode = "000"+channelCode;
	        }
	        
			ChannelInfoDomain domain = channelInfoDao.getById(id);
			
			
	        List<ChannelInfo> m_clist = new ArrayList<ChannelInfo>();
	        ChannelInfo channelInfo = new ChannelInfo();
	        channelInfo.setId(id);
	        channelInfo.setChannelCode(channelCode);
	        if(null == channelName){
		        channelInfo.setChannelName(domain.getChannelName());	        	
	        }

	        channelInfo.setActive("1");
	        m_clist.add(channelInfo);
	        int code = msyn.syncchannel(type, m_clist);
	        domain.setSync_type(type);

	        if(0 != code){
		        domain.setSync_status(false);	        	
	        	Logging.logError("sync channel failed! channelid : " + id);
	        }else{
	        	domain.setSync_status(true);
	        }
	        
	        channelInfoDao.update(domain);
		} catch (Exception e) {
			Logging.logError("sync channel failed! channelid : " + id, e);
		}
        
	
	}
	
	
	public void syncFailureChannels(){
       SyncChannels_Service syc;
		try {
			syc = new SyncChannels_Service(new URL(syncChannelWSDL),
					new QName("http://cm.com/", "SyncChannels"));
			SyncChannels msyn = syc.getSyncChannelsPort();
			List<ChannelInfoDomain> channelDomainList = channelInfoDao.fetchSyncFaiureChannels();

			if(null != channelDomainList && !channelDomainList.isEmpty()){
				for(ChannelInfoDomain domain : channelDomainList){
					List<ChannelInfo> m_clist = new ArrayList<ChannelInfo>();
			        ChannelInfo channelInfo = new ChannelInfo();
			        channelInfo.setId(domain.getChannelId());
			        channelInfo.setChannelCode(domain.getChannelCode());
				    channelInfo.setChannelName(domain.getChannelName());	        	
			        channelInfo.setActive(domain.isStatus() ? "1" : "0");
			        m_clist.add(channelInfo);
			        
			        int code = msyn.syncchannel(domain.getSync_type(), m_clist);
			        if(0 == code){
			        	domain.setSync_status(true);
			        	channelInfoDao.update(domain);
			        }
				}
			}
		}catch(Exception e){
			Logging.logError("Error occur in syncFailureChannels.", e);
		}
	}

}
