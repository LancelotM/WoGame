package com.unicom.game.center.db.dao;

import java.util.Date;
import java.util.List;

import com.unicom.game.center.log.model.DownloadDiaplayModel;
import com.unicom.game.center.utils.Utility;
import org.hibernate.Query;
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

	public List<DownloadDiaplayModel> getByProductOrChaOrDate(String productId,Integer channelId,String startDate,String endDate,int page){
		StringBuffer sb = new StringBuffer();
		sb.append("select downInfoDomain.product.productName,downInfoDomain.downloadCount from DownloadInfoDomain downInfoDomain where 1 = 1 ");
        if(!Utility.isEmpty(productId)){
            sb.append("and downInfoDomain.productId = '");
            sb.append(productId);
            sb.append("'");
        }
        if(channelId != null){
            sb.append("and downInfoDomain.channelId = '");
            sb.append(channelId);
        }
        if(!Utility.isEmpty(startDate) && !Utility.isEmpty(endDate)){
           if(startDate.equals(endDate)){
               sb.append(" and downInfoDomain.dateCreated = '");
               sb.append(startDate);
               sb.append("'");
           }else {
               sb.append(" and downInfoDomain.dateCreated >= '");
               sb.append(startDate);
               sb.append("' and downInfoDomain.dateCreated <= '");
               sb.append(endDate);
               sb.append("'");
           }
        }

        int rowsPerPage = 10;
        int start = (page - 1)*rowsPerPage+1;
        List<DownloadDiaplayModel> downloadInfos = getSession().createQuery(sb.toString()).setFirstResult(start).setMaxResults(rowsPerPage).list();
		
		return downloadInfos;
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
