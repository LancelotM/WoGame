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
	}
	
	public void update(KeywordDomain keyWord){
		getSession().update(keyWord);
	}	
	
	public List<KeywordInfo> getTop50Keyword(){
		StringBuffer sb = new StringBuffer();
		sb.append("select top 50 key.keyword as keyword, key.count as count");
		sb.append(" from KeywordDomain key");
		sb.append(" order by key.count desc");
		
		@SuppressWarnings("unchecked")
		List<KeywordInfo> keywords= getSession().createQuery(sb.toString()).setResultTransformer(Transformers.aliasToBean(KeywordInfo.class)).list();		
		return keywords;
	}

}
