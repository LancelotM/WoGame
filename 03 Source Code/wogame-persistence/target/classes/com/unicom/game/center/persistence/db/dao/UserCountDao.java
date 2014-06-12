package com.unicom.game.center.persistence.db.dao;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.unicom.game.center.persistence.db.domain.UserCountDomain;

@Repository
public class UserCountDao extends HibernateDao{
	
	public void save(UserCountDomain userCountDomain){
		getSession().save(userCountDomain);
	}
	
	public void update(UserCountDomain userCountDomain){
		getSession().update(userCountDomain);
	}
	
	public List<UserCountDomain> getAll(){
		return getSession().createQuery("from UserCountDomain").list();
	}
	

}
