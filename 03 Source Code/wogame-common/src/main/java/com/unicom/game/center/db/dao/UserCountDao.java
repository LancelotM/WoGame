package com.unicom.game.center.db.dao;

import java.util.Date;

import org.springframework.stereotype.Component;

import com.unicom.game.center.db.domain.UserCountDomain;

@Component
public class UserCountDao extends HibernateDao{
	
	public void save(UserCountDomain userCountDomain){
		getSession().save(userCountDomain);
	}
	
	public void update(UserCountDomain userCountDomain){
		getSession().update(userCountDomain);
	}
	
	public UserCountDomain getByDate(Date date){
		String hql = "from UserCountDomain where dateCreated = '"+date+"'";
		return (UserCountDomain)getSession().createQuery(hql).uniqueResult();
	}
	

}
