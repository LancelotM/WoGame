package com.unicom.game.center.db.dao;

import com.unicom.game.center.db.domain.PackageInfoDomain;
import com.unicom.game.center.db.domain.PackageInfoKey;
import com.unicom.game.center.model.PackageInfo;
import com.unicom.game.center.utils.Constant;
import org.hibernate.Session;
import org.hibernate.transform.Transformers;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class PackageInfoDao extends HibernateDao{
	
	public void save(PackageInfoDomain packageInfo){
		getSession().save(packageInfo);
		getSession().flush();
	}
	
	public void update(PackageInfoDomain packageInfo){
		getSession().update(packageInfo);
		getSession().flush();
	}	
	
	public PackageInfoDomain getByKey(String channelId,String productId){
		PackageInfoKey key = new PackageInfoKey();
		key.setChannelId(channelId);
		key.setAppId(productId);
		return (PackageInfoDomain)getSession().get(PackageInfoDomain.class, key);
	}
	
	public List<PackageInfo> getDLPackageInfo(String channelId,String productId){
		StringBuffer sb = new StringBuffer();
		sb.append("select p.key.appId as appId, p.key.channelId as channelCode, p.apkOnlineTime as apkOnlineTime");
		sb.append(" from PackageInfoDomain p");
		sb.append(" where p.key.appId=:productId");
		sb.append(" and p.key.channelId in :channelIds");
		
		List<String> channelIds = new ArrayList<String>();
		channelIds.add(channelId);
		channelIds.add(Constant.WOGAME_CHANNEL_CODE);
		
		@SuppressWarnings("unchecked")
		List<PackageInfo> packageInfos = getSession().createQuery(sb.toString())
									.setParameter("productId", productId)
									.setParameterList("channelIds", channelIds)
									.setResultTransformer(Transformers.aliasToBean(PackageInfo.class))
									.list();
		
		return (null != packageInfos) ? packageInfos : null;
		
	}

    public void savePackageInfoDomainList(List<PackageInfoDomain> list, int num) {
        Session session = null;
        if (list != null && list.size() > 0) {
            try {
                session = getSession();
                PackageInfoDomain domain = null;

                for (int i = 0; i < list.size(); i++) {
                    domain = list.get(i);
                    session.saveOrUpdate(domain);
                    if (i % num == 0) {
                        session.flush();
                        session.clear();
                    }
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}
