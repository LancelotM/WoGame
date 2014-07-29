package com.unicom.game.center.db.dao;

import java.util.List;

import com.unicom.game.center.utils.Constant;
import org.springframework.stereotype.Component;

import com.unicom.game.center.db.domain.PackageReportDomain;
import com.unicom.game.center.utils.Utility;

/**
 * Created with IntelliJ IDEA.
 * User: lenovo
 * Date: 14-7-10
 * Time: 下午2:25
 * To change this template use File | Settings | File Templates.
 */
@Component
public class PackageReportDao extends HibernateDao<PackageReportDomain>{

    public void save(PackageReportDomain packageReport){
        getSession().save(packageReport);
        getSession().flush();
    }

    public int getPackageInfo(String channelID,String startDate,String endDate,Integer packageCode,Integer receiptCode){
        StringBuilder hql = new StringBuilder();
        hql.append("select count(*) from PackageReportDomain package where 1 = 1");
        if(!Utility.isEmpty(channelID)){
            hql.append(" and package.channelId = '");
            hql.append(channelID);
            hql.append("'");
        }
        if(!Utility.isEmpty(startDate)&&!Utility.isEmpty(endDate)){
            if(!startDate.equals(endDate)){
                hql.append(" and package.dateCreated >= '");
                hql.append(startDate);
                hql.append("' and package.dateCreated <= '");
                hql.append(endDate);
            }else {
                hql.append(" and package.dateCreated = '");
                hql.append(startDate);
            }
            hql.append(" '");
        }
        if(packageCode != null){
            hql.append(" and package.packageStatus = "+packageCode);
        }
        if(receiptCode != null){
            if(receiptCode != Constant.EXTRACT_NOSYNC_STATUS){
                hql.append(" and package.receiptStatus = "+receiptCode);
            }else {
                hql.append(" and package.receiptStatus is null");
            }

        }
        List list = getSession().createQuery(hql.toString()).list();
        getSession().flush();
        String result = (list != null && list.size() >0)?String.valueOf(list.get(0)):"0";
        return Integer.parseInt(result);
    }

    public int getFailPackageInfo(String channelID,String startDate,String endDate,Integer packageCode){
        StringBuilder hql = new StringBuilder();
        hql.append("select count(*) from PackageReportDomain package where 1 = 1");
        if(!Utility.isEmpty(channelID)){
            hql.append(" and package.channelId = '");
            hql.append(channelID);
            hql.append("'");
        }
        if(!Utility.isEmpty(startDate)&&!Utility.isEmpty(endDate)){
            if(!startDate.equals(endDate)){
                hql.append(" and package.dateCreated >= '");
                hql.append(startDate);
                hql.append("' and package.dateCreated <= '");
                hql.append(endDate);
            }else {
                hql.append(" and package.dateCreated = '");
                hql.append(startDate);
            }
            hql.append(" '");
        }
        if(packageCode != null){
            hql.append(" and package.packageStatus = "+packageCode);
        }
        hql.append(" and package.receiptStatus is not null ");
        hql.append(" and package.receiptStatus <> ");
        hql.append(Constant.EXTRACT_SUCCESS_STATUS);
        hql.append(" and package.receiptStatus <> ");
        hql.append(Constant.EXTRACT_SYNC_STATUS);
        List list = getSession().createQuery(hql.toString()).list();
        getSession().flush();
        String result = (list != null && list.size() >0)?String.valueOf(list.get(0)):"0";
        return Integer.parseInt(result);
    }

    public void savePackageReportDomainList(List<PackageReportDomain> list, int num) {
        saveDomainList(list, num);
    }
}
