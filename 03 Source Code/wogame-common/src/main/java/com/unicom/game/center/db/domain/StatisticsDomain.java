package com.unicom.game.center.db.domain;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * @author Alex
 *
 */
@Entity
@Table(name = "statistics")
public class StatisticsDomain implements Serializable {

	private static final long serialVersionUID = 8201851094435250244L;
	
	private Integer id;
	
	private int channelId;
	
	private int homepagePV;
	
	private int homepageUV;
	
	private int changwanPV;
	
	private int changwanUV;
	
	private Date dateCreated;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="id", unique = true, nullable = false)
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

	@Column(name="homepage_pv")
	public int getHomepagePV() {
		return homepagePV;
	}

	public void setHomepagePV(int homepagePV) {
		this.homepagePV = homepagePV;
	}

	@Column(name="homepage_uv")
	public int getHomepageUV() {
		return homepageUV;
	}

	public void setHomepageUV(int homepageUV) {
		this.homepageUV = homepageUV;
	}

	@Column(name="changwan_pv")
	public int getChangwanPV() {
		return changwanPV;
	}

	public void setChangwanPV(int changwanPV) {
		this.changwanPV = changwanPV;
	}

	@Column(name="changwan_uv")
	public int getChangwanUV() {
		return changwanUV;
	}

	public void setChangwanUV(int changwanUV) {
		this.changwanUV = changwanUV;
	}

	@Column(name="date_created")
	public Date getDateCreated() {
		return dateCreated;
	}

	public void setDateCreated(Date dateCreated) {
		this.dateCreated = dateCreated;
	}
	
	
	
}
