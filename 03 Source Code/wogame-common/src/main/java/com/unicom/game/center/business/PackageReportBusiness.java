package com.unicom.game.center.business;

import com.unicom.game.center.db.dao.PackageReportDao;
import com.unicom.game.center.db.dao.ZTEReportDao;
import com.unicom.game.center.db.domain.PackageReportDomain;
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
 * Time: 下午3:43
 * To change this template use File | Settings | File Templates.
 */
@Component
public class PackageReportBusiness {

    @Autowired
    private PackageReportDao dao;

    public ReportInfo fetchPackageReport(String channelId,String start,String end){
        int successPackage = dao.getPackageInfo(channelId,start,end,0,null);
        int packageSum = dao.getPackageInfo(channelId,start,end,null,null);
        ReportInfo reportInfo = new ReportInfo();
        reportInfo.setFailSum(packageSum - successPackage);
        reportInfo.setPackageSum(packageSum);
        reportInfo.setSucessSum(successPackage);
        return reportInfo;
    }

    public ReportInfo fetchReceiptInfo(String channelId,String start,String end){
        int successPackage = dao.getPackageInfo(channelId,start,end,0,2);
        int failPackage = dao.getPackageInfo(channelId,start,end,0,1);
        ReportInfo reportInfo = new ReportInfo();
        reportInfo.setFailSum(failPackage);
        reportInfo.setPackageSum(failPackage+successPackage);
        reportInfo.setSucessSum(successPackage);
        return reportInfo;
    }

    /**
     *
     * @param contentArr
     */
    public PackageReportDomain convertPackageReportFromFile(String[] contentArr){
        PackageReportDomain packageReportDomain = new PackageReportDomain();
        packageReportDomain.setAppid(contentArr[1]);
        packageReportDomain.setAppname(contentArr[2]);
        packageReportDomain.setChannelId(contentArr[3]);
        packageReportDomain.setPackageStatus(Integer.parseInt(contentArr[4]));
        packageReportDomain.setReceiptStatus(Integer.parseInt(contentArr[5]));
        packageReportDomain.setDateCreated(DateUtils.getDayByInterval(new Date(), -1));
        return packageReportDomain;
    }

    /**
     *
     * @param list
     * @param num
     */
    public void savePackageReportList(List<PackageReportDomain> list, int num){
        try{
            dao.savePackageReportDomainList(list, num);
        }catch(Exception ex){
            Logging.logError("Error occur in savePackageReportList.", ex);
        }
    }

}
