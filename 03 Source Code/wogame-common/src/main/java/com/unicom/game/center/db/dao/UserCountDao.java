package com.unicom.game.center.db.dao;

import java.util.Date;
import java.util.List;

import org.hibernate.transform.Transformers;
import org.springframework.stereotype.Component;

import com.unicom.game.center.db.domain.UserCountDomain;
import com.unicom.game.center.model.LoginInfo;

@Component
public class UserCountDao extends HibernateDao{
	
	public void save(UserCountDomain userCountDomain){
		getSession().save(userCountDomain);
	}
	
	public void update(UserCountDomain userCountDomain){
		getSession().update(userCountDomain);
	}
	
	public UserCountDomain getByDate(Date date){
		String hql = "from UserCountDomain where dateCreated = '"+date+"'";
		return (UserCountDomain)getSession().createQuery(hql).uniqueResult();
	}
	
	public long fetchNewUserCount(String date, Integer channelId){
		String hql = "select sum(newUserCount) from UserCountDomain where dateCreated = '"+date+"'";
		if(null != channelId){
			hql += " and channelId = " + channelId;
		}
		
		return (Long)getSession().createQuery(hql).list().get(0);
	}
	
	public long fetchTotalUserCount(Integer channelId){
		String hql = "select sum(newUserCount) from UserCountDomain ";
		if(null != channelId){
			hql += " where channelId = " + channelId;
		}
		
		return (Long)getSession().createQuery(hql).list().get(0);
	}	
	
	public List<LoginInfo> fetchLoginInfoByDate(String startDate, String endDate, Integer channelId){
		StringBuffer sb = new StringBuffer();
		sb.append("select sum(uc.newUserCount) as newUser,  sum(uc.oldUserCount) as oldUser,");
		sb.append(" uc.dateCreated as date");
		sb.append(" from UserCountDomain uc");
		sb.append(" where uc.dateCreated >= '");
		sb.append(startDate);
		sb.append("' and uc.dateCreated <= '");
		sb.append(endDate);
		sb.append("'");
		

		if(null != channelId && 0 != channelId.intValue()){
			sb.append(" and uc.channelId = ");
			sb.append(channelId);
		}
		
		sb.append(" group by uc.dateCreated");
		
		sb.append(" order by uc.dateCreated asc");
		
		@SuppressWarnings("unchecked")
		List<LoginInfo> loginInfoList= getSession().createQuery(sb.toString())
										.setResultTransformer(Transformers.aliasToBean(LoginInfo.class))
										.list();		
		return loginInfoList;		
	}

}
