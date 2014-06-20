package com.unicom.game.center.db.domain;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="page_map")
public class PageMapDomain implements Serializable{

	
	private static final long serialVersionUID = 1L;
	private int pageId;
	private String pageDesc;
	
	@Id
	@Column(name="page_id")
	public int getPageId() {
		return pageId;
	}
	
	public void setPageId(int pageId) {
		this.pageId = pageId;
	}
	
	@Column(name="page_desc")
	public String getPageDesc() {
		return pageDesc;
	}
	
	public void setPageDesc(String pageDesc) {
		this.pageDesc = pageDesc;
	}
	
}
