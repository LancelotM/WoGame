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
@Table(name="hot_game_traffic")
public class HotGameTrafficDomain implements Serializable{

	
	private static final long serialVersionUID = 1L;
	
	private int id;
	private int productId;
	private int channelId;
	private int clickThrough;
	private Date dateCreated;
	private boolean flag;
	
	@Id  
	@GeneratedValue(strategy=GenerationType.AUTO)   
	@Column(name = "id")
	public int getId() {
		return id;
	}
	
	public void setId(int id) {
		this.id = id;
	}
	
	@Column(name="product_id")
	public int getProductId() {
		return productId;
	}
	
	public void setProductId(int productId) {
		this.productId = productId;
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
	
	@Column(name="flag")
	public boolean getFlag() {
		return flag;
	}

	public void setFlag(boolean flag) {
		this.flag = flag;
	}
	
	

}
