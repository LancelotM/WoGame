package com.unicom.game.center.db.dao;

import java.util.List;

import org.hibernate.transform.Transformers;
import org.springframework.stereotype.Component;

import com.unicom.game.center.db.domain.KeywordDomain;
import com.unicom.game.center.model.KeywordInfo;

@Component
public class KeywordDao extends HibernateDao<KeywordDomain>{
	
	public KeywordDomain getByKeyWord(String keyword){
		String hql = "from KeywordDomain where keyword = '" +keyword+"'";
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
	
	public List<KeywordInfo> getTop50Keyword(Integer channelId){
		StringBuffer sb = new StringBuffer();
		sb.append("select key.keyword as keyword, key.count as count");
		sb.append(" from KeywordDomain key");
        if(null != channelId && 0 != channelId.intValue()){
            sb.append(" where key.channelId = ");
            sb.append(channelId);
        }
		sb.append(" order by key.count desc");
		
		@SuppressWarnings("unchecked")
		List<KeywordInfo> keywords= getSession().createQuery(sb.toString())
										.setFirstResult(0)
										.setMaxResults(50)
										.setResultTransformer(Transformers.aliasToBean(KeywordInfo.class))
										.list();
        getSession().flush();
		return keywords;
	}
	
	public void saveKeywordDomainList(List<KeywordDomain> list, int num) {
		saveDomainList(list, num);
	}

    public int getDayCount(String date,Integer channelId){
        StringBuffer sb = new StringBuffer();
        sb.append("select sum(keyword.count) from KeywordDomain keyword where keyword.dateCreated = '");
        sb.append(date);
        sb.append("'");
        if(null != channelId && 0 != channelId.intValue()){
            sb.append(" and keyword.channelId = ");
            sb.append(channelId);
        }


        List list = getSession().createQuery(sb.toString()).list();
        getSession().flush();
        String result = null;
        for(Object obj : list){
            if(obj == null){
                result = String.valueOf("0");
            } else {
                result = String.valueOf(obj);
            }
        }
        return Integer.parseInt(result);
    }

    public int getThirtyCount(String startDate,String endDate,Integer channelId){
        StringBuffer sb = new StringBuffer();
        sb.append("select sum(keyword.count) from KeywordDomain keyword where keyword.dateCreated >= '");
        sb.append(startDate);
        sb.append("'");
        sb.append(" and keyword.dateCreated <= '");
        sb.append(endDate);
        sb.append("'");
        if(null != channelId && 0 != channelId.intValue()){
            sb.append(" and keyword.channelId = ");
            sb.append(channelId);
        }


        List list = getSession().createQuery(sb.toString()).list();
        getSession().flush();
        String result = null;
        for(Object obj : list){
            if(obj == null){
                result = String.valueOf("0");
            } else {
                result = String.valueOf(obj);
            }


        }
        return Integer.parseInt(result);
    }

}
