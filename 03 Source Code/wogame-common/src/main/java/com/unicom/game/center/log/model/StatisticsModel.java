package com.unicom.game.center.log.model;

import java.io.Serializable;
import java.util.HashSet;

/**
 * @author Alex
 *
 */
public class StatisticsModel implements Serializable {

	private static final long serialVersionUID = 1L;
	
	private int channelId;
	
	private HashSet<String> homepageUV;
	
	private HashSet<String> changwanUV;
		
	private int homepagePV;
	
	private int changwanPV;

	public int getChannelId() {
		return channelId;
	}

	public void setChannelId(int channelId) {
		this.channelId = channelId;
	}

	public HashSet<String> getHomepageUV() {
		return homepageUV;
	}

	public void setHomepageUV(HashSet<String> homepageUV) {
		this.homepageUV = homepageUV;
	}

	public HashSet<String> getChangwanUV() {
		return changwanUV;
	}

	public void setChangwanUV(HashSet<String> changwanUV) {
		this.changwanUV = changwanUV;
	}

	public int getHomepagePV() {
		return homepagePV;
	}

	public void setHomepagePV(int homepagePV) {
		this.homepagePV = homepagePV;
	}

	public int getChangwanPV() {
		return changwanPV;
	}

	public void setChangwanPV(int changwanPV) {
		this.changwanPV = changwanPV;
	}

}
