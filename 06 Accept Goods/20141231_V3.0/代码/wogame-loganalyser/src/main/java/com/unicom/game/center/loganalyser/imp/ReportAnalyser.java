package com.unicom.game.center.loganalyser.imp;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import com.unicom.game.center.business.PackageReportBusiness;
import com.unicom.game.center.business.ZTEReportBusiness;
import com.unicom.game.center.db.domain.PackageReportDomain;
import com.unicom.game.center.db.domain.ZTEReportDomain;
import com.unicom.game.center.loganalyser.ILogAnalyser;
import com.unicom.game.center.utils.Constant;
import com.unicom.game.center.utils.DateUtils;
import com.unicom.game.center.utils.FTPHelper;
import com.unicom.game.center.utils.FileUtils;
import com.unicom.game.center.utils.Logging;
import com.unicom.game.center.utils.Utility;

/**
 * @author Alex Yin
 * 
 * @Date 2014-7-23
 */
@Component
public class ReportAnalyser implements ILogAnalyser {

    @Autowired
    private ZTEReportBusiness zteReportBusiness;
        
    @Autowired
    private PackageReportBusiness packageReportBusiness;    
    
    @Autowired
    private FTPHelper ftpHelper;     

    @Value("#{properties['extractReport.file.path']}")
    private String extractFilePath;

    @Value("#{properties['latest.handle.extractReport.file']}")
    private String extractHandleFile;

    @Value("#{properties['packagesReport.file.path']}")
    private String packagesFilePath;

    @Value("#{properties['latest.handle.packagesReport.file']}")
    private String packagesHandleFile;    

    @Override
    public void doLogAnalyse() throws Exception{
    	
        Logging.logDebug("----- doReportDomainsSave start -----");
        try{
        	doPackageReportDomainsSave();
        	doExtractReportDomainsSave();
        }catch(Exception e){
            Logging.logError("Error occurs in doReportDomainsSave ", e);
        }
        
        Logging.logDebug("----- doReportDomainsSave end -----");    	
    }

    private void doExtractReportDomainsSave() throws Exception {
        Logging.logDebug("----- doExtractReportDomainsSave start -----");
  		System.out.println("=====doExtractReportDomainsSave start========");

        String currentFileName = "";        

        try{
            List<String> currentFileNameList = FileUtils.readFileByRow(extractHandleFile);
            if (currentFileNameList.size() > 0) {
                currentFileName = currentFileNameList.get(0);
            }
  			
            List<String> fileList = ftpHelper.getFileList(extractFilePath);
            System.out.println("FTP Files size :" + fileList.size());
            fileList = Utility.getSubStringList(fileList, currentFileName);
            
            
            for (String fileName : fileList) {
            	System.out.println(fileName);
            	String strDate = (fileName.split("\\."))[1];
            	Date date = DateUtils.stringToDate(strDate, "yyyy-MM-dd");            	
            	List<ZTEReportDomain> zteReportDomains = new ArrayList<ZTEReportDomain>();
                List<String> contentList = ftpHelper.readRemoteFileByRow(extractFilePath, fileName);

                for (String content : contentList) {
                    String[] contentArr = Utility.splitString(content, Constant.RESPONSE_FIEL_SEPARATOR);
                    ZTEReportDomain zteReportDomain = zteReportBusiness.convertZTEReportFromFile(contentArr, date);
                    zteReportDomains.add(zteReportDomain);
                }

                zteReportBusiness.saveZTEReportList(zteReportDomains, Constant.HIBERNATE_FLUSH_NUM);
                currentFileName = fileName;
            }

        }catch(Exception e){
            Logging.logError("Error occurs in doExtractReportDomainsSave ", e);
  			e.printStackTrace();
  		} finally{
  			FileUtils.writeFileOverWrite(extractHandleFile, currentFileName);
  			ftpHelper.disConnectFtpServer();
  		}
  		
  		System.out.println("=====doExtractReportDomainsSave end========");
  		Logging.logDebug("----- doExtractReportDomainsSave end -----");
    }
    
    private void doPackageReportDomainsSave() throws Exception {
        Logging.logDebug("----- doPackageReportDomainsSave start -----");
  		System.out.println("=====doPackageReportDomainsSave start========");

        String currentFileName = "";        

        try{
            List<String> currentFileNameList = FileUtils.readFileByRow(packagesHandleFile);
            if (currentFileNameList.size() > 0) {
                currentFileName = currentFileNameList.get(0);
            }
  			
            List<String> fileList = ftpHelper.getFileList(packagesFilePath);
            System.out.println("FTP Files size :" + fileList.size());
            fileList = Utility.getSubStringList(fileList, currentFileName);
            
            
            for (String fileName : fileList) {
            	System.out.println(fileName);
            	String strDate = (fileName.split("\\."))[1];
            	Date date = DateUtils.stringToDate(strDate, "yyyy-MM-dd");
            	
            	List<PackageReportDomain> packageReportDomains = new ArrayList<PackageReportDomain>();
                List<String> contentList = ftpHelper.readRemoteFileByRow(packagesFilePath, fileName);

                for (String content : contentList) {
                    String[] contentArr = Utility.splitString(content, Constant.RESPONSE_FIEL_SEPARATOR);
                    PackageReportDomain domain = packageReportBusiness.convertPackageReportFromFile(contentArr, date);
                    packageReportDomains.add(domain);
                }

                packageReportBusiness.savePackageReportList(packageReportDomains, Constant.HIBERNATE_FLUSH_NUM);
                currentFileName = fileName;
            }

        }catch(Exception e){
            Logging.logError("Error occurs in doPackageReportDomainsSave ", e);
  			e.printStackTrace();
  		} finally{
  			FileUtils.writeFileOverWrite(packagesHandleFile, currentFileName);
  			ftpHelper.disConnectFtpServer();
  		}
  		
  		System.out.println("=====doPackageReportDomainsSave end========");                        
        Logging.logDebug("----- doPackageReportDomainsSave end -----");
    }    
}
