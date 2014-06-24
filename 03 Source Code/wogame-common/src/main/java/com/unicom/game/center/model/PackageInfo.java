package com.unicom.game.center.model;

import java.io.Serializable;

/**
 * @author Alex Yin
 * 
 * @Date Jun 23, 2014
 */
public class PackageInfo implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private String channelCode;
	
	private String appId;
	
	private String apkOnlineTime;

	public String getChannelCode() {
		return channelCode;
	}

	public void setChannelCode(String channelCode) {
		this.channelCode = channelCode;
	}

	public String getAppId() {
		return appId;
	}

	public void setAppId(String appId) {
		this.appId = appId;
	}

	public String getApkOnlineTime() {
		return apkOnlineTime;
	}

	public void setApkOnlineTime(String apkOnlineTime) {
		this.apkOnlineTime = apkOnlineTime;
	}

	
}
