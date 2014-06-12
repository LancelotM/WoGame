package com.unicom.game.center.persistence.db.domain;

import java.io.Serializable;
import java.sql.Date;

import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name="package_info")
public class PackageInfoDomain implements Serializable{

	
	private static final long serialVersionUID = 1L;
	private PackageInfoKey key;
	private Date dateCreated;
	private Date dateModified;

	@EmbeddedId
	public PackageInfoKey getKey() {
		return key;
	}

	public void setKey(PackageInfoKey key) {
		this.key = key;
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
