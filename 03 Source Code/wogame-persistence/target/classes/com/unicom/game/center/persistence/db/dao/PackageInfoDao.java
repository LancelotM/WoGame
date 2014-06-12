package com.unicom.game.center.persistence.db.dao;

import org.springframework.stereotype.Repository;

import com.unicom.game.center.persistence.db.domain.PackageInfoDomain;
import com.unicom.game.center.persistence.db.domain.PackageInfoKey;

@Repository
public class PackageInfoDao extends HibernateDao{
	
	public void save(PackageInfoDomain packageInfo){
		getSession().save(packageInfo);
	}
	
	public PackageInfoDomain getById(int channelId,int productId){
		PackageInfoKey key = new PackageInfoKey();
		key.setChannelId(channelId);
		key.setProductId(productId);
		return (PackageInfoDomain)getSession().load(PackageInfoDomain.class, key);
	}

}
