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
	
	private int newUser;
	
	private int oldUser;
	
	private Date date;

	public int getNewUser() {
		return newUser;
	}

	public void setNewUser(int newUser) {
		this.newUser = newUser;
	}

	public int getOldUser() {
		return oldUser;
	}

	public void setOldUser(int oldUser) {
		this.oldUser = oldUser;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}	

}
