package com.unicom.game.center.db.domain;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name="game_traffic")
public class GameTrafficDomain implements Serializable{

	
	private static final long serialVersionUID = 1L;
	
	private Integer id;
	private ProductDomain product;
	private int channelId;
	private int sort;
	private int clickThrough;
	private int downloadCount;
	private Date dateCreated;
	private boolean flag;
	
	@Id  
	@GeneratedValue(strategy=GenerationType.IDENTITY)   
	@Column(name = "id")
	public Integer getId() {
		return id;
	}
	
	public void setId(Integer id) {
		this.id = id;
	}
	
	@ManyToOne
	@JoinColumn(name = "product_id")	
	public ProductDomain getProduct() {
		return product;
	}

	public void setProduct(ProductDomain product) {
		this.product = product;
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
	
	@Column(name="banner_flag")
	public boolean getFlag() {
		return flag;
	}

	public void setFlag(boolean flag) {
		this.flag = flag;
	}
	
}
