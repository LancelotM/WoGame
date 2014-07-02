package com.unicom.game.center.db.dao;

import java.util.List;

import org.hibernate.transform.Transformers;
import org.springframework.stereotype.Component;

import com.unicom.game.center.db.domain.KeywordDomain;
import com.unicom.game.center.model.KeywordInfo;

@Component
public class KeywordDao extends HibernateDao{
	
	public KeywordDomain getByKeyWord(String keyword){
		String hql = "from KeyWordDomain where keyword = '" +keyword+"'";
		return (KeywordDomain)getSession().createQuery(hql).uniqueResult();
	}
	
	public void save(KeywordDomain keyWord){
		getSession().save(keyWord);
		getSession().flush();
	}
	
	public void update(KeywordDomain keyWord){
		getSession().update(keyWord);
		getSession().flush();
	}	
	
	public List<KeywordInfo> getTop50Keyword(){
		StringBuffer sb = new StringBuffer();
		sb.append("select key.keyword as keyword, key.count as count");
		sb.append(" from KeywordDomain key");
		sb.append(" order by key.count desc");
		
		@SuppressWarnings("unchecked")
		List<KeywordInfo> keywords= getSession().createQuery(sb.toString())
										.setFirstResult(0)
										.setMaxResults(50)
										.setResultTransformer(Transformers.aliasToBean(KeywordInfo.class))
										.list();		
		return keywords;
	}

}
