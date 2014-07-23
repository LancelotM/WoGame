package com.unicom.game.center.loganalyser.imp;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import com.unicom.game.center.business.PackageInfoBusiness;
import com.unicom.game.center.business.ZTEReportBusiness;
import com.unicom.game.center.db.domain.PackageInfoDomain;
import com.unicom.game.center.db.domain.ZTEReportDomain;
import com.unicom.game.center.loganalyser.ILogAnalyser;
import com.unicom.game.center.utils.Constant;
import com.unicom.game.center.utils.DateUtils;
import com.unicom.game.center.utils.FileUtils;
import com.unicom.game.center.utils.Logging;
import com.unicom.game.center.utils.Utility;

/**
 * Created with IntelliJ IDEA.
 * User: claire_chang
 * Date: 14-7-14
 * Time: 下午2:09
 * To change this template use File | Settings | File Templates.
 */
@Component
public class ExtractReportAnalyser implements ILogAnalyser {

    @Autowired
    private ZTEReportBusiness zteReportBusiness;

    @Autowired
    private PackageInfoBusiness packageInfoBusiness;

    @Value("#{properties['extractReport.file.path']}")
    private String responseFilePath;

    @Value("#{properties['latest.handle.extractReport.file']}")
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
    public void doPackageReportDomainsSave() throws Exception{
        // TODO Auto-generated method stub
    }

    @Override
    public void doExtractReportDomainsSave() throws Exception {
        Logging.logDebug("----- doExtractReportDomainsSave start -----");

        try{
            String dateBefore = null;
            Date today = new Date();
            Date yesterday = DateUtils.getDayByInterval(today,-1);
            String fileDate = DateUtils.formatDateToString(yesterday,"yyyyMMdd");
            String currentFileName = "extract_report_"+fileDate+".log";
            List<String> currentFileList = FileUtils.readFileByRow(latestHandleFile);
            if(currentFileList.size()>0){
                dateBefore = currentFileList.get(0);
            }else{
                dateBefore = new SimpleDateFormat("yyyy-MM-dd").format(DateUtils.getDayByInterval(today,-2));
            }
            String dateNow = new SimpleDateFormat("yyyy-MM-dd").format(yesterday);
            int compareDateNum = DateUtils.compareLogDate(dateBefore,dateNow);
            switch (compareDateNum){
                case -1:
                    FileUtils.writeFileOverWrite(latestHandleFile,dateNow);
                    File file = new File(responseFilePath+"/"+currentFileName);
                    if(!file.exists()){
                        file.createNewFile();
                    }
                    BufferedReader reader = new BufferedReader(new InputStreamReader(new FileInputStream(file), "GB2312"));
                    String tempString = null;
                    List<ZTEReportDomain> zteReportDomains = new ArrayList<ZTEReportDomain>();
                    List<PackageInfoDomain> packageInfoDomains = new ArrayList<PackageInfoDomain>();
                    while ((tempString = reader.readLine()) != null){
                        String[] contentArr = Utility.splitString(tempString, Constant.RESPONSE_FIEL_SEPARATOR);
                        if(Integer.parseInt(contentArr[8].trim()) != 0){
                            ZTEReportDomain zteReportDomain = zteReportBusiness.convertZTEReportFromFile(contentArr);
                            zteReportDomains.add(zteReportDomain);
                            PackageInfoDomain packageInfoDomain = packageInfoBusiness.convertPackageInfoFromFile(contentArr);
                            packageInfoDomains.add(packageInfoDomain);
                        }
                    }
                    zteReportBusiness.saveZTEReportList(zteReportDomains, Constant.HIBERNATE_FLUSH_NUM);
                    packageInfoBusiness.savePackageInfoList(packageInfoDomains, Constant.HIBERNATE_FLUSH_NUM);
                    break;
                case 1:
                case 0:
                    break;
            }
        }catch(Exception e){
            Logging.logError("Error occurs in doExtractReportDomainsSave ", e);
        }
        Logging.logDebug("----- doExtractReportDomainsSave end -----");
    }
}
