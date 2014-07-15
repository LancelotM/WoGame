package com.unicom.game.center.db.dao;

import com.unicom.game.center.db.domain.ZTEReportDomain;
import com.unicom.game.center.model.ReportInfo;
import com.unicom.game.center.utils.Utility;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: lenovo
 * Date: 14-7-10
 * Time: 下午3:27
 * To change this template use File | Settings | File Templates.
 */
@Component
public class ZTEReportDao extends HibernateDao{

    public void save(ZTEReportDomain zteReportDomain){
        getSession().save(zteReportDomain);
        getSession().flush();
    }

    public int getReportInfo(String channelId,String startDate,String endDate,Integer isSuccess){
        StringBuilder hql = new StringBuilder();
        hql.append("select count(*) from ZTEReportDomain zteReport where zteReport.channelId = '");
        hql.append(channelId);
        if(!Utility.isEmpty(startDate)&&!Utility.isEmpty(endDate)){
            if(!startDate.equals(endDate)){
                hql.append("' and zteReport.dateCreate >= '");
                hql.append(startDate);
                hql.append("' and zteReport.dateCreate <= '");
                hql.append(endDate);
            }else {
                hql.append("' and zteReport.dateCreate = '");
                hql.append(startDate);
            }
        }
        hql.append("'");
        if(isSuccess != null){
            hql.append(" and zteReport.operateResult = "+isSuccess);
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
