package com.unicom.game.center.db.dao;

import java.util.List;

import org.hibernate.transform.Transformers;
import org.springframework.stereotype.Component;

import com.unicom.game.center.db.domain.ChannelInfoDomain;
import com.unicom.game.center.model.ChannelInfo;

@Component
public class ChannelInfoDao extends HibernateDao<ChannelInfoDomain>{
	
	public ChannelInfoDomain getById(int channelId){
		Object obj = getSession().get(ChannelInfoDomain.class, channelId);
		
		return (null != obj) ? (ChannelInfoDomain)obj : null;
	}
	
	public void save(ChannelInfoDomain channelInfo){
		getSession().save(channelInfo);
		getSession().flush();
	}
	
	public void update(ChannelInfoDomain channelInfo){		
		getSession().update(channelInfo);
		getSession().flush();
	}
	
	public ChannelInfo fetchChannelById(Integer channelId){
		StringBuffer sb = new StringBuffer();
		sb.append("select channel.channelId as channelId, channel.channelName as channelName,");
		sb.append(" channel.cpId as cpId, channel.channelCode as channelCode,");
		sb.append(" channel.wapToken as wapToken, channel.logToken as logToken, channel.status as flag,");
		sb.append(" DATE_FORMAT(channel.dateModified, '%Y-%m-%d') as date");
		sb.append(" from ChannelInfoDomain channel where channel.channelId = ");
		sb.append(channelId);
		
		ChannelInfo channelInfo= (ChannelInfo)getSession().createQuery(sb.toString())
									.setResultTransformer(Transformers.aliasToBean(ChannelInfo.class))
									.uniqueResult();
		
		return channelInfo;		
	}
	
	public ChannelInfoDomain fetchChannelByCode(String channelCode){
		StringBuffer sb = new StringBuffer();
		sb.append(" from ChannelInfoDomain channel where channel.channelCode = '");
		sb.append(channelCode);
		sb.append("'");
		
		ChannelInfoDomain channelInfo= (ChannelInfoDomain)getSession().createQuery(sb.toString())
									.uniqueResult();
		
		return channelInfo;		
	}	
	
	
	public ChannelInfo fetchChannelInfoByCode(String channelCode){
		StringBuffer sb = new StringBuffer();
		sb.append("select channel.channelId as channelId, channel.channelName as channelName,");
		sb.append(" channel.cpId as cpId, channel.channelCode as channelCode,");
		sb.append(" channel.wapToken as wapToken, channel.logToken as logToken,channel.status as flag,");
		sb.append(" DATE_FORMAT(channel.dateModified, '%Y-%m-%d') as date");
		sb.append(" from ChannelInfoDomain channel where channel.channelCode = '");
		sb.append(channelCode);
		sb.append("'");
		
		ChannelInfo channelInfo= (ChannelInfo)getSession().createQuery(sb.toString())
									.setResultTransformer(Transformers.aliasToBean(ChannelInfo.class))
									.uniqueResult();
		
		return channelInfo;		
	}	
	
	public ChannelInfo fetchChannelByName(String channelName){
		StringBuffer sb = new StringBuffer();
		sb.append("select channel.channelId as channelId, channel.channelName as channelName,");
		sb.append(" channel.cpId as cpId, channel.channelCode as channelCode,");
		sb.append(" channel.wapToken as wapToken, channel.logToken as logToken,channel.status as flag,");
		sb.append(" DATE_FORMAT(channel.dateModified, '%Y-%m-%d') as date");
		sb.append(" from ChannelInfoDomain channel where channel.channelName like '%");
		sb.append(channelName);
		sb.append("%'");
		
		
		@SuppressWarnings("unchecked")
		List<ChannelInfo> channelInfos= getSession().createQuery(sb.toString())
									.setResultTransformer(Transformers.aliasToBean(ChannelInfo.class))
									.list();
		
		return (null != channelInfos && !channelInfos.isEmpty()) ? channelInfos.get(0) : null;		
	}
	
	public List<ChannelInfo> fetchActiveChannels(){
		StringBuffer sb = new StringBuffer();
		sb.append("select channel.channelId as channelId, channel.channelName as channelName,");
		sb.append(" channel.cpId as cpId, channel.channelCode as channelCode,");
		sb.append(" channel.wapToken as wapToken, channel.logToken as logToken,channel.status as flag,");
		sb.append(" DATE_FORMAT(channel.dateModified, '%Y-%m-%d') as date");
		sb.append(" from ChannelInfoDomain channel where channel.status = true");
		
		@SuppressWarnings("unchecked")
		List<ChannelInfo> channelInfos= getSession().createQuery(sb.toString())
									.setResultTransformer(Transformers.aliasToBean(ChannelInfo.class))
									.list();
		
		return channelInfos;
	}

	
	public List<ChannelInfoDomain> fetchSyncFaiureChannels(){
		StringBuffer sb = new StringBuffer();
		sb.append("from ChannelInfoDomain channel where channel.sync_status = false");
		
		@SuppressWarnings("unchecked")
		List<ChannelInfoDomain> channelInfos= getSession().createQuery(sb.toString())
									.list();
		
		return channelInfos;
	}	
}
