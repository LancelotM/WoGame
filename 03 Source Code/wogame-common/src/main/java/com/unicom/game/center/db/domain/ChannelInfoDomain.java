package com.unicom.game.center.db.domain;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name="channel_info")
public class ChannelInfoDomain implements Serializable{

	
	private static final long serialVersionUID = 1L;
	private int channelId;
	private String channelName;
	private StatusMapDomain status;
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
	
	@Column(name="channel_name")
	public String getChannelName() {
		return channelName;
	}
	
	public void setChannelName(String channelName) {
		this.channelName = channelName;
	}
	
	@ManyToOne
	@JoinColumn(name = "status_id")
	public StatusMapDomain getStatus() {
		return status;
	}

	public void setStatus(StatusMapDomain status) {
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