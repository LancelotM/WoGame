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

    public int getPackageInfo(String channelID,String startDate,String endDate,boolean flag){
        StringBuilder hql = new StringBuilder();
        hql.append("select count(*) from PackageReportDomain package where package.channelId = '");
        hql.append(channelID);
        hql.append("' and package.dateCreated >= '");
        hql.append(startDate);
        if(!startDate.equals(endDate)){
            hql.append("' and package.dateCreate < '");
            hql.append(endDate);
        }
        hql.append("'");
        if(flag){
            hql.append(" and package.packageStatus = 1");
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
