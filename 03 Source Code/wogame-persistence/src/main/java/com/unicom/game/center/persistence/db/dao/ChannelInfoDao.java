package com.unicom.game.center.persistence.db.dao;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.unicom.game.center.persistence.db.domain.ChannelInfoDomain;

@Repository
public class ChannelInfoDao extends HibernateDao{
	
	public ChannelInfoDomain getById(int channelId){
		return (ChannelInfoDomain)getSession().load(ChannelInfoDomain.class, channelId);
	}
	
	public void save(ChannelInfoDomain channelInfo){
		getSession().save(channelInfo);
	}
	
	public void update(int id,int statusId){
		ChannelInfoDomain channelInfo = (ChannelInfoDomain)getSession().get(ChannelInfoDomain.class, id);
		channelInfo.setStatusId(statusId);
		channelInfo.setDateModified(new java.sql.Date(System.currentTimeMillis()));
		getSession().update(channelInfo);
	}
	
	public List<ChannelInfoDomain> getAll(){
		String hql = "from ChannelInfoDomain";
		List<ChannelInfoDomain> list = getSession().createQuery(hql).list();
		return list;
	}
	

}
