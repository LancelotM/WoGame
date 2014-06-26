package com.unicom.game.center.db.dao;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Query;
import org.hibernate.SQLQuery;
import org.springframework.stereotype.Component;

import com.unicom.game.center.db.domain.PageTrafficDomain;
import com.unicom.game.center.model.PageTrafficInfo;

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
	
	public List<PageTrafficInfo> fetchTrafficInfoByDate(String startDate, String endDate, Integer channelId){
		StringBuffer sb = new StringBuffer();
		sb.append(" select sum(pt.homepage) as homepage,  sum(pt.category) as category,");
		sb.append(" sum(pt.hotlist) as hotlist,  sum(pt.latest) as latest,");
		sb.append(" DATE_FORMAT(pt.dateCreated, '%m-%d') as date");
		sb.append(" from PageTrafficDomain pt");
		sb.append(" where pt.dateCreated >= '");
		sb.append(startDate);
		sb.append("' and pt.dateCreated <= '");
		sb.append(endDate);
		sb.append("'");
		

		if(null != channelId && 0 != channelId.intValue()){
			sb.append(" and pt.channelId = ");
			sb.append(channelId);
		}
		
		sb.append(" group by pt.dateCreated");
		
		sb.append(" order by pt.dateCreated asc");
		
		Query query = getSession().createQuery(sb.toString());
		@SuppressWarnings("unchecked")
		List<Object[]> list = query.list();
		
		List<PageTrafficInfo> trafficInfoList= convertToPageTrafficInfo(list);
		
		return trafficInfoList;	
	}

	public List<PageTrafficInfo> fetchTrafficInfoByMonth(String startDate, String endDate, Integer channelId){
		StringBuffer sb = new StringBuffer();
		sb.append(" select sum(pt.homepage) as homepage,  sum(pt.category) as category,");
		sb.append(" sum(pt.hotlist) as hotlist,  sum(pt.latest) as latest,");
		sb.append(" DATE_FORMAT(pt.date_created, '%Y-%m') as date");
		sb.append(" from page_traffic pt");
		sb.append(" where pt.date_created >= '");
		sb.append(startDate);
		sb.append("' and pt.date_created <= '");
		sb.append(endDate);
		sb.append("'");		

		if(null != channelId && 0 != channelId.intValue()){
			sb.append(" and pt.channel_id = ");
			sb.append(channelId);
		}
		
		sb.append(" group by date");
		
		sb.append(" order by date asc");
		
		SQLQuery sqlQuery = getSession().createSQLQuery(sb.toString());
		
		@SuppressWarnings("unchecked")
		List<Object[]> list = sqlQuery.list();
		
		List<PageTrafficInfo> trafficInfoList= convertToPageTrafficInfo(list);
			
		return trafficInfoList;
	}
	
	private List<PageTrafficInfo> convertToPageTrafficInfo(List<Object[]> list){
		List<PageTrafficInfo> trafficInfoList = null;
		if(null != list && list.size() > 0){
			trafficInfoList = new ArrayList<PageTrafficInfo>();
			for(Object[] object : list){
				PageTrafficInfo info = new PageTrafficInfo();
				info.setHomepage(String.valueOf(object[0]));
				info.setCategory(String.valueOf(object[1]));
				info.setHotlist(String.valueOf(object[2]));
				info.setLatest(String.valueOf(object[3]));			
				info.setDate(String.valueOf(object[4]));
				trafficInfoList.add(info);
			}
		}
		
		return trafficInfoList;
	}


}
