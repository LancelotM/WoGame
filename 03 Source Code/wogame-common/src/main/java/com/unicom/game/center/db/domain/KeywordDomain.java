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
@Table(name="keyword")
public class KeywordDomain implements Serializable{

	
	private static final long serialVersionUID = 1L;
	private Integer id;
	private String keyword;
	private int count;
	private Date dateCreated;
	private Date dateModified;
	
	@Id  
	@GeneratedValue(strategy=GenerationType.IDENTITY)   
	@Column(name = "id")
	public Integer getId() {
		return id;
	}
	
	public void setId(Integer id) {
		this.id = id;
	}
	
	@Column(name="keyword")
	public String getKeyword() {
		return keyword;
	}
	
	public void setKeyword(String keyword) {
		this.keyword = keyword;
	}
	
	@Column(name="count")
	public int getCount() {
		return count;
	}
	
	public void setCount(int count) {
		this.count = count;
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
