package com.unicom.game.center.loganalyser.imp;

import com.jcraft.jsch.ChannelSftp;
import com.unicom.game.center.business.PackageInfoBusiness;
import com.unicom.game.center.db.domain.PackageInfoDomain;
import com.unicom.game.center.loganalyser.ILogAnalyser;
import com.unicom.game.center.utils.Constant;
import com.unicom.game.center.utils.FileUtils;
import com.unicom.game.center.utils.Logging;
import com.unicom.game.center.utils.SFTPHelper;
import com.unicom.game.center.utils.Utility;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class LogAnalyser implements ILogAnalyser {

    @Autowired
    private PackageInfoBusiness packageInfoBusiness;

    @Autowired
   	private SFTPHelper sftpHelper;
    
	@Value("#{properties['response.file.path']}")
	private String responseFilePath;
	
	@Value("#{properties['latest.handdle.file']}")
	private String latestHanddleFile;

	@Override
	public void doLogAnaylyse(){
		Logging.logDebug("----- doLogAnaylyse start -----");
		try{
			

					
		}catch(Exception e){
			Logging.logError("Error occurs in doLogAnaylyse ", e);
		}
		Logging.logDebug("----- doLogAnaylyse end -----");
	}


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

}
