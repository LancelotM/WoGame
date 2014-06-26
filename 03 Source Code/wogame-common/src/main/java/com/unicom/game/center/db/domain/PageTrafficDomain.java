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
 * @author Alex.Yin
 * @Date Jun 09, 2014
 */

@Entity
@Table(name = "page_traffic")
public class PageTrafficDomain implements Serializable {

	private static final long serialVersionUID = 8201851094435250244L;
	private Integer id;
	private int channelId;
	private int homepage;
	private int category;
	private int hotlist;
	private int latest;
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
	
	@Column(name="homepage")
	public int getHomepage() {
		return homepage;
	}

	public void setHomepage(int homepage) {
		this.homepage = homepage;
	}

	@Column(name="category")
	public int getCategory() {
		return category;
	}

	public void setCategory(int category) {
		this.category = category;
	}

	@Column(name="hotlist")
	public int getHotlist() {
		return hotlist;
	}

	public void setHotlist(int hotlist) {
		this.hotlist = hotlist;
	}

	@Column(name="latest")
	public int getLatest() {
		return latest;
	}

	public void setLatest(int latest) {
		this.latest = latest;
	}

	@Column(name="date_created")
	public Date getDateCreated() {
		return dateCreated;
	}
	
	public void setDateCreated(Date dateCreated) {
		this.dateCreated = dateCreated;
	}
	
}
