package com.unicom.game.center.db.dao;

import java.util.List;

import org.hibernate.transform.Transformers;
import org.springframework.stereotype.Component;

import com.unicom.game.center.db.domain.ChannelInfoDomain;
import com.unicom.game.center.model.ChannelInfo;

@Component
public class ChannelInfoDao extends HibernateDao{
	
	public ChannelInfoDomain getById(int channelId){
		return (ChannelInfoDomain)getSession().get(ChannelInfoDomain.class, channelId);
	}
	
	public void save(ChannelInfoDomain channelInfo){
		getSession().save(channelInfo);
	}
	
	public void update(ChannelInfoDomain channelInfo){		
		getSession().update(channelInfo);
		getSession().flush();
	}
	
	public List<ChannelInfo> fetchActiveChannels(){
		StringBuffer sb = new StringBuffer();
		sb.append("select channel.channelId as channelId, channel.channelName as channelName,");
		sb.append(" channel.wapToken as wapToken, channel.logToken as logToken,");
		sb.append(" DATE_FORMAT(channel.dateModified, '%Y-%m-%d') as date");
		sb.append(" from ChannelInfoDomain channel where channel.status.statusId = 90");
		
		@SuppressWarnings("unchecked")
		List<ChannelInfo> channelInfos= getSession().createQuery(sb.toString())
									.setResultTransformer(Transformers.aliasToBean(ChannelInfo.class))
									.list();
		
		return channelInfos;
	}

}
