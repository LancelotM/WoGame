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
@Table(name="download_info")
public class DownloadInfoDomain implements Serializable{

	
	private static final long serialVersionUID = 1L;
	private int id;
	private int channelId;
	private String productId;
	private int downloadCount;
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
	
	@Column(name="channel_id")
	public int getChannelId() {
		return channelId;
	}
	
	public void setChannelId(int channelId) {
		this.channelId = channelId;
	}
	
	@Column(name="product_id")
	public String getProductId() {
		return productId;
	}
	
	public void setProductId(String productId) {
		this.productId = productId;
	}
	
	@Column(name="download_count")
	public int getDownloadCount() {
		return downloadCount;
	}
	
	public void setDownloadCount(int downloadCount) {
		this.downloadCount = downloadCount;
	}
	
	@Column(name="date_created")
	public Date getDateCreated() {
		return dateCreated;
	}
	
	public void setDateCreated(Date dateCreated) {
		this.dateCreated = dateCreated;
	}

}
