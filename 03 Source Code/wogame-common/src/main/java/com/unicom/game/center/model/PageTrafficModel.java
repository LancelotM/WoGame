package com.unicom.game.center.model;

import java.io.Serializable;

/**
 * @author Alex
 *
 */
public class PageTrafficModel implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private String homepagePV;
	
	private String homepageUV;	
	
	private String date;

	public String getHomepagePV() {
		return homepagePV;
	}

	public void setHomepagePV(String homepagePV) {
		this.homepagePV = homepagePV;
	}

	public String getHomepageUV() {
		return homepageUV;
	}

	public void setHomepageUV(String homepageUV) {
		this.homepageUV = homepageUV;
	}

	public String getDate() {
		return date;
	}

	public void setDate(String date) {
		this.date = date;
	}	

}
