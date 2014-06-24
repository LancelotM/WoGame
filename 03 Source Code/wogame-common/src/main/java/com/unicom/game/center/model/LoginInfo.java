package com.unicom.game.center.model;

import java.io.Serializable;
import java.util.Date;

/**
 * @author Alex Yin
 * 
 * @Date 2014-6-24
 */
public class LoginInfo implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private long newUser;
	
	private long oldUser;
	
	private Date date;

	public long getNewUser() {
		return newUser;
	}

	public void setNewUser(long newUser) {
		this.newUser = newUser;
	}

	public long getOldUser() {
		return oldUser;
	}

	public void setOldUser(long oldUser) {
		this.oldUser = oldUser;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}	

}
