package com.unicom.game.center.business;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.unicom.game.center.db.dao.GameTrafficDao;
import com.unicom.game.center.model.GameInfo;
import com.unicom.game.center.utils.DateUtils;
import com.unicom.game.center.utils.Logging;

/**
 * @author Alex Yin
 * 
 * @Date Jun 22, 2014
 */
@Component
@Transactional
public class GameTrafficBusiness {

	@Autowired
	private GameTrafficDao gameTrafficDao;
	
	/**
	 * 
	 * @param channelId
	 * @param type	--type = 1 by day	--type = 2 by month
	 * @return
	 */
	public List<GameInfo> fetchBannerInfo(int channelId, int type){
		List<GameInfo>  gameList = null;
		
		try{
			if(1 == type){
				Date today = new Date();
				String endDate = DateUtils.formatDateToString(today, "yyyy-MM-dd");
				Date previousDate = DateUtils.getDayByInterval(today, -4);
				String startDate = DateUtils.formatDateToString(previousDate, "yyyy-MM-dd");
				gameList = gameTrafficDao.fetchGameInfoByDate(startDate, endDate, false, 18082);				
			}else{
				
			}	
		}catch(Exception ex){
			Logging.logError("Error occur in fetchBannerInfo.", ex);
		}
		
		return gameList;
	}
	
}
