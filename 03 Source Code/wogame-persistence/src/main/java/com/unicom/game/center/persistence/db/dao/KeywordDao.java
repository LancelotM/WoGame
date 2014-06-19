package com.unicom.game.center.persistence.db.dao;

import java.util.List;

import org.springframework.stereotype.Component;

import com.unicom.game.center.persistence.db.domain.KeywordDomain;

@Component
public class KeywordDao extends HibernateDao{
	
	public KeywordDomain getByKeyWord(String keyword){
		String hql = "from KeyWordDomain where keyword = '" +keyword+"'";
		return (KeywordDomain)getSession().createQuery(hql).uniqueResult();
	}
	
	public void save(KeywordDomain keyWord){
		KeywordDomain keyWordDomain = getByKeyWord(keyWord.getKeyword());
		if(keyWordDomain == null){
			getSession().save(keyWord);
		}else{
			keyWordDomain.setCount(keyWordDomain.getCount() + 1);
			keyWordDomain.setDateModified(new java.sql.Date(System.currentTimeMillis()));
			getSession().update(keyWordDomain);
		}
	}
	
	public List<KeywordDomain> getAll(){
		return getSession().createQuery("from KeyWordDomain").list();
	}

}
