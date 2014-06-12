package com.unicom.game.center.persistence.db.domain;

import java.io.Serializable;
import java.sql.Date;

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
	private int id;
	private int newUserCount;
	private int oldUserCount;
	private Date dateCreated;
	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name="id")
	public int getId() {
		return id;
	}
	
	public void setId(int id) {
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
	
	

}
