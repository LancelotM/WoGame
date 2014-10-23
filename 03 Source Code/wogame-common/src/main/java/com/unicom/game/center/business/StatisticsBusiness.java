package com.unicom.game.center.business;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.unicom.game.center.db.dao.StatisticsDao;
import com.unicom.game.center.db.domain.StatisticsDomain;
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
}
