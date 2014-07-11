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
@Table(name="ad_traffic")
public class AdTrafficDomain implements Serializable{

	
	private static final long serialVersionUID = 1L;
	
	private Integer id;
	private int channelId;
	private int adId;
	private int adType;
	private int sort;
	private int clickThrough;
	private Date dateCreated;

	
	@Id  
	@GeneratedValue(strategy=GenerationType.IDENTITY)   
	@Column(name = "id")
	public Integer getId() {
		return id;
	}
	
	public void setId(Integer id) {
		this.id = id;
	}
		
	@Column(name="channel_id")
	public int getChannelId() {
		return channelId;
	}

	public void setChannelId(int channelId) {
		this.channelId = channelId;
	}
	
	@Column(name="sort")
	public int getSort() {
		return sort;
	}

	public void setSort(int sort) {
		this.sort = sort;
	}

	@Column(name="click_through")
	public int getClickThrough() {
		return clickThrough;
	}
	
	public void setClickThrough(int clickThrough) {
		this.clickThrough = clickThrough;
	}
	
	@Column(name="date_created")
	public Date getDateCreated() {
		return dateCreated;
	}
	
	public void setDateCreated(Date dateCreated) {
		this.dateCreated = dateCreated;
	}
	
	@Column(name="ad_id")
	public int getAdId() {
		return adId;
	}

	public void setAdId(int adId) {
		this.adId = adId;
	}

	@Column(name="ad_type")
	public int getAdType() {
		return adType;
	}

	public void setAdType(int adType) {
		this.adType = adType;
	}	
}
