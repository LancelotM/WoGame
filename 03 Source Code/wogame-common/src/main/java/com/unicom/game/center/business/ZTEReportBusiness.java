package com.unicom.game.center.business;

import com.unicom.game.center.db.dao.ZTEReportDao;
import com.unicom.game.center.db.domain.ZTEReportDomain;
import com.unicom.game.center.model.ReportInfo;
import com.unicom.game.center.utils.DateUtils;
import com.unicom.game.center.utils.Logging;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: lenovo
 * Date: 14-7-10
 * Time: 下午3:44
 * To change this template use File | Settings | File Templates.
 */
@Component
public class ZTEReportBusiness {

    @Autowired
    private ZTEReportDao dao;

    public void saveZTEReport(ZTEReportDomain domain){
        dao.save(domain);
    }

    public ReportInfo fetchZTEInfo(String channelId,String start,String end){
        int successPackage = dao.getReportInfo(channelId,start,end,0);
        int packageSum = dao.getReportInfo(channelId,start,end,null);
        ReportInfo reportInfo = new ReportInfo();
        reportInfo.setFailSum(packageSum - successPackage);
        reportInfo.setPackageSum(packageSum);
        reportInfo.setSucessSum(successPackage);
        return reportInfo;
    }

    /**
     *
     * @param contentArr
     */
    public ZTEReportDomain convertZTEReportFromFile(String[] contentArr){
        ZTEReportDomain zteReportDomain = new ZTEReportDomain();
        zteReportDomain.setAppid(contentArr[3]);
        zteReportDomain.setAppname(contentArr[4]);
        zteReportDomain.setChannelId(contentArr[8]);
        zteReportDomain.setOperateResult(Integer.parseInt(contentArr[11]));
        zteReportDomain.setDateCreate(DateUtils.getDayByInterval(new Date(), -1));
        return zteReportDomain;
    }

    /**
     *
     * @param list
     * @param num
     */
    public void saveZTEReportList(List<ZTEReportDomain> list, int num){
        try{
            dao.saveZTEReportDomainList(list, num);
        }catch(Exception ex){
            Logging.logError("Error occur in savePackageReportList.", ex);
        }
    }

}
