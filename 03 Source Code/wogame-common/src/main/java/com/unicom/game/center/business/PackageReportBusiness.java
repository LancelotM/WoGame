package com.unicom.game.center.business;

import com.unicom.game.center.db.dao.PackageReportDao;
import com.unicom.game.center.db.dao.ZTEReportDao;
import com.unicom.game.center.db.domain.ZTEReportDomain;
import com.unicom.game.center.model.ReportInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

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


}