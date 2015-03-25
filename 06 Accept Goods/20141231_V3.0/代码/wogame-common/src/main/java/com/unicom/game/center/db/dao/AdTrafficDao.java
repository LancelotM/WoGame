package com.unicom.game.center.db.dao;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Query;
import org.springframework.stereotype.Component;

import com.unicom.game.center.db.domain.AdTrafficDomain;
import com.unicom.game.center.model.AdInfo;

@Component
public class AdTrafficDao extends HibernateDao<AdTrafficDomain>{
	
	public void save(AdTrafficDomain gameTraffic){
		getSession().save(gameTraffic);
		getSession().flush();
	}
	
	public void update(AdTrafficDomain gameTraffic){		
		getSession().update(gameTraffic);
		getSession().flush();
	}
	
	public List<AdInfo> fetchAdInfoByDate(String startDate, String endDate, Integer channelId){	
		StringBuffer sb = new StringBuffer();
		sb.append("select traffic.ad_id as adId, sum(traffic.click_through) as clickThrough,");
		sb.append(" DATE_FORMAT(traffic.date_created, '%Y-%m-%d') as date");
		sb.append(" from ad_traffic traffic");
		sb.append(" where traffic.date_created >= '");
		sb.append(startDate);
		sb.append("' and traffic.date_created <= '");
		sb.append(endDate);
		sb.append("' and traffic.ad_id != 0");		
		
		if(null != channelId && 0 != channelId.intValue()){
			sb.append(" and traffic.channel_id = ");
			sb.append(channelId);
		}
		
		sb.append(" group by date, traffic.ad_id");
		sb.append(" order by date desc, adId asc");

		
		Query query = getSession().createSQLQuery(sb.toString());
		@SuppressWarnings("unchecked")
		List<Object[]> list = query.list();
		
		List<AdInfo> gameList= convertToGameInfo(list);		
		return gameList;
	}

	
	public List<AdInfo> fetchAdInfoByMonth(String startDate, String endDate, Integer channelId){	
		StringBuffer sb = new StringBuffer();
		sb.append("select traffic.ad_id as adId, sum(traffic.click_through) as clickThrough,");
		sb.append(" DATE_FORMAT(traffic.date_created, '%Y-%m') as date");
		sb.append(" from ad_traffic traffic");
		sb.append(" where traffic.date_created >= '");
		sb.append(startDate);
		sb.append("' and traffic.date_created <= '");
		sb.append(endDate);
		sb.append("' and traffic.ad_id != 0");
		
		if(null != channelId && 0 != channelId.intValue()){
			sb.append(" and traffic.channel_id = ");
			sb.append(channelId);
		}
		
		sb.append(" group by date, traffic.ad_id");
		sb.append(" order by date desc, adId asc");

		
		Query query = getSession().createSQLQuery(sb.toString());
		@SuppressWarnings("unchecked")
		List<Object[]> list = query.list();
		
		List<AdInfo> gameList= convertToGameInfo(list);		
		return gameList;
	}
	
	public List<AdInfo> fetchBannerInfoByDate(String startDate, String endDate, Integer channelId){	
		StringBuffer sb = new StringBuffer();
		sb.append("select traffic.sort as adId, sum(traffic.click_through) as clickThrough,");
		sb.append(" DATE_FORMAT(traffic.date_created, '%Y-%m-%d') as date");
		sb.append(" from ad_traffic traffic");
		sb.append(" where traffic.date_created >= '");
		sb.append(startDate);
		sb.append("' and traffic.date_created <= '");
		sb.append(endDate);
		sb.append("' and traffic.ad_id = 0");		
		
		if(null != channelId && 0 != channelId.intValue()){
			sb.append(" and traffic.channel_id = ");
			sb.append(channelId);
		}
		
		sb.append(" group by date, traffic.sort");
		sb.append(" order by date desc, adId asc");

		
		Query query = getSession().createSQLQuery(sb.toString());
		@SuppressWarnings("unchecked")
		List<Object[]> list = query.list();
		
		List<AdInfo> gameList= convertToGameInfo(list);		
		return gameList;
	}

	
	public List<AdInfo> fetchBannerInfoByMonth(String startDate, String endDate, Integer channelId){	
		StringBuffer sb = new StringBuffer();
		sb.append("select traffic.sort as adId, sum(traffic.click_through) as clickThrough,");
		sb.append(" DATE_FORMAT(traffic.date_created, '%Y-%m') as date");
		sb.append(" from ad_traffic traffic");
		sb.append(" where traffic.date_created >= '");
		sb.append(startDate);
		sb.append("' and traffic.date_created <= '");
		sb.append(endDate);
		sb.append("' and traffic.ad_id = 0");
		
		if(null != channelId && 0 != channelId.intValue()){
			sb.append(" and traffic.channel_id = ");
			sb.append(channelId);
		}
		
		sb.append(" group by date, traffic.sort");
		sb.append(" order by date desc, adId asc");

		
		Query query = getSession().createSQLQuery(sb.toString());
		@SuppressWarnings("unchecked")
		List<Object[]> list = query.list();
		
		List<AdInfo> gameList= convertToGameInfo(list);		
		return gameList;
	}	
	
	
	private List<AdInfo> convertToGameInfo(List<Object[]> list){
		List<AdInfo> adInfoList = null;
		if(null != list && list.size() > 0){
			adInfoList = new ArrayList<AdInfo>();
			for(Object[] object : list){
				AdInfo info = new AdInfo();
				info.setAdId(String.valueOf(object[0]));
				info.setClickThrough(String.valueOf(object[1]));
				info.setDate(String.valueOf(object[2]));				
				adInfoList.add(info);
			}
		}
		
		return adInfoList;
	}

	public void saveAdTrafficDomainList(List<AdTrafficDomain> list, int num) {
		saveDomainList(list, num);
	}
	
}
