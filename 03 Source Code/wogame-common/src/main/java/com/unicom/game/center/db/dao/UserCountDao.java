package com.unicom.game.center.db.dao;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.hibernate.Query;
import org.hibernate.SQLQuery;
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
		sb.append(" DATE_FORMAT(uc.dateCreated, '%m-%d') as date");
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
		
		Query query = getSession().createQuery(sb.toString());
		@SuppressWarnings("unchecked")
		List<Object[]> list = query.list();
		
		List<LoginInfo> loginInfoList= convertToLoginInfo(list);
		
		return loginInfoList;		
	}

	public List<LoginInfo> fetchLoginInfoByMonth(String startDate, String endDate, Integer channelId){
		StringBuffer sb = new StringBuffer();
		sb.append(" select sum(uc.new_user_count) as newUser,  sum(uc.old_user_count) as oldUser,");
		sb.append(" DATE_FORMAT(uc.date_created, '%Y-%m') as date");
		sb.append(" from user_count uc");
		sb.append(" where uc.date_created >= '");
		sb.append(startDate);
		sb.append("' and uc.date_created <= '");
		sb.append(endDate);
		sb.append("'");		

		if(null != channelId && 0 != channelId.intValue()){
			sb.append(" and uc.channel_id = ");
			sb.append(channelId);
		}
		
		sb.append(" group by date");
		
		sb.append(" order by date asc");
		
		SQLQuery sqlQuery = getSession().createSQLQuery(sb.toString());
		
		@SuppressWarnings("unchecked")
		List<Object[]> list = sqlQuery.list();
		
		List<LoginInfo> loginInfoList= convertToLoginInfo(list);
			
		return loginInfoList;
	}
	
	private List<LoginInfo> convertToLoginInfo(List<Object[]> list){
		List<LoginInfo> loginInfoList = null;
		if(null != list && list.size() > 0){
			loginInfoList = new ArrayList<LoginInfo>();
			for(Object[] object : list){
				LoginInfo info = new LoginInfo();
				info.setNewUser(String.valueOf(object[0]));
				info.setOldUser(String.valueOf(object[1]));
				info.setDate(String.valueOf(object[2]));
				loginInfoList.add(info);
			}
		}
		
		return loginInfoList;
	}
	
}
