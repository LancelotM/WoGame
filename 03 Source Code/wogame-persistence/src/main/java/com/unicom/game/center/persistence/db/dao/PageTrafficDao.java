package com.unicom.game.center.persistence.db.dao;

import java.sql.Date;
import java.util.List;

import org.hibernate.Query;
import org.springframework.stereotype.Component;

import com.unicom.game.center.persistence.db.domain.PageTrafficDomain;

/**
 * @author Alex.Yin
 * @Date Jun 09, 2014
 */

@Component
public class PageTrafficDao extends HibernateDao{
	
	public PageTrafficDomain getByPageAndChannel(int pageId,int channelId,Date date){
		StringBuffer sb = new StringBuffer();
		sb.append("from PageTrafficDomain where pageId =");
		sb.append(pageId);
		sb.append(" and channelId =");
		sb.append(channelId);
		sb.append(" and dateCreated = '");
		sb.append(date);
		sb.append("'");
		PageTrafficDomain pageTraffic = (PageTrafficDomain)getSession().createQuery(sb.toString()).uniqueResult();
		return pageTraffic;
	}
	
	
	public void save(PageTrafficDomain pageTraffic){
		PageTrafficDomain pageTrafficDomain = getByPageAndChannel(pageTraffic.getPageId(), 
				pageTraffic.getChannelId(),pageTraffic.getDateCreated());
		if(pageTrafficDomain == null){
			getSession().save(pageTraffic);
		}else{
			pageTrafficDomain.setClickThrough(pageTrafficDomain.getClickThrough() + 1);
			getSession().update(pageTrafficDomain);
		}
	}
	
	public List getAllByPage(int page,int rowsPerPage){
		
		String hql = "from PageTrafficDomain";
		int startRows = (page - 1) * rowsPerPage + 1;
		Query query = getSession().createQuery(hql);
		query.setFirstResult(startRows);
		query.setMaxResults(rowsPerPage);
		return query.list();
	}

}
