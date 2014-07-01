package com.unicom.game.center.db.domain;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="channel_info")
public class ChannelInfoDomain implements Serializable{

	
	private static final long serialVersionUID = 1L;
	private int channelId;
	private String channelCode;	
	private String channelName;
	private String cpId;
	private String wapToken;
	private String logToken;
	private boolean status;
	private Date dateCreated;
	private Date dateModified;
	
	
	@Id
	@Column(name="channel_id")
	public int getChannelId() {
		return channelId;
	}
	
	public void setChannelId(int channelId) {
		this.channelId = channelId;
	}
	
	@Column(name="channel_code")
	public String getChannelCode() {
		return channelCode;
	}

	public void setChannelCode(String channelCode) {
		this.channelCode = channelCode;
	}

	@Column(name="channel_name")
	public String getChannelName() {
		return channelName;
	}
	
	public void setChannelName(String channelName) {
		this.channelName = channelName;
	}
	
	@Column(name="cp_id")
	public String getCpId() {
		return cpId;
	}

	public void setCpId(String cpId) {
		this.cpId = cpId;
	}

	@Column(name="wap_token")
	public String getWapToken() {
		return wapToken;
	}

	public void setWapToken(String wapToken) {
		this.wapToken = wapToken;
	}

	@Column(name="log_token")
	public String getLogToken() {
		return logToken;
	}

	public void setLogToken(String logToken) {
		this.logToken = logToken;
	}

	@Column(name = "status")
	public boolean isStatus() {
		return status;
	}

	public void setStatus(boolean status) {
		this.status = status;
	}

	@Column(name="date_created")
	public Date getDateCreated() {
		return dateCreated;
	}	

	public void setDateCreated(Date dateCreated) {
		this.dateCreated = dateCreated;
	}
	
	@Column(name="date_modified")
	public Date getDateModified() {
		return dateModified;
	}
	public void setDateModified(Date dateModified) {
		this.dateModified = dateModified;
	}
	
}
