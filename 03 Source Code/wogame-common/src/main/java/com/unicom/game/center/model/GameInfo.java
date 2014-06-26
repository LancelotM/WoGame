package com.unicom.game.center.model;

import java.io.Serializable;

/**
 * @author Alex Yin
 * 
 * @Date Jun 21, 2014
 */
public class GameInfo implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private String name;
	
	private String icon;
	
	private String clickThrough;
	
	private String downloadCount;
	
	private String date;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getIcon() {
		return icon;
	}

	public void setIcon(String icon) {
		this.icon = icon;
	}

	public String getClickThrough() {
		return clickThrough;
	}

	public void setClickThrough(String clickThrough) {
		this.clickThrough = clickThrough;
	}

	public String getDownloadCount() {
		return downloadCount;
	}

	public void setDownloadCount(String downloadCount) {
		this.downloadCount = downloadCount;
	}

	public String getDate() {
		return date;
	}

	public void setDate(String date) {
		this.date = date;
	}		

}
