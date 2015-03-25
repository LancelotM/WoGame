package com.unicom.game.center.business;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.unicom.game.center.db.dao.StatisticsDao;
import com.unicom.game.center.db.domain.StatisticsDomain;
import com.unicom.game.center.model.JsonModel;
import com.unicom.game.center.model.JsonParent;
import com.unicom.game.center.model.PageTrafficModel;
import com.unicom.game.center.utils.DateUtils;
import com.unicom.game.center.utils.Logging;

/**
 * @author Alex
 *
 */
@Component
@Transactional
public class StatisticsBusiness {
	
	@Autowired
	private StatisticsDao statisticsDao;

    /**
    *
    * @param list
    * @param num
    */
  	public void saveStatisticsList(List<StatisticsDomain> list, int num){
  		try{
  			statisticsDao.saveStatisticsDomainList(list, num);
  		}catch(Exception ex){
  			Logging.logError("Error occur in saveStatisticsList.", ex);
  		}
  	}
  	
	public JsonParent fetchTrafficInfoByDate(Integer channelId){
		List<PageTrafficModel> loginInfoList = null;
        JsonParent jsonModel = null;
		try{
			Date today = new Date();
			Date yesterday = DateUtils.getDayByInterval(today, -1);
			String endDate = DateUtils.formatDateToString(yesterday, "yyyy-MM-dd");
			Date previousDate = DateUtils.getDayByInterval(today, -30);
			String startDate = DateUtils.formatDateToString(previousDate, "yyyy-MM-dd");
			
			loginInfoList = statisticsDao.fetchTrafficInfoByDate(startDate, endDate, channelId);
            jsonModel = getJsonData(loginInfoList,"day");
		}catch(Exception ex){
			Logging.logError("Error occur in fetchTrafficInfoByDate", ex);
		}
		
		return jsonModel;
	}
	
	public JsonParent fetchTrafficInfoByMonth(Integer channelId){
		List<PageTrafficModel> loginInfoList = null;
        JsonParent jsonModel = null;
		try{
			Date today = new Date();
			Date yesterday = DateUtils.getDayByInterval(today, -1);
			String endDate = DateUtils.formatDateToString(yesterday, "yyyy-MM-dd");
			String startDate = DateUtils.getMonthFirstByInterval(today, -11);
			
			loginInfoList = statisticsDao.fetchTrafficInfoByMonth(startDate, endDate, channelId);
            jsonModel = getJsonData(loginInfoList,"month");
		}catch(Exception ex){
			Logging.logError("Error occur in fetchTrafficInfoByMonth", ex);
		}
		
		return jsonModel;
	}

    public JsonParent getJsonData(List<PageTrafficModel> pageTrafficInfos,String dateType){
        JsonParent jsonData = new JsonParent();
        JsonModel homepagePV = new JsonModel();
        homepagePV.setName("首页 PV");
        JsonModel homepageUV = new JsonModel();
        homepageUV.setName("首页 UV");

        if(pageTrafficInfos == null){
            List<String> units = new ArrayList<String>();
            if("day".equals(dateType)){
                for(int i = 0;i<30;i++){
                    homepagePV.addData(0);
                    homepageUV.addData(0);
                    units.add(DateUtils.formatDateToString(DateUtils.getDayByInterval(new Date(),-(i+1)),"MM-dd"));
                }
            }else if("month".equals(dateType)){
                for(int i = 0;i<12;i++){
                    homepagePV.addData(0);
                    homepageUV.addData(0);
                    units.add(DateUtils.formatDateToString(DateUtils.stringToDate(DateUtils.getMonthFirstByInterval(new Date(),-(i)),"yyyy-MM-dd"),"yyyy-MM"));

                }
            }
            Collections.reverse(units);
            jsonData.setUnit(units);
        }else {
            for(PageTrafficModel pageTrafficInfo: pageTrafficInfos){
                homepagePV.addData(Integer.parseInt(pageTrafficInfo.getHomepagePV()));
                homepageUV.addData(Integer.parseInt(pageTrafficInfo.getHomepageUV()));
                jsonData.addUnit(pageTrafficInfo.getDate());
            }
        }

        jsonData.addResult(homepagePV);
        jsonData.addResult(homepageUV);
        jsonData.setStatus("success");
        return jsonData;
    }  	
}
