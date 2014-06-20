package com.unicom.game.center.db.dao;

import org.springframework.stereotype.Component;

import com.unicom.game.center.db.domain.PackageInfoDomain;
import com.unicom.game.center.db.domain.PackageInfoKey;

@Component
public class PackageInfoDao extends HibernateDao{
	
	public void save(PackageInfoDomain packageInfo){
		getSession().save(packageInfo);
	}
	
	public PackageInfoDomain getById(String channelId,String productId){
		PackageInfoKey key = new PackageInfoKey();
		key.setChannelId(channelId);
		key.setAppId(productId);
		return (PackageInfoDomain)getSession().load(PackageInfoDomain.class, key);
	}

}
