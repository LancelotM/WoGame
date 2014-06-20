package com.unicom.game.center.db.dao;

import java.util.List;

import org.hibernate.Session;
import org.springframework.stereotype.Component;

import com.unicom.game.center.db.domain.AccountDomain;

@Component
public class AccountDao extends HibernateDao{
	
	public void save(AccountDomain account){
		getSession().save(account);
	}
	
	public AccountDomain getById(int accountId){
		return (AccountDomain)getSession().load(AccountDomain.class, accountId);
	}
	
	public void update(AccountDomain account){
		Session session = getSession();
		account.setDateModified(new java.sql.Date(System.currentTimeMillis()));
		getSession().merge(account);
	}
	
	public void delete(int accountId){
		AccountDomain account = (AccountDomain)getSession().get(AccountDomain.class, accountId);
		getSession().delete(account);
	}
	
	public List<AccountDomain> getAll(){
		return getSession().createQuery("from AccountDomain").list();
	}
	
}
