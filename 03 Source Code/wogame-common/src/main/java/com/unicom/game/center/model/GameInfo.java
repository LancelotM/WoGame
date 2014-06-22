package com.unicom.game.center.model;

import java.io.Serializable;
import java.util.Date;

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
	
	private int clickThrough;
	
	private int downloadCount;
	
	private Date date;

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

	public int getClickThrough() {
		return clickThrough;
	}

	public void setClickThrough(int clickThrough) {
		this.clickThrough = clickThrough;
	}

	public int getDownloadCount() {
		return downloadCount;
	}

	public void setDownloadCount(int downloadCount) {
		this.downloadCount = downloadCount;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}		

}
