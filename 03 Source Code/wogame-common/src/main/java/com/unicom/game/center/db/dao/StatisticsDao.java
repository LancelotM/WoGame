package com.unicom.game.center.db.dao;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Query;
import org.hibernate.SQLQuery;
import org.springframework.stereotype.Component;

import com.unicom.game.center.db.domain.StatisticsDomain;
import com.unicom.game.center.model.PageTrafficModel;

/**
 * @author Alex
 *
 */
@Component
public class StatisticsDao extends HibernateDao<StatisticsDomain>{

	public void save(StatisticsDomain statistics){
		getSession().save(statistics);
		getSession().flush();
	}
	
	public void update(StatisticsDomain statistics){
		getSession().update(statistics);
		getSession().flush();
	}
	
    public void saveStatisticsDomainList(List<StatisticsDomain> list, int num) {
    	saveDomainList(list, num);
    }
    
	
	public List<PageTrafficModel> fetchTrafficInfoByDate(String startDate, String endDate, Integer channelId){
		StringBuffer sb = new StringBuffer();
		sb.append(" select sum(pt.homepagePV) as homepagePV,  sum(pt.homepageUV) as homepageUV,");
		sb.append(" DATE_FORMAT(pt.dateCreated, '%m-%d') as date");
		sb.append(" from StatisticsDomain pt");
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
		
		List<PageTrafficModel> trafficInfoList= convertToPageTrafficInfo(list);
		
		return trafficInfoList;	
	}

	public List<PageTrafficModel> fetchTrafficInfoByMonth(String startDate, String endDate, Integer channelId){
		StringBuffer sb = new StringBuffer();
		sb.append(" select sum(pt.homepage_pv) as homepagePV,  sum(pt.homepage_uv) as homepageUV,");
		sb.append(" DATE_FORMAT(pt.date_created, '%Y-%m') as date");
		sb.append(" from statistics pt");
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
		
		List<PageTrafficModel> trafficInfoList= convertToPageTrafficInfo(list);
			
		return trafficInfoList;
	}

	private List<PageTrafficModel> convertToPageTrafficInfo(List<Object[]> list){
		List<PageTrafficModel> trafficInfoList = null;
		if(null != list && list.size() > 0){
			trafficInfoList = new ArrayList<PageTrafficModel>();
			for(Object[] object : list){
				PageTrafficModel info = new PageTrafficModel();
				info.setHomepagePV(String.valueOf(object[0]));
				info.setHomepageUV(String.valueOf(object[1]));			
				info.setDate(String.valueOf(object[2]));
				trafficInfoList.add(info);
			}
		}
		
		return trafficInfoList;
	}
    
}
