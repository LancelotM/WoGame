package com.unicom.game.center.db.dao;

import java.util.Date;
import java.util.List;

import org.hibernate.Query;
import org.springframework.stereotype.Component;

import com.unicom.game.center.db.domain.GameTrafficDomain;

@Component
public class GameTrafficDao extends HibernateDao{
	
	public GameTrafficDomain getByProductAndChannelAndDate(String productId,int channelId,Date date,boolean flag){
		StringBuffer sb = new StringBuffer();
		sb.append("from HotGameTrafficDomain where productId ='");
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
	
	public void save(GameTrafficDomain hotGameTraffic){
		GameTrafficDomain hotGameTrafficDomain = getByProductAndChannelAndDate
			(hotGameTraffic.getProductId(), hotGameTraffic.getChannelId(),
					hotGameTraffic.getDateCreated(),hotGameTraffic.getFlag());
		if(hotGameTrafficDomain == null){
			getSession().save(hotGameTraffic);
		}else{
			hotGameTrafficDomain.setClickThrough(hotGameTrafficDomain.getClickThrough() + 1);
			getSession().update(hotGameTrafficDomain);
		}
	}
	
	public List getByFlag(boolean flag){
		StringBuilder hql = new StringBuilder();
		hql.append("from HotGameTrafficDomain where flag = ");
		hql.append(flag);
		Query query = getSession().createQuery(hql.toString());
		return query.list();
	}

}
