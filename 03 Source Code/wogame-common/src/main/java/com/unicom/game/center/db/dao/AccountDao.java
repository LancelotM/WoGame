package com.unicom.game.center.db.dao;

import java.util.Date;
import java.util.List;

import org.springframework.stereotype.Component;

import com.unicom.game.center.db.domain.AccountDomain;

@Component
public class AccountDao extends HibernateDao{
	
	public void save(AccountDomain account){
		getSession().save(account);
	}
	
	public AccountDomain getById(int accountId){
		return (AccountDomain)getSession().get(AccountDomain.class, accountId);
	}
	
	public void update(AccountDomain account){
		account.setDateModified(new Date());
		getSession().update(account);
	}
	
	public void delete(int accountId){
		AccountDomain account = getById(accountId);
		getSession().delete(account);
	}
	
	@SuppressWarnings("unchecked")
	public List<AccountDomain> getAll(){
		return getSession().createQuery("from AccountDomain").list();
	}	
	
	public AccountDomain fetchUserByName(String username){
		String hql = "from AccountDomain ac where ac.accountName=:accountName";
		
		AccountDomain account = (AccountDomain)getSession().createQuery(hql)
									.setParameter("accountName", username)
									.uniqueResult();
		
		return (null != account) ? account : null;
	}	
	
}
