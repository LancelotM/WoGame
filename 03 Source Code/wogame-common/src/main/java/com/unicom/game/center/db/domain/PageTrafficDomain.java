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
	private int pageId;
	private int channelId;
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
	
	@Column(name="page_id")
	public int getPageId() {
		return pageId;
	}
	
	public void setPageId(int pageId) {
		this.pageId = pageId;
	}
	
	@Column(name="channel_id")
	public int getChannelId() {
		return channelId;
	}
	
	public void setChannelId(int channelId) {
		this.channelId = channelId;
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
	
}
