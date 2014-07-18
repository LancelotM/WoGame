package com.unicom.game.center.loganalyser.imp;

import com.unicom.game.center.business.DownLoadInfoBusiness;
import com.unicom.game.center.db.domain.DownloadInfoDomain;
import com.unicom.game.center.log.model.DownLoadInfo;
import com.unicom.game.center.loganalyser.ILogAnalyser;
import com.unicom.game.center.utils.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;

import java.io.*;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * Created with IntelliJ IDEA.
 * User: claire_chang
 * Date: 14-7-14
 * Time: 下午1:24
 * To change this template use File | Settings | File Templates.
 */
public class DownloadCountAnalyser implements ILogAnalyser {

    @Autowired
    private DownLoadInfoBusiness downloadInfoBusiness;

    @Value("#{properties['downloadCount.file.path']}")
    private String responseFilePath;

    @Value("#{properties['latest.handle.downloadCount.file']}")
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
    public void doPackageReportDomainsSave() throws Exception {
        // TODO Auto-generated method stub
    }

    @Override
    public void doExtractReportDomainsSave() throws Exception{
        // TODO Auto-generated method stub
    }

    @Override
    public void doDownloadCountDomainsSave() throws Exception {
        Logging.logDebug("----- doDownloadCountDomainsSave start -----");
        try{
            String dateBefore = null;
            Date today = new Date();
            Date yesterday = DateUtils.getDayByInterval(today,-1);
            String fileDate = DateUtils.formatDateToString(yesterday,"yyyyMMdd");
            String currentFileName = "download_report_"+fileDate+".log";
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
                    Map<String,DownLoadInfo>downLoadInfoMap = new HashMap<String, DownLoadInfo>();
                    DownLoadInfo downLoadInfo = null;
                    BufferedReader reader = new BufferedReader(new FileReader(file));
                    String tempString = null;
                    while ((tempString = reader.readLine()) != null){
                        String[] contentArr = Utility.splitString(tempString, Constant.RESPONSE_FIEL_SEPARATOR);
                        if(!downLoadInfoMap.containsKey(contentArr[1])){
                            downLoadInfo = new DownLoadInfo();
                            downLoadInfo.setDownload_count(Integer.parseInt(contentArr[2]));
                        }else{
                            downLoadInfo = downLoadInfoMap.get(contentArr[1]);
                            downLoadInfo.setDownload_count(downLoadInfo.getDownload_count() + Integer.parseInt(contentArr[2]));
                        }
                        downLoadInfo.setChannel_id(Integer.parseInt(contentArr[1]));
                        downLoadInfo.setProduct_id(contentArr[0]);
                        downLoadInfo.setDateCreated(DateUtils.getDayByInterval(new Date(), -1));
                        downLoadInfoMap.put(contentArr[1], downLoadInfo);
                    }
                    downloadInfoBusiness.typeConversion(downLoadInfoMap);
                    downLoadInfoMap.clear();
                    break;
                case 1:
                case 0:
                    break;
            }
        }catch(Exception e){
            Logging.logError("Error occurs in doExtractReportDomainsSave ", e);
        }
        Logging.logDebug("----- doDownloadCountDomainsSave end -----");
    }

}


