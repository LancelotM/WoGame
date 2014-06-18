package com.unicom.game.center.persistence.db.dao;

import java.util.List;

import org.springframework.stereotype.Component;

import com.unicom.game.center.persistence.db.domain.KeyWordDomain;

@Component
public class KeyWordDao extends HibernateDao{
	
	public KeyWordDomain getByKeyWord(String keyword){
		String hql = "from KeyWordDomain where keyword = '" +keyword+"'";
		return (KeyWordDomain)getSession().createQuery(hql).uniqueResult();
	}
	
	public void save(KeyWordDomain keyWord){
		KeyWordDomain keyWordDomain = getByKeyWord(keyWord.getKeyword());
		if(keyWordDomain == null){
			getSession().save(keyWord);
		}else{
			keyWordDomain.setCount(keyWordDomain.getCount() + 1);
			keyWordDomain.setDateModified(new java.sql.Date(System.currentTimeMillis()));
			getSession().update(keyWordDomain);
		}
	}
	
	public List<KeyWordDomain> getAll(){
		return getSession().createQuery("from KeyWordDomain").list();
	}

}
