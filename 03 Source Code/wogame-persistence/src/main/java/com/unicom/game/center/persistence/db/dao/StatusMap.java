package com.unicom.game.center.persistence.db.dao;

import org.springframework.stereotype.Component;

import com.unicom.game.center.persistence.db.domain.StatusMapDomain;

@Component
public class StatusMap extends HibernateDao{
	
	public StatusMapDomain getById(int id){
		return (StatusMapDomain)getSession().load(StatusMapDomain.class, id);
	}

}
