package com.unicom.game.center.persistence.db.dao;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.unicom.game.center.persistence.db.domain.AccountDomain;

@Repository
public class AccountDao extends HibernateDao{
	
	public void save(AccountDomain account){
		getSession().save(account);
	}
	
	public AccountDomain getById(int accountId){
		return (AccountDomain)getSession().load(AccountDomain.class, accountId);
	}
	
	public void update(AccountDomain account){
		account.setDateModified(new java.sql.Date(System.currentTimeMillis()));
		getSession().update(account);
	}
	
	public void delete(int accountId){
		AccountDomain account = (AccountDomain)getSession().get(AccountDomain.class, accountId);
		getSession().delete(account);
	}
	
	public List<AccountDomain> getAll(){
		return getSession().createQuery("from AccountDomain").list();
	}
	
}
