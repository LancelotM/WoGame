package com.unicom.game.center.model;

import java.io.Serializable;

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
	
	private String newUser;
	
	private String oldUser;
	
	private String date;

	public String getNewUser() {
		return newUser;
	}

	public void setNewUser(String newUser) {
		this.newUser = newUser;
	}

	public String getOldUser() {
		return oldUser;
	}

	public void setOldUser(String oldUser) {
		this.oldUser = oldUser;
	}

	public String getDate() {
		return date;
	}

	public void setDate(String date) {
		this.date = date;
	}	

}
