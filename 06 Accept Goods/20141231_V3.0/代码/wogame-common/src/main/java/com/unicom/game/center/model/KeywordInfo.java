package com.unicom.game.center.model;

import java.io.Serializable;

/**
 * @author Alex Yin
 * 
 * @Date 2014-6-23
 */
public class KeywordInfo implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private String keyword;
	
	private int count;

	public String getKeyword() {
		return keyword;
	}

	public void setKeyword(String keyword) {
		this.keyword = keyword;
	}

	public int getCount() {
		return count;
	}

	public void setCount(int count) {
		this.count = count;
	}
	
}
