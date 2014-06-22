package com.unicom.game.center.db.dao;

import java.util.Date;
import java.util.List;

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


	public List<GameInfo> fetchGameInfoByDate(String startDate, String endDate, boolean bannerFlag, Integer channelId){	
		StringBuffer sb = new StringBuffer();
		sb.append("select traffic.product.productName as name,  traffic.product.productIcon as icon,");
		sb.append(" traffic.clickThrough as clickThrough, traffic.downloadCount as downloadCount,");
		sb.append(" traffic.dateCreated as date");
		sb.append(" from GameTrafficDomain traffic");
		sb.append(" where traffic.dateCreated >= '");
		sb.append(startDate);
		sb.append("' and traffic.dateCreated < '");
		sb.append(endDate);
		sb.append("' and traffic.flag = ");
		sb.append(bannerFlag);
		
		if(null != channelId && 0 != channelId.intValue()){
			sb.append(" and traffic.channelId = ");
			sb.append(channelId);
		}
		
		sb.append(" order by traffic.sort asc, traffic.dateCreated desc");
		
		@SuppressWarnings("unchecked")
		List<GameInfo> gameList= getSession().createQuery(sb.toString()).setResultTransformer(Transformers.aliasToBean(GameInfo.class)).list();		
		return gameList;
	}

	
}
