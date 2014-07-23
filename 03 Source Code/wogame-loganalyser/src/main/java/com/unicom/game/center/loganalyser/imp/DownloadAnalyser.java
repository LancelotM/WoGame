package com.unicom.game.center.loganalyser.imp;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import com.unicom.game.center.business.DownLoadInfoBusiness;
import com.unicom.game.center.log.model.DownLoadInfo;
import com.unicom.game.center.loganalyser.ILogAnalyser;
import com.unicom.game.center.utils.Constant;
import com.unicom.game.center.utils.DateUtils;
import com.unicom.game.center.utils.FileUtils;
import com.unicom.game.center.utils.Logging;
import com.unicom.game.center.utils.Utility;

/**
 * @author Alex Yin
 * 
 * @Date 2014-7-23
 */
@Component
public class DownloadAnalyser implements ILogAnalyser {

    @Autowired
    private DownLoadInfoBusiness downloadInfoBusiness;

    @Value("#{properties['downloadCount.file.path']}")
    private String responseFilePath;

    @Value("#{properties['latest.handle.downloadCount.file']}")
    private String downloadHandleFile;


    @Override
    public void doLogAnalyse() throws Exception {

    }

    @Override
    public void doPackageInfoDomainsSave() throws Exception {

    }

    @Override
    public void doReportDomainsSave() throws Exception {

    }

    @Override
    public void doDownloadCountDomainsSave() throws Exception {
        Logging.logDebug("----- doDownloadCountDomainsSave start -----");
        System.out.println("=====doDownloadCountDomainsSave start========");
        
        BufferedReader reader = null;
        String currentFileName = "";
        try{            
            List<String> currentFileList = FileUtils.readFileByRow(downloadHandleFile);
            if(currentFileList.size()>0){
            	currentFileName = currentFileList.get(0);
            }
            
            List<String> fileList = FileUtils.getFileList(responseFilePath);
            System.out.println("Files size :" + fileList.size());
            fileList = Utility.getSubStringList(fileList, currentFileName);
            
            Date yesterday = DateUtils.getDayByInterval(new Date(), -1);
            
            for (String fileName : fileList) {
            	System.out.println(fileName);
                Map<String,DownLoadInfo>downLoadInfoMap = new HashMap<String, DownLoadInfo>();
                DownLoadInfo downLoadInfo = null;
                File file = new File(responseFilePath+"/"+fileName);
                reader = new BufferedReader(new UnicodeReader(new FileInputStream(file), "UTF-8"));
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
                    downLoadInfo.setChannel_code(contentArr[1]);
                    downLoadInfo.setProduct_id(contentArr[0]);
                    downLoadInfo.setDateCreated(yesterday);
                    downLoadInfoMap.put(contentArr[1], downLoadInfo);

                }
                downloadInfoBusiness.typeConversion(downLoadInfoMap);
                downLoadInfoMap.clear();
                currentFileName = fileName;
            }
        }catch(Exception e){
            Logging.logError("Error occurs in doExtractReportDomainsSave ", e);
            e.printStackTrace();
        }finally{
        	FileUtils.writeFileOverWrite(downloadHandleFile, currentFileName);
        	if(null != reader){
        		reader.close();
        	}
        }
        
        System.out.println("=====doDownloadCountDomainsSave end========");
        Logging.logDebug("----- doDownloadCountDomainsSave end -----");
    }
}
