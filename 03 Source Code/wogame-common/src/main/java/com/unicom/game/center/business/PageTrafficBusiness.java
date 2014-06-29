package com.unicom.game.center.business;

import java.util.Date;
import java.util.List;

import com.unicom.game.center.model.JsonModel;
import com.unicom.game.center.model.JsonParent;
import com.unicom.game.center.model.LoginInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.unicom.game.center.db.dao.PageTrafficDao;
import com.unicom.game.center.model.PageTrafficInfo;
import com.unicom.game.center.utils.DateUtils;
import com.unicom.game.center.utils.Logging;

/**
 * @author Alex Yin
 * 
 * @Date 2014-6-24
 */
@Component
@Transactional
public class PageTrafficBusiness {
	
	@Autowired
	private PageTrafficDao pageTrafficDao;
	
	public JsonParent fetchTrafficInfoByDate(Integer channelId){
		List<PageTrafficInfo> loginInfoList = null;
        JsonParent jsonModel = null;
		try{
			Date today = new Date();
			Date yesterday = DateUtils.getDayByInterval(today, -1);
			String endDate = DateUtils.formatDateToString(yesterday, "yyyy-MM-dd");
			Date previousDate = DateUtils.getDayByInterval(today, -30);
			String startDate = DateUtils.formatDateToString(previousDate, "yyyy-MM-dd");
			
			loginInfoList = pageTrafficDao.fetchTrafficInfoByDate(startDate, endDate, channelId);
            jsonModel = getJsonData(loginInfoList);
		}catch(Exception ex){
			Logging.logError("Error occur in fetchTrafficInfoByDate", ex);
		}
		
		return jsonModel;
	}
	
	public JsonParent fetchTrafficInfoByMonth(Integer channelId){
		List<PageTrafficInfo> loginInfoList = null;
        JsonParent jsonModel = null;
		try{
			Date today = new Date();
			Date yesterday = DateUtils.getDayByInterval(today, -1);
			String endDate = DateUtils.formatDateToString(yesterday, "yyyy-MM-dd");
			String startDate = DateUtils.getMonthFirstByInterval(today, -11);
			
			loginInfoList = pageTrafficDao.fetchTrafficInfoByMonth(startDate, endDate, channelId);
            jsonModel = getJsonData(loginInfoList);
		}catch(Exception ex){
			Logging.logError("Error occur in fetchTrafficInfoByMonth", ex);
		}
		
		return jsonModel;
	}

    public JsonParent getJsonData(List<PageTrafficInfo> pageTrafficInfos){
        JsonParent jsonData = new JsonParent();
        JsonModel hostPage = new JsonModel();
        hostPage.setName("首頁");
        JsonModel category = new JsonModel();
        category.setName("分类");
        JsonModel hotlist = new JsonModel();
        hotlist.setName("一周热榜");
        JsonModel latest = new JsonModel();
        latest.setName("最新");
        for(PageTrafficInfo pageTrafficInfo: pageTrafficInfos){
            hostPage.addData(Integer.parseInt(pageTrafficInfo.getHomepage()));
            category.addData(Integer.parseInt(pageTrafficInfo.getCategory()));
            hotlist.addData(Integer.parseInt(pageTrafficInfo.getHotlist()));
            latest.addData(Integer.parseInt(pageTrafficInfo.getLatest()));
            jsonData.addUnit(pageTrafficInfo.getDate());
        }
        jsonData.addResult(hostPage);
        jsonData.addResult(category);
        jsonData.addResult(hotlist);
        jsonData.addResult(latest);
        jsonData.setStatus("success");
        return jsonData;
    }
}
