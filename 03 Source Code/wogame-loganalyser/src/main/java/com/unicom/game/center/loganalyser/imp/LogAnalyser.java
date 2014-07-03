package com.unicom.game.center.loganalyser.imp;

import com.unicom.game.center.business.PackageInfoBusiness;
import com.unicom.game.center.db.domain.PackageInfoDomain;
import com.unicom.game.center.loganalyser.ILogAnalyser;
import com.unicom.game.center.utils.FileUtils;
import com.unicom.game.center.utils.Logging;
import com.unicom.game.center.utils.SFTPHelper;
import com.unicom.game.center.utils.Utility;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Component
public class LogAnalyser implements ILogAnalyser {

    @Autowired
    private PackageInfoBusiness packageInfoBusiness;

    @Autowired
   	private SFTPHelper sftpHelper;

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
  	public void doPackageInfoDomainsSave() throws IOException {
  		Logging.logDebug("----- doPackageInfoDomainsSave start -----");

        String recordPath = "D:\\demo\\demo.txt";
        String path = "/wostore/wostorechannelapk/response/all/";
        String separate = "|";
        int flushNum = 20;

        String currentFileName = "";

  		try {
            List<String> currentFileNameList = FileUtils.getFileList(recordPath);
            if (currentFileNameList.size() > 0) {
                currentFileName = currentFileNameList.get(0);
            }

            List<String> fileList = sftpHelper.getFileList(path);
            fileList = Utility.getSubStringList(fileList, currentFileName);

            for (String fileName : fileList) {
                List<PackageInfoDomain> packageInfoDomains = new ArrayList<PackageInfoDomain>();
                List<String> contentList = sftpHelper.readRemoteFileByRow(path, fileName);

                for (String content : contentList) {
                    String[] contentArr = Utility.splitString(content, separate);
                    PackageInfoDomain domain = packageInfoBusiness.convertPackageInfoFromFile(contentArr);
                    packageInfoDomains.add(domain);
                }

                packageInfoBusiness.savePackageInfoList(packageInfoDomains, flushNum);
                currentFileName = fileName;
            }
  		} catch(Exception e){
            FileUtils.writeFileOverWrite(recordPath, currentFileName);
  			Logging.logError("Error occurs in doPackageInfoDomainsSave ", e);
  		}
  		Logging.logDebug("----- doPackageInfoDomainsSave end -----");
  	}

}
