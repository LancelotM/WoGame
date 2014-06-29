package com.unicom.game.center.business;

import java.util.Date;
import java.util.List;

import com.unicom.game.center.model.JsonModel;
import com.unicom.game.center.model.JsonParent;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.unicom.game.center.db.dao.UserCountDao;
import com.unicom.game.center.model.LoginInfo;
import com.unicom.game.center.utils.DateUtils;
import com.unicom.game.center.utils.Logging;

/**
 * @author Alex Yin
 * 
 * @Date 2014-6-24
 */
@Component
@Transactional
public class LoginInfoBusiness {
	
	@Autowired
	private UserCountDao userCountDao;
	
	public long fetchNewUserCount(Integer channelId){
		long count = 0;
		
		try{
			Date yesterday = DateUtils.getDayByInterval(new Date(), -1);
			String endDate = DateUtils.formatDateToString(yesterday, "yyyy-MM-dd");
			count = userCountDao.fetchNewUserCount(endDate, channelId);
		}catch(Exception ex){
			Logging.logError("Error occur in fetchNewUserCount.", ex);
		}
		
		return count;
	}
	
	public long fetchTotalUserCount(Integer channelId){
		long count = 0;
		
		try{
			count = userCountDao.fetchTotalUserCount(channelId);
		}catch(Exception ex){
			Logging.logError("Error occur in fetchTotalUserCount.", ex);
		}
		
		return count;
	}
	
	public JsonParent fetchLoginInfoByDate(Integer channelId){
		List<LoginInfo> loginInfoList = null;
        JsonParent jsonData = null;
		try{
			Date today = new Date();
			Date yesterday = DateUtils.getDayByInterval(today, -1);
			String endDate = DateUtils.formatDateToString(yesterday, "yyyy-MM-dd");
			Date previousDate = DateUtils.getDayByInterval(today, -30);
			String startDate = DateUtils.formatDateToString(previousDate, "yyyy-MM-dd");
			
			loginInfoList = userCountDao.fetchLoginInfoByDate(startDate, endDate, channelId);
            jsonData = getJsonData(loginInfoList);
		}catch(Exception ex){
			Logging.logError("Error occur in fetchLoginInfoByDate", ex);
		}
		
		return jsonData;
	}
	
	public JsonParent fetchLoginInfoByMonth(Integer channelId){
		List<LoginInfo> loginInfoList = null;
        JsonParent jsonData = null;
		try{
			Date today = new Date();
			Date yesterday = DateUtils.getDayByInterval(today, -1);
			String endDate = DateUtils.formatDateToString(yesterday, "yyyy-MM-dd");
			String startDate = DateUtils.getMonthFirstByInterval(today, -11);
			
			loginInfoList = userCountDao.fetchLoginInfoByMonth(startDate, endDate, channelId);
            jsonData = getJsonData(loginInfoList);
		}catch(Exception ex){
			Logging.logError("Error occur in fetchLoginInfoByMonth", ex);
		}
		
		return jsonData;
	}

    public JsonParent getJsonData(List<LoginInfo> loginInfos){
        JsonParent jsonData = new JsonParent();
        JsonModel newUser = new JsonModel();
        newUser.setName("新用户");
        JsonModel oldUser = new JsonModel();
        oldUser.setName("老用户");
        for(LoginInfo loginInfo: loginInfos){
            newUser.addData(Integer.parseInt(loginInfo.getNewUser()));
            oldUser.addData(Integer.parseInt(loginInfo.getOldUser()));
            jsonData.addUnit(loginInfo.getDate());
        }
        jsonData.addResult(newUser);
        jsonData.addResult(oldUser);
        jsonData.setStatus("success");
        return jsonData;
    }

}
