package com.unicom.game.center.loganalyser.imp;

import com.unicom.game.center.loganalyser.ILogAnalyser;
import com.unicom.game.center.utils.Logging;

public class LogAnalyser implements ILogAnalyser {

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
  	public void doPackageInfoDomainsSave(){
  		Logging.logDebug("----- doPackageInfoDomainsSave start -----");
  		try {



  		} catch(Exception e){
  			Logging.logError("Error occurs in doPackageInfoDomainsSave ", e);
  		}
  		Logging.logDebug("----- doPackageInfoDomainsSave end -----");
  	}

}
