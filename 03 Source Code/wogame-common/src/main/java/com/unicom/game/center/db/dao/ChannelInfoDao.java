package com.unicom.game.center.db.dao;

import java.util.List;

import org.springframework.stereotype.Component;

import com.unicom.game.center.db.domain.ChannelInfoDomain;

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
	
	@SuppressWarnings("unchecked")
	public List<ChannelInfoDomain> getAll(){
		return getSession().createQuery("from ChannelInfoDomain").list();
	}
	
	public List<ChannelInfoDomain> fetchActiveChannels(){
		String hql = "from ChannelInfoDomain channel where channel.status.statusId = 90";
		
		@SuppressWarnings("unchecked")
		List<ChannelInfoDomain> channelInfos= getSession().createQuery(hql).list();
		
		return channelInfos;
	}

}
