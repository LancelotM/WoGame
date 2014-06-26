package com.unicom.game.center.model;

import java.io.Serializable;

/**
 * @author Alex Yin
 * 
 * @Date 2014-6-26
 */
public class PageTrafficInfo implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private String homepage;
	
	private String category;
	
	private String hotlist;
	
	private String latest;
	
	private String date;

	public String getHomepage() {
		return homepage;
	}

	public void setHomepage(String homepage) {
		this.homepage = homepage;
	}

	public String getCategory() {
		return category;
	}

	public void setCategory(String category) {
		this.category = category;
	}

	public String getHotlist() {
		return hotlist;
	}

	public void setHotlist(String hotlist) {
		this.hotlist = hotlist;
	}

	public String getLatest() {
		return latest;
	}

	public void setLatest(String latest) {
		this.latest = latest;
	}

	public String getDate() {
		return date;
	}

	public void setDate(String date) {
		this.date = date;
	}

}
