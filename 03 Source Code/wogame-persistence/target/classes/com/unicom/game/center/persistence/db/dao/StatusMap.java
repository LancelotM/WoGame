package com.unicom.game.center.persistence.db.dao;

import org.springframework.stereotype.Repository;

import com.unicom.game.center.persistence.db.domain.StatusMapDomain;

@Repository
public class StatusMap extends HibernateDao{
	
	public StatusMapDomain getById(int id){
		return (StatusMapDomain)getSession().load(StatusMapDomain.class, id);
	}

}
