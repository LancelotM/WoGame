package com.unicom.game.center.loganalyser.imp;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import com.unicom.game.center.business.PackageInfoBusiness;
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
    private FTPHelper ftpHelper;
    
	@Value("#{properties['response.file.path']}")
	private String responseFilePath;
	
	@Value("#{properties['latest.handdle.file']}")
	private String latestHanddleFile;	

	@Override
	public void doLogAnalyse() throws Exception {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void doPackageInfoDomainsSave() throws Exception {
  		Logging.logDebug("----- doPackageInfoDomainsSave start -----");

        String currentFileName = "";

  		try {
            List<String> currentFileNameList = FileUtils.readFileByRow(latestHanddleFile);
            if (currentFileNameList.size() > 0) {
                currentFileName = currentFileNameList.get(0);
            }

            List<String> fileList = ftpHelper.getFileList(responseFilePath);
            fileList = Utility.getSubStringList(fileList, currentFileName);

            for (String fileName : fileList) {
                List<PackageInfoDomain> packageInfoDomains = new ArrayList<PackageInfoDomain>();
                List<String> contentList = ftpHelper.readRemoteFileByRow(responseFilePath, fileName);

                for (String content : contentList) {
                    String[] contentArr = Utility.splitString(content, Constant.RESPONSE_FIEL_SEPARATOR);
                    if(null != contentArr && contentArr.length > 0 && "0".equals(contentArr[11])){
                        PackageInfoDomain domain = packageInfoBusiness.convertPackageInfoFromFile(contentArr);
                        packageInfoDomains.add(domain);                    	
                    }
                }

                packageInfoBusiness.savePackageInfoList(packageInfoDomains, Constant.HIBERNATE_FLUSH_NUM);
                currentFileName = fileName;
            }
  		} catch(Exception e){           
  			Logging.logError("Error occurs in doPackageInfoDomainsSave ", e);
  		} finally{
  			 FileUtils.writeFileOverWrite(latestHanddleFile, currentFileName);
  			ftpHelper.disConnectFtpServer();
  		}
  		Logging.logDebug("----- doPackageInfoDomainsSave end -----");
  	}

}
