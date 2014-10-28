package com.unicom.game.center.business;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.unicom.game.center.db.dao.AccountDao;
import com.unicom.game.center.db.domain.AccountDomain;
import com.unicom.game.center.model.AccountInfo;
import com.unicom.game.center.utils.AESEncryptionHelper;
import com.unicom.game.center.utils.Logging;

/**
 * @author Alex Yin
 * 
 * @Date 2014-6-20
 */
@Component
@Transactional
public class AccountBusiness {

	@Autowired
	private AccountDao accountDao;
	
	@Value("#{properties['site.secret.key']}")
	private String secretKey;
	
	/**
	 * 
	 * @param username
	 * @param password
	 * @return loginStatus --0:OK  --1:user not exist  --2:password error  --3:other error
	 */
	public AccountInfo login(String username, String password){
		AccountInfo accountInfo = new AccountInfo();
		
		accountInfo.setLoginStatus(0);
		
		try{
			AccountDomain account = accountDao.fetchUserByName(username);
			if(null != account){
				String pwd = AESEncryptionHelper.encrypt(password, secretKey);
				if(!pwd.equals(account.getPassword())){
					accountInfo.setLoginStatus(2);
				}else{
					accountInfo.setUserName(username);
					accountInfo.setRole(account.getRole());
				}
			}else{
				accountInfo.setLoginStatus(1);
			}
		}catch(Exception e){
			Logging.logError("Error occur in login", e);
			accountInfo.setLoginStatus(3);
		}
		
		return accountInfo;
	}
	
	/**
	 * 
	 * @param username
	 * @param password
	 * @return	--0:OK	--1:user exist error --2:other error
	 */
	public int signup(String username, String password){
		int flag = 0;
		
		try{
			AccountDomain account = accountDao.fetchUserByName(username);
			
			if(null == account){
				Date date = new Date();
				String pwd = AESEncryptionHelper.encrypt(password, secretKey);
				account = new AccountDomain();
				account.setAccountName(username);
				account.setPassword(pwd);
				account.setDateCreated(date);
				account.setDateModified(date);
				accountDao.save(account);				
			}else{
				flag = 1;
			}			

		}catch(Exception e){
			Logging.logError("Error occur in login", e);
			flag = 2;
		}
		
		return flag;
	}
}
