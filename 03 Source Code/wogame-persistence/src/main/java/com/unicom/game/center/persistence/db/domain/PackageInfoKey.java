package com.unicom.game.center.persistence.db.domain;

import java.io.Serializable;

public class PackageInfoKey implements Serializable{
	
	private static final long serialVersionUID = -2127956450923443152L;
	private int channelId;
	private int productId;
	public int getChannelId() {
		return channelId;
	}
	public void setChannelId(int channelId) {
		this.channelId = channelId;
	}
	public int getProductId() {
		return productId;
	}
	public void setProductId(int productId) {
		this.productId = productId;
	}
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + channelId;
		result = prime * result + productId;
		return result;
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		PackageInfoKey other = (PackageInfoKey) obj;
		if (channelId != other.channelId)
			return false;
		if (productId != other.productId)
			return false;
		return true;
	}
	
	

}
