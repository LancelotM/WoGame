package com.unicom.game.center.persistence.db.domain;

import java.io.Serializable;

public class PackageInfoKey implements Serializable{
	
	private static final long serialVersionUID = -2127956450923443152L;
	private String channelId;
	private String appId;
	

	public String getChannelId() {
		return channelId;
	}
	public void setChannelId(String channelId) {
		this.channelId = channelId;
	}
	public String getAppId() {
		return appId;
	}
	public void setAppId(String appId) {
		this.appId = appId;
	}

	
}
