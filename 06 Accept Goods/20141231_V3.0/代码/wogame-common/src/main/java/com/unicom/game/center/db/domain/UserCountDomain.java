package com.unicom.game.center.db.domain;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;


@Entity
@Table(name="user_count")
public class UserCountDomain implements Serializable{
	
	private static final long serialVersionUID = 1L;
	private Integer id;
	private int newUserCount;
	private int oldUserCount;
	private int channelId;
	private Date dateCreated;
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="id")
	public Integer getId() {
		return id;
	}
	
	public void setId(Integer id) {
		this.id = id;
	}
	
	@Column(name="new_user_count")
	public int getNewUserCount() {
		return newUserCount;
	}
	
	public void setNewUserCount(int newUserCount) {
		this.newUserCount = newUserCount;
	}
	
	@Column(name="old_user_count")
	public int getOldUserCount() {
		return oldUserCount;
	}
	
	public void setOldUserCount(int oldUserCount) {
		this.oldUserCount = oldUserCount;
	}
	
	@Column(name="date_created")
	public Date getDateCreated() {
		return dateCreated;
	}
	public void setDateCreated(Date dateCreated) {
		this.dateCreated = dateCreated;
	}

	@Column(name="channel_id")
	public int getChannelId() {
		return channelId;
	}

	public void setChannelId(int channelId) {
		this.channelId = channelId;
	}
}
