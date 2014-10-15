package com.unicom.game.center.db.dao;

import org.springframework.stereotype.Component;

import com.unicom.game.center.db.domain.StatisticsDomain;

/**
 * @author Alex
 *
 */
@Component
public class StatisticsDao extends HibernateDao<StatisticsDomain>{

	public void save(StatisticsDomain statistics){
		getSession().save(statistics);
		getSession().flush();
	}
	
	public void update(StatisticsDomain statistics){
		getSession().update(statistics);
		getSession().flush();
	}
}
