package com.unicom.game.center.business;

import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import javax.xml.namespace.QName;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import com.cm.ChannelInfo;
import com.cm.SyncChannels;
import com.cm.SyncChannels_Service;
import com.unicom.game.center.utils.Logging;


/**
 * @author Alex Yin
 * 
 * @Date 2014-7-17
 */
@Component
public class SyncChannelClient {

    @Value("#{properties['sync.channel.wsdl']}")
    private String syncChannelWSDL;

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
			
	        List<ChannelInfo> m_clist = new ArrayList<ChannelInfo>();
	        ChannelInfo channelInfo = new ChannelInfo();
	        channelInfo.setId(id);
	        channelInfo.setChannelCode(channelCode);
	        if(null != channelName){
		        channelInfo.setChannelName(channelName);	        	
	        }

	        channelInfo.setActive("1");
	        m_clist.add(channelInfo);
	        int code = msyn.syncchannel(type, m_clist);   			
	        if(0 != code){
	        	Logging.logError("sync channel failed! channelid : " + id);
	        }
		} catch (Exception e) {
			Logging.logError("sync channel failed! channelid : " + id, e);
		}
        
	
	}

}
