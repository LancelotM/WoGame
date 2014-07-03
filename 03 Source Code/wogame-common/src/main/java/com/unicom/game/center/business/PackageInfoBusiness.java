package com.unicom.game.center.business;

import com.unicom.game.center.db.dao.PackageInfoDao;
import com.unicom.game.center.db.domain.PackageInfoDomain;
import com.unicom.game.center.model.PackageInfo;
import com.unicom.game.center.utils.Constant;
import com.unicom.game.center.utils.DateUtils;
import com.unicom.game.center.utils.Logging;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

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
			List<PackageInfo> packageInfoList = packageInfoDao.getDLPackageInfo(channelId, productId);
			if(null != packageInfoList && !packageInfoList.isEmpty()){
				for(PackageInfo packageInfo : packageInfoList){
					if((packageInfo.getChannelCode().equals(channelId)) &&
						(DateUtils.compareDate(packageInfo.getApkOnlineTime(),onlinetime) >= 0)){
						channelCode = channelId;
						break;
					}else if((packageInfo.getChannelCode().equals(Constant.WOGAME_CHANNEL_CODE)) &&
						(DateUtils.compareDate(packageInfo.getApkOnlineTime(),onlinetime) >= 0)){
						channelCode = Constant.WOGAME_CHANNEL_CODE;
					}
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

    /**
     *
     * @param list
     * @param num
     */
   	public void savePackageInfoList(List<PackageInfoDomain> list, int num){
   		try{
   			packageInfoDao.savePackageInfoDomainList(list, num);
   		}catch(Exception ex){
   			Logging.logError("Error occur in savePackageInfoList.", ex);
   		}
   	}
}
