package com.unicom.game.center.loganalyser.imp;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import com.unicom.game.center.business.PackageInfoBusiness;
import com.unicom.game.center.business.SyncChannelClient;
import com.unicom.game.center.db.domain.PackageInfoDomain;
import com.unicom.game.center.loganalyser.ILogAnalyser;
import com.unicom.game.center.utils.Constant;
import com.unicom.game.center.utils.FTPHelper;
import com.unicom.game.center.utils.FileUtils;
import com.unicom.game.center.utils.Logging;
import com.unicom.game.center.utils.Utility;

/**
 * @author Alex Yin
 * 
 * @Date 2014-7-11
 */
@Component
public class PackageInfoAnalyser implements ILogAnalyser{
	
    @Autowired
    private PackageInfoBusiness packageInfoBusiness;
    
    @Autowired
    private SyncChannelClient syncChannelClient;
   
    @Autowired
    private FTPHelper ftpHelper;  
    
//    @Autowired
//    private SFTPHelper sftpHelper;    
    
	@Value("#{properties['response.file.path']}")
	private String responseFilePath;
	
	@Value("#{properties['latest.handdle.file']}")
	private String packageHandleFile;	

	@Override
	public void doLogAnalyse() throws Exception {
  		Logging.logDebug("----- doPackageInfoDomainsSave start -----");
  		
  		try{
  			System.out.println("=====syncFailureChannels========");
  			syncChannelClient.syncFailureChannels();
  		}catch(Exception e){
  			Logging.logError("Error occurs in syncFailureChannels ", e);
  		}
  		
  		System.out.println("=====doPackageInfoDomainsSave start========");

        String currentFileName = "";

  		try {
            List<String> currentFileNameList = FileUtils.readFileByRow(packageHandleFile);
            if (currentFileNameList.size() > 0) {
                currentFileName = currentFileNameList.get(0);
            }

  			
            List<String> fileList = ftpHelper.getFileList(responseFilePath);
            System.out.println("FTP Files size :" + fileList.size());
            fileList = Utility.getSubStringList(fileList, currentFileName);

            for (String fileName : fileList) {
            	System.out.println(fileName);
                List<PackageInfoDomain> packageInfoDomains = new ArrayList<PackageInfoDomain>();
                List<String> contentList = ftpHelper.readRemoteFileByRow(responseFilePath, fileName);

                for (String content : contentList) {
                    String[] contentArr = Utility.splitString(content, Constant.RESPONSE_FIEL_SEPARATOR);
                    if(null != contentArr && contentArr.length > 12 && "0".equals(contentArr[11])){
                        PackageInfoDomain domain = packageInfoBusiness.convertPackageInfoFromFile(contentArr);
                        packageInfoDomains.add(domain);                    	
                    }
                }

                packageInfoBusiness.savePackageInfoList(packageInfoDomains, Constant.HIBERNATE_FLUSH_NUM);
                currentFileName = fileName;
            }
  		} catch(Exception e){           
  			Logging.logError("Error occurs in doPackageInfoDomainsSave ", e);
  			e.printStackTrace();
  		} finally{
  			 FileUtils.writeFileOverWrite(packageHandleFile, currentFileName);
  			ftpHelper.disConnectFtpServer();
  		}
  		
  		System.out.println("=====doPackageInfoDomainsSave end========");
  		Logging.logDebug("----- doPackageInfoDomainsSave end -----");
  	}
	
/**	
    @Override
    public void doPackageInfoDomainsSave() throws Exception {
        Logging.logDebug("----- doPackageInfoDomainsSave start -----");

        String currentFileName = "";
        ChannelSftp sftp = null;
        try {
            List<String> currentFileNameList = FileUtils.readFileByRow(latestHanddleFile);
            if (currentFileNameList.size() > 0) {
                currentFileName = currentFileNameList.get(0);
            }

            List<String> fileList = sftpHelper.getFileList(responseFilePath);
            fileList = Utility.getSubStringList(fileList, currentFileName);

            sftp = sftpHelper.connectServer();
            for (String fileName : fileList) {
                List<PackageInfoDomain> packageInfoDomains = new ArrayList<PackageInfoDomain>();
                List<String> contentList = sftpHelper.readRemoteFileByRow(responseFilePath, fileName, sftp);

                for (String content : contentList) {
                    String[] contentArr = Utility.splitString(content, Constant.RESPONSE_FIEL_SEPARATOR);
                    PackageInfoDomain domain = packageInfoBusiness.convertPackageInfoFromFile(contentArr);
                    packageInfoDomains.add(domain);
                }

                packageInfoBusiness.savePackageInfoList(packageInfoDomains, Constant.HIBERNATE_FLUSH_NUM);
                currentFileName = fileName;
            }
        } catch(Exception e){
            Logging.logError("Error occurs in doPackageInfoDomainsSave ", e);
        } finally{
            FileUtils.writeFileOverWrite(latestHanddleFile, currentFileName);
            if (sftp != null) {
                sftpHelper.closeChannel(sftp.getSession(), sftp);
            }
        }
        Logging.logDebug("----- doPackageInfoDomainsSave end -----");
    }	
**/	
}
