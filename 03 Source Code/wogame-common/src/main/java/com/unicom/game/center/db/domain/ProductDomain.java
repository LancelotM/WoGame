package com.unicom.game.center.db.domain;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="product")
public class ProductDomain implements Serializable{

	
	private static final long serialVersionUID = 1L;
	private String productId;
	private String productName;
	private String productIcon;
	private Date dateCreated;
	
	
	@Id
	@Column(name="product_id")
	public String getProductId() {
		return productId;
	}
	
	public void setProductId(String productId) {
		this.productId = productId;
	}
	@Column(name="product_name")
	public String getProductName() {
		return productName;
	}
	
	public void setProductName(String productName) {
		this.productName = productName;
	}
	
	@Column(name="product_icon")
	public String getProductIcon() {
		return productIcon;
	}
	
	public void setProductIcon(String productIcon) {
		this.productIcon = productIcon;
	}
	
	@Column(name="date_created")
	public Date getDateCreated() {
		return dateCreated;
	}
	
	public void setDateCreated(Date dateCreated) {
		this.dateCreated = dateCreated;
	}
		
}
