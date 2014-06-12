package com.unicom.game.center.persistence.db.domain;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="status_map")
public class StatusMapDomain implements Serializable{

	
	private static final long serialVersionUID = 1L;
	private int statusId;
	private String status;
	
	@Id
	@Column(name="status_id")
	public int getStatusId() {
		return statusId;
	}
	
	public void setStatusId(int statusId) {
		this.statusId = statusId;
	}
	
	@Column(name="status")
	public String getStatus() {
		return status;
	}
	
	
	public void setStatus(String status) {
		this.status = status;
	}
	
	
	

}
