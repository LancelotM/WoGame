package com.unicom.game.center.db.dao;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.hibernate.Query;
import org.hibernate.transform.Transformers;
import org.springframework.stereotype.Component;

import com.unicom.game.center.db.domain.GameTrafficDomain;
import com.unicom.game.center.model.GameInfo;

@Component
public class GameTrafficDao extends HibernateDao{
	
	public void save(GameTrafficDomain gameTraffic){
		getSession().save(gameTraffic);
	}
	
	public void update(GameTrafficDomain gameTraffic){		
		getSession().update(gameTraffic);
	}
	
	public GameTrafficDomain getByProductAndChannelAndDate(String productId,int channelId,Date date,boolean flag){
		StringBuffer sb = new StringBuffer();
		sb.append("from GameTrafficDomain where productId ='");
		sb.append(productId);
		sb.append("' and channelId =");
		sb.append(channelId);
		sb.append(" and dateCreated = '");
		sb.append(date);
		sb.append("'");
		sb.append(" and flag = ");
		sb.append(flag);
		GameTrafficDomain hotGameTraffic = (GameTrafficDomain)getSession().
			createQuery(sb.toString()).uniqueResult();
		return hotGameTraffic;
	}	


	//TODO:
	public List<GameInfo> fetchGameInfoByDate(String startDate, String endDate, boolean bannerFlag, Integer channelId){	
		StringBuffer sb = new StringBuffer();
		sb.append("select traffic.product.productName as name,  traffic.product.productIcon as icon,");
		sb.append(" sum(traffic.clickThrough) as clickThrough, sum(traffic.downloadCount) as downloadCount,");
		sb.append(" DATE_FORMAT(traffic.dateCreated, '%m-%d') as date");
		sb.append(" from GameTrafficDomain traffic");
		sb.append(" where traffic.dateCreated >= '");
		sb.append(startDate);
		sb.append("' and traffic.dateCreated <= '");
		sb.append(endDate);
		sb.append("' and traffic.flag = ");
		sb.append(bannerFlag);
		
		if(null != channelId && 0 != channelId.intValue()){
			sb.append(" and traffic.channelId = ");
			sb.append(channelId);
		}
		
		sb.append(" group by traffic.dateCreated");
		sb.append(" order by traffic.sort asc, traffic.dateCreated desc");
		
		@SuppressWarnings("unchecked")
		List<GameInfo> gameList= getSession().createQuery(sb.toString()).setResultTransformer(Transformers.aliasToBean(GameInfo.class)).list();		
		return gameList;
	}
	
	//TODO:
	public List<GameInfo> fetchGameInfoByDate1(String startDate, String endDate, boolean bannerFlag, Integer channelId){	
		StringBuffer sb = new StringBuffer();
		sb.append("select p.product_id as productId, p.product_name as name, p.product_icon as icon");
		sb.append(" from game_traffic gt");
		sb.append(" inner join product p");
		sb.append(" on gt.product_id = p.product_id");
		sb.append(" where gt.date_created = '");
		sb.append(endDate);
		sb.append("' ");

		
		Query query = getSession().createQuery(sb.toString());
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

	
}
