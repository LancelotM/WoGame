package com.unicom.game.center.db.dao;

import java.util.Date;

import org.springframework.stereotype.Component;

import com.unicom.game.center.db.domain.DownloadInfoDomain;

@Component
public class DownloadInfoDao extends HibernateDao{
	
	public void save(DownloadInfoDomain downloadInfo){
		getSession().save(downloadInfo);
		getSession().flush();
	}

	public void update(DownloadInfoDomain downloadInfo){
		getSession().update(downloadInfo);
		getSession().flush();
	}

	public DownloadInfoDomain getByProductAndChannel(String productId,int channelId,Date date){
		StringBuffer sb = new StringBuffer();
		sb.append("from DownloadInfoDomain where productId ='");
		sb.append(productId);
		sb.append("' and channelId =");
		sb.append(channelId);
		sb.append(" and dateCreated = '");
		sb.append(date);
		sb.append("'");
		
		DownloadInfoDomain downloadInfo = (DownloadInfoDomain)getSession().createQuery(sb.toString()).uniqueResult();
		
		return downloadInfo;
	}	
}
