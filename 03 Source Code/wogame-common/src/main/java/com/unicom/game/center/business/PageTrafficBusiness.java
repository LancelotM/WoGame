package com.unicom.game.center.business;

import java.util.Date;
import java.util.List;

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
	
	public List<PageTrafficInfo> fetchTrafficInfoByDate(Integer channelId){
		List<PageTrafficInfo> loginInfoList = null;
		
		try{
			Date today = new Date();
			Date yesterday = DateUtils.getDayByInterval(today, -1);
			String endDate = DateUtils.formatDateToString(yesterday, "yyyy-MM-dd");
			Date previousDate = DateUtils.getDayByInterval(today, -30);
			String startDate = DateUtils.formatDateToString(previousDate, "yyyy-MM-dd");
			
			loginInfoList = pageTrafficDao.fetchTrafficInfoByDate(startDate, endDate, channelId);
		}catch(Exception ex){
			Logging.logError("Error occur in fetchTrafficInfoByDate", ex);
		}
		
		return loginInfoList;
	}
	
	public List<PageTrafficInfo> fetchTrafficInfoByMonth(Integer channelId){
		List<PageTrafficInfo> loginInfoList = null;
		
		try{
			Date today = new Date();
			Date yesterday = DateUtils.getDayByInterval(today, -1);
			String endDate = DateUtils.formatDateToString(yesterday, "yyyy-MM-dd");
			String startDate = DateUtils.getMonthFirstByInterval(today, -11);
			
			loginInfoList = pageTrafficDao.fetchTrafficInfoByMonth(startDate, endDate, channelId);
	
		}catch(Exception ex){
			Logging.logError("Error occur in fetchTrafficInfoByMonth", ex);
		}
		
		return loginInfoList;
	}
}
