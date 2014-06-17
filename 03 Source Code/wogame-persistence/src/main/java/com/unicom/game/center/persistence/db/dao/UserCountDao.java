package com.unicom.game.center.persistence.db.dao;

import java.sql.Date;
import java.util.List;

import org.springframework.stereotype.Component;

import com.unicom.game.center.persistence.db.domain.UserCountDomain;

@Component
public class UserCountDao extends HibernateDao{
	
	public UserCountDomain getByDate(Date date){
		String hql = "from UserCountDomain where dateCreated = '"+date+"'";
		return (UserCountDomain)getSession().createQuery(hql).uniqueResult();
	}
	
	public void save(UserCountDomain userCountDomain){
		UserCountDomain userCount = getByDate(userCountDomain.getDateCreated());
		if(userCount == null){
			getSession().save(userCountDomain);
		}else{
			userCount.setNewUserCount(userCountDomain.getNewUserCount() +userCount.getNewUserCount());
			userCount.setOldUserCount(userCountDomain.getOldUserCount() +userCount.getOldUserCount());
			getSession().update(userCount);
		}
	}
	
	public void update(UserCountDomain userCountDomain){
		getSession().update(userCountDomain);
	}
	
	public List<UserCountDomain> getAll(){
		return getSession().createQuery("from UserCountDomain").list();
	}
	

}
