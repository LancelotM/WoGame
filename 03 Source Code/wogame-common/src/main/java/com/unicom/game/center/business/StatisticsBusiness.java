package com.unicom.game.center.business;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.unicom.game.center.db.dao.StatisticsDao;

/**
 * @author Alex
 *
 */
@Component
@Transactional
public class StatisticsBusiness {
	
	@Autowired
	private StatisticsDao statisticsDao;

}
