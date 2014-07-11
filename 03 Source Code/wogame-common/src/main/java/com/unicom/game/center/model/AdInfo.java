package com.unicom.game.center.model;

import java.io.Serializable;

/**
 * @author Alex Yin
 * 
 * @Date Jun 21, 2014
 */
public class AdInfo implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private String adId;
	
	private String clickThrough;	
	
	private String date;


	public String getAdId() {
		return adId;
	}

	public void setAdId(String adId) {
		this.adId = adId;
	}

	public String getClickThrough() {
		return clickThrough;
	}

	public void setClickThrough(String clickThrough) {
		this.clickThrough = clickThrough;
	}

	public String getDate() {
		return date;
	}

	public void setDate(String date) {
		this.date = date;
	}		

}
