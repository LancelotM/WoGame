package com.unicom.game.center.loganalyser.imp;

import com.jcraft.jsch.ChannelSftp;
import com.unicom.game.center.business.PackageReportBusiness;
import com.unicom.game.center.db.domain.PackageReportDomain;
import com.unicom.game.center.loganalyser.ILogAnalyser;
import com.unicom.game.center.utils.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: claire_chang
 * Date: 14-7-14
 * Time: 上午9:53
 * To change this template use File | Settings | File Templates.
 */
public class PackageReportAnalyser implements ILogAnalyser {

    @Autowired
    private PackageReportBusiness packageReportBusiness;

    @Value("#{properties['packagesReport.file.path']}")
    private String responseFilePath;

    @Value("#{properties['latest.handle.packagesReport.file']}")
    private String latestHandleFile;

    @Override
    public void doLogAnalyse() throws Exception {
        // TODO Auto-generated method stub

    }

    @Override
    public void doPackageInfoDomainsSave() throws Exception {
        // TODO Auto-generated method stub

    }

    @Override
    public void doDownloadCountDomainsSave() throws Exception{
        // TODO Auto-generated method stub
    }

    @Override
    public void doExtractReportDomainsSave() throws Exception{
        // TODO Auto-generated method stub
    }

    @Override
    public void doPackageReportDomainsSave() throws Exception {
        Logging.logDebug("----- doPackageReportDomainsSave start -----");
        try{
            String dateBefore = null;
            String tempString = null;
            Date today = new Date();
            Date yesterday = DateUtils.getDayByInterval(today,-1);
            String fileDate = DateUtils.formatDateToString(yesterday,"yyyyMMdd");
            String currentFileName = "packagesys_report_"+fileDate+".log";
            List<String> currentFileList = FileUtils.readFileByRow(latestHandleFile);
            if(currentFileList.size()>0){
                dateBefore = currentFileList.get(0);
            }else{
                dateBefore = new SimpleDateFormat("yyyyMMddHHmmss").format(DateUtils.getDayByInterval(today,-2));
            }
            String dateNow = new SimpleDateFormat("yyyyMMddHHmmss").format(yesterday);
            int compareDateNum = DateUtils.compareDate(dateBefore,dateNow);
            switch (compareDateNum){
                case -1:
                    FileUtils.writeFileOverWrite(latestHandleFile,dateNow);
                    File file = new File(responseFilePath+"/"+currentFileName);
                    if(!file.exists()){
                        file.createNewFile();
                    }
                    BufferedReader reader = new BufferedReader(new InputStreamReader(new FileInputStream(file), "GB2312"));
                    List<PackageReportDomain> packageReportDomains = new ArrayList<PackageReportDomain>();
                    while ((tempString = reader.readLine()) != null){
                        String[] contentArr = Utility.splitString(tempString, Constant.RESPONSE_FIEL_SEPARATOR);
                        PackageReportDomain domain = packageReportBusiness.convertPackageReportFromFile(contentArr);
                        packageReportDomains.add(domain);
                    }
                    packageReportBusiness.savePackageReportList(packageReportDomains, Constant.HIBERNATE_FLUSH_NUM);
                    break;
                case 1:
                case 0:
                    break;
            }
        }catch(Exception e){
            Logging.logError("Error occurs in doExtractReportDomainsSave ", e);
        }
        Logging.logDebug("----- doPackageReportDomainsSave end -----");
    }


}
