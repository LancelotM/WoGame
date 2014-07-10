package com.unicom.game.center.business;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.unicom.game.center.db.dao.UserCountDao;
import com.unicom.game.center.db.domain.UserCountDomain;
import com.unicom.game.center.log.model.UserCount;
import com.unicom.game.center.model.JsonModel;
import com.unicom.game.center.model.JsonParent;
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
            jsonData = getJsonData(loginInfoList,"day");
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
            jsonData = getJsonData(loginInfoList,"month");
		}catch(Exception ex){
			Logging.logError("Error occur in fetchLoginInfoByMonth", ex);
		}
		
		return jsonData;
	}

    public JsonParent getJsonData(List<LoginInfo> loginInfos,String dateType){
        JsonParent jsonData = new JsonParent();
        JsonModel newUser = new JsonModel();
        newUser.setName("新用户");
        JsonModel oldUser = new JsonModel();
        oldUser.setName("老用户");
        if(loginInfos == null){
            List<String> units = new ArrayList<String>();
            if("day".equals(dateType)){
                for(int i = 0;i<30;i++){
                    newUser.addData(0);
                    oldUser.addData(0);
                    units.add(DateUtils.formatDateToString(DateUtils.getDayByInterval(new Date(),-(i+1)),"MM-dd"));
                }
            }else if("month".equals(dateType)){
                for(int i = 0;i<12;i++){
                    newUser.addData(0);
                    oldUser.addData(0);
                    units.add(DateUtils.formatDateToString(DateUtils.stringToDate(DateUtils.getMonthFirstByInterval(new Date(),-(i)),"yyyy-MM-dd"),"yyyy-MM"));
                }

            }
            Collections.reverse(units);
            jsonData.setUnit(units);
        }else {
            for(LoginInfo loginInfo: loginInfos){
                newUser.addData(Integer.parseInt(loginInfo.getNewUser()));
                oldUser.addData(Integer.parseInt(loginInfo.getOldUser()));
                jsonData.addUnit(loginInfo.getDate());
            }
        }




        jsonData.addResult(newUser);
        jsonData.addResult(oldUser);
        jsonData.setStatus("success");
        return jsonData;
    }
    
    public void typeConversion(HashMap<Integer,UserCount> userCountHashMap){
        List<UserCountDomain> list = new ArrayList<UserCountDomain>();
        Iterator iterator = userCountHashMap.entrySet().iterator();
        while (iterator.hasNext()){
            UserCountDomain userCountDomain = new UserCountDomain();
            Map.Entry<Integer, UserCount> entry = (Map.Entry)iterator.next();
            UserCount userCount = entry.getValue();
            userCountDomain.setNewUserCount(userCount.getNew_user_count());
            userCountDomain.setOldUserCount(userCount.getOld_user_count());
            userCountDomain.setChannelId(userCount.getChannelId());
            userCountDomain.setDateCreated(userCount.getDateCreated());
            list.add(userCountDomain);
        }
        userCountDao.saveUserCountDomainList(list,100);
    }    

}
