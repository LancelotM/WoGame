package com.unicom.game.center.db.dao;

import com.unicom.game.center.db.domain.PackageReportDomain;
import com.unicom.game.center.model.ReportInfo;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: lenovo
 * Date: 14-7-10
 * Time: 下午2:25
 * To change this template use File | Settings | File Templates.
 */
@Component
public class PackageReportDao extends HibernateDao{

    public void save(PackageReportDomain packageReport){
        getSession().save(packageReport);
        getSession().flush();
    }

    public int getPackageInfo(String channelID,String startDate,String endDate,Integer packageCode,Integer receiptCode){
        StringBuilder hql = new StringBuilder();
        hql.append("select count(*) from PackageReportDomain package where package.channelId = '");
        hql.append(channelID);

        if(!startDate.equals(endDate)){
            hql.append("' and package.dateCreated >= '");
            hql.append(startDate);
            hql.append("' and package.dateCreate <= '");
            hql.append(endDate);
        }else {
            hql.append("' and package.dateCreated = '");
            hql.append(startDate);
            hql.append("'");
        }
        if(packageCode != null){
            hql.append(" and package.packageStatus = "+packageCode);
        }
        if(receiptCode != null){
            hql.append(" and package.receiptStatus = "+receiptCode);
        }
        List list = getSession().createQuery(hql.toString()).list();
        getSession().flush();
        String result = null;
        for(Object obj : list){
            result = String.valueOf(obj);
        }

        return Integer.parseInt(result);
    }


}