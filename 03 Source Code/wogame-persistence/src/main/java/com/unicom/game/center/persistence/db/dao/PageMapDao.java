package com.unicom.game.center.persistence.db.dao;

import org.springframework.stereotype.Component;

import com.unicom.game.center.persistence.db.domain.PageMapDomain;

@Component
public class PageMapDao extends HibernateDao{
	
	public PageMapDomain getByid(int id){
		PageMapDomain pageMap = (PageMapDomain) getSession().load(PageMapDomain.class, id);
		return pageMap;
	}
	

}
