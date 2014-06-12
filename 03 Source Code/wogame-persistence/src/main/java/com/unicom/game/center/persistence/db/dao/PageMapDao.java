package com.unicom.game.center.persistence.db.dao;

import org.springframework.stereotype.Repository;

import com.unicom.game.center.persistence.db.domain.PageMapDomain;

@Repository
public class PageMapDao extends HibernateDao{
	
	public PageMapDomain getByid(int id){
		PageMapDomain pageMap = (PageMapDomain) getSession().load(PageMapDomain.class, id);
		return pageMap;
	}
	

}
