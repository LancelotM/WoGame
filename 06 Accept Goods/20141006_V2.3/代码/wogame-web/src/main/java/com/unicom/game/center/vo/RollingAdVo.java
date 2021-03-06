package com.unicom.game.center.vo;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * Created by jession on 14-6-20.
 */
public class RollingAdVo {
    @JsonProperty("version_name")
    private String versionName;
    @JsonProperty("banner_url")
    private String bannerUrl;
    @JsonProperty("title")
    private String title;
    @JsonProperty("package_name")
    private String packageName;
    @JsonProperty("link_id")
    private String linkId;
    @JsonProperty("res_type")
    private String resType;

    public String getVersionName() {
        return versionName;
    }

    public void setVersionName(String versionName) {
        this.versionName = versionName;
    }

    public String getBannerUrl() {
        return bannerUrl;
    }

    public void setBannerUrl(String bannerUrl) {
        this.bannerUrl = bannerUrl;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getPackageName() {
        return packageName;
    }

    public void setPackageName(String packageName) {
        this.packageName = packageName;
    }

    public String getLinkId() {
        return linkId;
    }

    public void setLinkId(String linkId) {
        this.linkId = linkId;
    }

    public String getResType() {
        return resType;
    }

    public void setResType(String resType) {
        this.resType = resType;
    }
}

