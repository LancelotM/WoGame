package com.unicom.game.center.db.dao;

import java.util.Date;

import org.springframework.stereotype.Component;

import com.unicom.game.center.db.domain.PageTrafficDomain;

/**
 * @author Alex.Yin
 * @Date Jun 09, 2014
 */

@Component
public class PageTrafficDao extends HibernateDao{

	public void save(PageTrafficDomain pageTraffic){
		getSession().save(pageTraffic);
	}
	
	public void update(PageTrafficDomain pageTraffic){
		getSession().update(pageTraffic);
	}
	
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


}
