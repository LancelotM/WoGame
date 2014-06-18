package com.unicom.game.center.persistence.db.dao;

import java.sql.Date;
import java.util.List;

import org.springframework.stereotype.Component;

import com.unicom.game.center.persistence.db.domain.DownloadInfoDomain;

@Component
public class DownloadInfoDao extends HibernateDao{
	
	public DownloadInfoDomain getByProductAndChannel(int productId,int channelId,Date date){
		StringBuffer sb = new StringBuffer();
		sb.append("from DownloadInfoDomain where productId =");
		sb.append(productId);
		sb.append(" and channelId =");
		sb.append(channelId);
		sb.append(" and dateCreated = '");
		sb.append(date);
		sb.append("'");
		
		DownloadInfoDomain downloadInfo = (DownloadInfoDomain)getSession().createQuery(sb.toString()).uniqueResult();
		return downloadInfo;
	}
	
	public void save(DownloadInfoDomain downloadInfo){
		DownloadInfoDomain downloadInfoDomain = getByProductAndChannel(downloadInfo.getProductId(),
				downloadInfo.getChannelId(),downloadInfo.getDateCreated());
		
		if(downloadInfoDomain == null){
			getSession().save(downloadInfo);
		}else{
			downloadInfoDomain.setDownloadCount(downloadInfoDomain.getDownloadCount() + 1);
			getSession().update(downloadInfoDomain);
		}
	}
	
	public List getAll(){
		return getSession().createQuery("from DownloadInfoDomain").list();
	}

}
