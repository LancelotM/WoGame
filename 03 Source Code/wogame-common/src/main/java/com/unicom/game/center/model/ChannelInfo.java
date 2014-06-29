package com.unicom.game.center.model;

import java.io.Serializable;

/**
 * @author Alex.Yin
 * @Date Jun 29, 2014
 */
public class ChannelInfo implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private int channelId;
	
	private String channelName;
	
	private String wapToken;
	
	private String logToken;

	private String date;

	public int getChannelId() {
		return channelId;
	}

	public void setChannelId(int channelId) {
		this.channelId = channelId;
	}

	public String getChannelName() {
		return channelName;
	}

	public void setChannelName(String channelName) {
		this.channelName = channelName;
	}

	public String getWapToken() {
		return wapToken;
	}

	public void setWapToken(String wapToken) {
		this.wapToken = wapToken;
	}

	public String getLogToken() {
		return logToken;
	}

	public void setLogToken(String logToken) {
		this.logToken = logToken;
	}

	public String getDate() {
		return date;
	}

	public void setDate(String date) {
		this.date = date;
	}
}
