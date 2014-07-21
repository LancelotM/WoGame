package com.unicom.game.center.db.dao;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Component;

import com.unicom.game.center.db.domain.DownloadInfoDomain;
import com.unicom.game.center.log.model.DownloadDiaplayModel;
import com.unicom.game.center.utils.Utility;

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

	public List<DownloadDiaplayModel> getByProductOrChaOrDate(Integer channelId,String startDate,String endDate){
		StringBuffer sb = new StringBuffer();
		sb.append("select game.product_name,downInfoDomain.download_count from ");
        sb.append(" download_info as downInfoDomain inner join product as game on downInfoDomain.product_id = game.product_id where 1 = 1 ");
//        if(!Utility.isEmpty(productId)){
//            sb.append("and game.product_name like '");
//            sb.append(productId);
//            sb.append("'");
//        }
        if(channelId != null){
            sb.append("and downInfoDomain.channel_id = ");
            sb.append(channelId);
        }
        if(!Utility.isEmpty(startDate) && !Utility.isEmpty(endDate)){
           if(startDate.equals(endDate)){
               sb.append(" and downInfoDomain.date_created = '");
               sb.append(startDate);
               sb.append("'");
           }else {
               sb.append(" and downInfoDomain.date_created >= '");
               sb.append(startDate);
               sb.append("' and downInfoDomain.date_created <= '");
               sb.append(endDate);
               sb.append("'");
           }
        }
        sb.append(" order by downInfoDomain.download_count desc");
        List<Object[]> downloadInfos = getSession().createSQLQuery(sb.toString()).list();
		getSession().flush();
        List<DownloadDiaplayModel>  downloadDiaplayModels = new ArrayList<DownloadDiaplayModel>();
        DownloadDiaplayModel model = null;
        for(Object[] objects : downloadInfos){
            model = new DownloadDiaplayModel();
            model.setProductName(String.valueOf(objects[0]));
            model.setDownloadCount(String.valueOf(objects[1]));
            downloadDiaplayModels.add(model);
        }
		return downloadDiaplayModels;
	}
	
    public void saveDownloadCountDomainList(List<DownloadInfoDomain> list, int num) {
    	saveDomainList(list, num);
    }	
}
