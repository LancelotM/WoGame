package com.unicom.game.center.persistence.db.dao;

import java.sql.Date;
import java.util.List;

import org.hibernate.Query;
import org.springframework.stereotype.Repository;

import com.unicom.game.center.persistence.db.domain.HotGameTrafficDomain;

@Repository
public class HotGameTrafficDao extends HibernateDao{
	
	public HotGameTrafficDomain getByProductAndChannelAndDate(int productId,int channelId,Date date,boolean flag){
		StringBuffer sb = new StringBuffer();
		sb.append("from HotGameTrafficDomain where productId =");
		sb.append(productId);
		sb.append(" and channelId =");
		sb.append(channelId);
		sb.append(" and dateCreated = ");
		sb.append(date);
		sb.append(" and flag = ");
		sb.append(flag);
		HotGameTrafficDomain hotGameTraffic = (HotGameTrafficDomain)getSession().
			createQuery(sb.toString()).uniqueResult();
		return hotGameTraffic;
	}
	
	public void save(HotGameTrafficDomain hotGameTraffic){
		HotGameTrafficDomain hotGameTrafficDomain = getByProductAndChannelAndDate
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
