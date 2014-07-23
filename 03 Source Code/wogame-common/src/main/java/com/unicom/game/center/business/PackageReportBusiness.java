package com.unicom.game.center.business;

import com.unicom.game.center.db.dao.PackageReportDao;
import com.unicom.game.center.db.domain.PackageReportDomain;
import com.unicom.game.center.model.ReportInfo;
import com.unicom.game.center.utils.Constant;
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

    public ReportInfo fetchPackageReport(String channelCode,String start,String end){
        ReportInfo reportInfo = null;
        try{
            int successPackage = dao.getPackageInfo(channelCode,start,end, Constant.PACKAGE_SUCCESS_STATUS,null);
            int packageSum = dao.getPackageInfo(channelCode,start,end,null,null);
            reportInfo = new ReportInfo();
            reportInfo.setFailSum(packageSum - successPackage);
            reportInfo.setPackageSum(packageSum);
            reportInfo.setSucessSum(successPackage);
            return reportInfo;
        }catch(Exception e){
            Logging.logError("Error occur in fetchPackageReport", e);
            e.printStackTrace();
        }
        return null;
    }

    public ReportInfo fetchReceiptInfo(String channelCode,String start,String end){
        ReportInfo reportInfo = null;
        try {  //EXTRACT_NOSYNC_STATUS
            int successPackage = dao.getPackageInfo(channelCode,start,end,Constant.PACKAGE_SUCCESS_STATUS,Constant.EXTRACT_SUCCESS_STATUS);
            int syncing = dao.getPackageInfo(channelCode,start,end,Constant.PACKAGE_SUCCESS_STATUS,Constant.EXTRACT_SYNC_STATUS);
            int noSync = dao.getPackageInfo(channelCode,start,end,Constant.PACKAGE_SUCCESS_STATUS,Constant.EXTRACT_NOSYNC_STATUS);
            int failReceiptPackage = dao.getFailPackageInfo(channelCode,start,end,Constant.PACKAGE_SUCCESS_STATUS);
            reportInfo = new ReportInfo();
            reportInfo.setFailSum(failReceiptPackage);
            reportInfo.setPackageSum(failReceiptPackage+successPackage+noSync+syncing);
            reportInfo.setSucessSum(successPackage);
            reportInfo.setNoSyncSum(noSync);
            reportInfo.setSyncSum(syncing);
        }catch(Exception e){
            Logging.logError("Error occur in fetchReceiptInfo", e);
            e.printStackTrace();
        }
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
