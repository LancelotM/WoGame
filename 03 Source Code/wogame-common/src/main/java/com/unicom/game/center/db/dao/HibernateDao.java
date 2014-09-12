package com.unicom.game.center.db.dao;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * @author Alex.Yin
 * @Date Jun 09, 2014
 */

@Component
public class HibernateDao<T>{

	@Autowired
	private SessionFactory sessionFactory;

	protected Session getSession() {
		return this.sessionFactory.getCurrentSession();
	}
	
    public void saveDomainList(List<T> list, int num) {
        Session session = null;
        if (list != null && list.size() > 0) {
            try {
                session = getSession();
                T domain = null;

                for (int i = 0; i < list.size(); i++) {
                    domain = list.get(i);
                    session.merge(domain);
                    if (i % num == 0) {
                        session.flush();
                        session.clear();
                    }
                }
                session.flush();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }	
}
