package com.unicom.game.center.business;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.unicom.game.center.db.dao.PackageInfoDao;
import com.unicom.game.center.db.domain.PackageInfoDomain;
import com.unicom.game.center.utils.Logging;

/**
 * @author Alex Yin
 * 
 * @Date 2014-6-23
 */
@Component
@Transactional
public class PackageInfoBusiness {
	@Autowired
	private PackageInfoDao packageInfoDao;

	/**
	 * 
	 * @param channelId
	 * @param productId
	 * @param onlinetime
	 * @return
	 */
	public String checkPackageExist(String channelId,String productId, String onlinetime){
		String channelCode = null;
		
		try{
			PackageInfoDomain packageInfo = packageInfoDao.getById(channelId, productId);
			if(null != packageInfo && null != packageInfo.getApkOnlineTime()){
				if(Integer.parseInt(packageInfo.getApkOnlineTime()) >= Integer.parseInt(onlinetime)){

				}
			}
		}catch(Exception ex){
			Logging.logError("Error occur in checkPackageExist", ex);
		}
		return channelCode;
	}
	
	/**
	 * 
	 * @param packageInfo
	 */
	public void savePackageInfo(String packageInfo){
		try{
			//TODO: call stored procedure to save package info
		}catch(Exception ex){
			Logging.logError("Error occur in savePackageInfo.", ex);
		}
	}
}
