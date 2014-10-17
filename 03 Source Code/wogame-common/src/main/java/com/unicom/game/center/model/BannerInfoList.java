package com.unicom.game.center.model;

import java.io.Serializable;
import java.util.List;

/**
 * @author Alex
 *
 */
public class BannerInfoList implements Serializable {

	private static final long serialVersionUID = 1L;
	
	private BannerInfo topAD;
	
	private List<BannerInfo> topBanner;
	
	private List<BannerInfo> activityModule;
	
	private List<BannerInfo> activityBanner;
	
	private BannerInfo bottomAD;

	public BannerInfo getTopAD() {
		return topAD;
	}

	public void setTopAD(BannerInfo topAD) {
		this.topAD = topAD;
	}

	public List<BannerInfo> getTopBanner() {
		return topBanner;
	}

	public void setTopBanner(List<BannerInfo> topBanner) {
		this.topBanner = topBanner;
	}

	public List<BannerInfo> getActivityModule() {
		return activityModule;
	}

	public void setActivityModule(List<BannerInfo> activityModule) {
		this.activityModule = activityModule;
	}

	public List<BannerInfo> getActivityBanner() {
		return activityBanner;
	}

	public void setActivityBanner(List<BannerInfo> activityBanner) {
		this.activityBanner = activityBanner;
	}

	public BannerInfo getBottomAD() {
		return bottomAD;
	}

	public void setBottomAD(BannerInfo bottomAD) {
		this.bottomAD = bottomAD;
	}

}
