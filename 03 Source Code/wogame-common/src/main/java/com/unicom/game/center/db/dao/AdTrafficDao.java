package com.unicom.game.center.db.dao;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Query;
import org.springframework.stereotype.Component;

import com.unicom.game.center.db.domain.AdTrafficDomain;
import com.unicom.game.center.model.GameInfo;

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
	
	public List<GameInfo> fetchGameInfoByDate(String startDate, String endDate, boolean bannerFlag, Integer channelId){	
		StringBuffer sb = new StringBuffer();
		sb.append("select temp.name as name, temp.icon as icon, sum(traffic.click_through) as clickThrough,");
		sb.append(" sum(traffic.download_count) as downloadCount, DATE_FORMAT(traffic.date_created, '%m-%d') as date");
		sb.append(" from game_traffic traffic");
		sb.append(" inner join");
		sb.append(" (select p.product_id as productId, p.product_name as name, p.product_icon as icon, gt.sort as sort");
		sb.append(" from game_traffic gt");
		sb.append(" inner join product p");
		sb.append(" on gt.product_id = p.product_id");
		sb.append(" where gt.date_created = '");
		sb.append(endDate);
		sb.append("' group by gt.product_id) as temp");
		sb.append(" on traffic.product_id = temp.productId");
		sb.append(" where traffic.date_created >= '");
		sb.append(startDate);
		sb.append("' and traffic.date_created <= '");
		sb.append(endDate);
		sb.append("' and traffic.banner_flag = ");
		sb.append(bannerFlag);
		
		if(null != channelId && 0 != channelId.intValue()){
			sb.append(" and traffic.channel_id = ");
			sb.append(channelId);
		}
		
		sb.append(" group by date, traffic.product_id");
		sb.append(" order by temp.sort asc, date desc");

		
		Query query = getSession().createSQLQuery(sb.toString());
		@SuppressWarnings("unchecked")
		List<Object[]> list = query.list();
		
		List<GameInfo> gameList= convertToGameInfo(list);		
		return gameList;
	}

	
	public List<GameInfo> fetchGameInfoByMonth(String startDate, String endDate, boolean bannerFlag, Integer channelId){	
		StringBuffer sb = new StringBuffer();
		sb.append("select temp.name as name, temp.icon as icon, sum(traffic.click_through) as clickThrough,");
		sb.append(" sum(traffic.download_count) as downloadCount, DATE_FORMAT(traffic.date_created, '%Y-%m') as date");
		sb.append(" from game_traffic traffic");
		sb.append(" inner join");
		sb.append(" (select p.product_id as productId, p.product_name as name, p.product_icon as icon, gt.sort as sort");
		sb.append(" from game_traffic gt");
		sb.append(" inner join product p");
		sb.append(" on gt.product_id = p.product_id");
		sb.append(" where gt.date_created = '");
		sb.append(endDate);
		sb.append("' group by gt.product_id) as temp");
		sb.append(" on traffic.product_id = temp.productId");
		sb.append(" where traffic.date_created >= '");
		sb.append(startDate);
		sb.append("' and traffic.date_created <= '");
		sb.append(endDate);
		sb.append("' and traffic.banner_flag = ");
		sb.append(bannerFlag);
		
		if(null != channelId && 0 != channelId.intValue()){
			sb.append(" and traffic.channel_id = ");
			sb.append(channelId);
		}
		
		sb.append(" group by date, traffic.product_id");
		sb.append(" order by temp.sort asc, date desc");

		
		Query query = getSession().createSQLQuery(sb.toString());
		@SuppressWarnings("unchecked")
		List<Object[]> list = query.list();
		
		List<GameInfo> gameList= convertToGameInfo(list);		
		return gameList;
	}
	
	
	private List<GameInfo> convertToGameInfo(List<Object[]> list){
		List<GameInfo> gameInfoList = null;
		if(null != list && list.size() > 0){
			gameInfoList = new ArrayList<GameInfo>();
			for(Object[] object : list){
				GameInfo info = new GameInfo();
				info.setName(String.valueOf(object[0]));
				info.setIcon(String.valueOf(object[1]));
				info.setClickThrough(String.valueOf(object[2]));
				info.setDownloadCount(String.valueOf(object[3]));
				info.setDate(String.valueOf(object[4]));				
				gameInfoList.add(info);
			}
		}
		
		return gameInfoList;
	}

	public void saveGameTrafficDomainList(List<AdTrafficDomain> list, int num) {
		saveDomainList(list, num);
	}
	
}
