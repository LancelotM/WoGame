package com.unicom.game.center.db.dao;

import java.util.Date;
import java.util.List;

import org.springframework.stereotype.Component;

import com.unicom.game.center.db.domain.DownloadInfoDomain;

@Component
public class DownloadInfoDao extends HibernateDao<DownloadInfoDomain>{
	
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
	
    public void saveUserCountDomainList(List<DownloadInfoDomain> list, int num) {
//        ApplicationContext ac = new ClassPathXmlApplicationContext("applicationContext_dao.xml");
//        SessionFactory sf = (SessionFactory) ac.getBean("sessionFactory");
//        Session session = sf.openSession();
//        /*Session session = null;*/
//        if (list != null && list.size() > 0) {
//            try {
//                /*session = getSession();*/
//                DownloadInfoDomain domain = null;
//
//                for (int i = 0; i < list.size(); i++) {
//                    domain = list.get(i);
//                    session.saveOrUpdate(domain);
//                    if (i % num == 0) {
//                        session.flush();
//                        session.clear();
//                    }
//                }
//            } catch (Exception e) {
//                e.printStackTrace();
//            }
//        }
    	saveDomainList(list, num);
    }	
}
