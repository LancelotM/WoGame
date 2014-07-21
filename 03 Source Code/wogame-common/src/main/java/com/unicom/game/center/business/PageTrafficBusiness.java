package com.unicom.game.center.business;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import com.unicom.game.center.utils.Constant;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.unicom.game.center.db.dao.PageTrafficDao;
import com.unicom.game.center.db.domain.PageTrafficDomain;
import com.unicom.game.center.log.model.PageTraffic;
import com.unicom.game.center.model.JsonModel;
import com.unicom.game.center.model.JsonParent;
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
            jsonModel = getJsonData(loginInfoList,"day");
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
            jsonModel = getJsonData(loginInfoList,"month");
		}catch(Exception ex){
			Logging.logError("Error occur in fetchTrafficInfoByMonth", ex);
		}
		
		return jsonModel;
	}

    public JsonParent getJsonData(List<PageTrafficInfo> pageTrafficInfos,String dateType){
        JsonParent jsonData = new JsonParent();
        JsonModel hostPage = new JsonModel();
        hostPage.setName("首页");
        JsonModel category = new JsonModel();
        category.setName("分类");
        JsonModel hotlist = new JsonModel();
        hotlist.setName("一周热榜");
        JsonModel latest = new JsonModel();
        latest.setName("最新");

        if(pageTrafficInfos == null){
            List<String> units = new ArrayList<String>();
            if("day".equals(dateType)){
                for(int i = 0;i<30;i++){
                    hostPage.addData(0);
                    category.addData(0);
                    hotlist.addData(0);
                    latest.addData(0);
                    units.add(DateUtils.formatDateToString(DateUtils.getDayByInterval(new Date(),-(i+1)),"MM-dd"));
                }
            }else if("month".equals(dateType)){
                for(int i = 0;i<12;i++){
                    hostPage.addData(0);
                    category.addData(0);
                    hotlist.addData(0);
                    latest.addData(0);
                    units.add(DateUtils.formatDateToString(DateUtils.stringToDate(DateUtils.getMonthFirstByInterval(new Date(),-(i)),"yyyy-MM-dd"),"yyyy-MM"));

                }
            }
            Collections.reverse(units);
            jsonData.setUnit(units);
        }else {
            for(PageTrafficInfo pageTrafficInfo: pageTrafficInfos){
                hostPage.addData(Integer.parseInt(pageTrafficInfo.getHomepage()));
                category.addData(Integer.parseInt(pageTrafficInfo.getCategory()));
                hotlist.addData(Integer.parseInt(pageTrafficInfo.getHotlist()));
                latest.addData(Integer.parseInt(pageTrafficInfo.getLatest()));
                jsonData.addUnit(pageTrafficInfo.getDate());
            }
        }

        jsonData.addResult(hostPage);
        jsonData.addResult(category);
        jsonData.addResult(hotlist);
        jsonData.addResult(latest);
        jsonData.setStatus("success");
        return jsonData;
    }


    public void typeConversion(Map<Integer,PageTraffic> pageTrafficHashMap){
        List<PageTrafficDomain> list = new ArrayList<PageTrafficDomain>();
        Iterator iterator = pageTrafficHashMap.entrySet().iterator();
        while (iterator.hasNext()){
            PageTrafficDomain pageTrafficDomain = new PageTrafficDomain();
            Map.Entry<Integer, PageTraffic> entry = (Map.Entry)iterator.next();
            PageTraffic pageTraffic = entry.getValue();
            pageTrafficDomain.setHomepage(pageTraffic.getHomepage());
            pageTrafficDomain.setCategory(pageTraffic.getCategory());
            pageTrafficDomain.setHotlist(pageTraffic.getHotlist());
            pageTrafficDomain.setLatest(pageTraffic.getLatest());
            pageTrafficDomain.setChannelId(pageTraffic.getChannelId());
            pageTrafficDomain.setDateCreated(pageTraffic.getDateCreated());
            list.add(pageTrafficDomain);
        }
        pageTrafficDao.savePageTrafficDomainList(list, Constant.HIBERNATE_FLUSH_NUM);
    }
}
