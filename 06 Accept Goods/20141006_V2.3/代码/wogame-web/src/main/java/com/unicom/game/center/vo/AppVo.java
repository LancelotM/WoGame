package com.unicom.game.center.vo;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * Created by jession on 14-6-20.
 */
public class AppVo {
    @JsonProperty("version_name")
    private String versionName;
    @JsonProperty("apk_id")
    private int apkId;

    private int sort;
    @JsonProperty("product_id")
    private String productId;

    @JsonProperty("subject_id")
    private String subjectId;

    @JsonProperty("res_id")
    private String resId;
    @JsonProperty("event_type")
    private int eventType;
    @JsonProperty("package_name")
    private String packageName;
    @JsonProperty("icon_url")
    private String iconUrl;
    @JsonProperty("subject_title")
    private String subjectTitle;
    @JsonProperty("apk_size")
    private long apkSize;
    @JsonProperty("apk_url")
    private String apkUrl;
    @JsonProperty("app_name")
    private String appName;
    @JsonProperty("version_code")
    private String versionCode;
    @JsonProperty("banner_url")
    private String bannerUrl;

    private int rate;
    @JsonProperty("update_time")
    private long updateTime;
    @JsonProperty("ad_id")
    private int adId;

    private String description;
    @JsonProperty("recommend_type")
    private int recommendType;
    @JsonProperty("app_date")
    private long appDate;

    public String getVersionName() {
        return versionName;
    }

    public void setVersionName(String versionName) {
        this.versionName = versionName;
    }

    public int getApkId() {
        return apkId;
    }

    public void setApkId(int apkId) {
        this.apkId = apkId;
    }

    public String getSubjectId() {
        return subjectId;
    }

    public void setSubjectId(String subjectId) {
        this.subjectId = subjectId;
    }

    public String getResId() {
        return resId;
    }

    public void setResId(String resId) {
        this.resId = resId;
    }

    public int getSort() {
        return sort;
    }

    public void setSort(int sort) {
        this.sort = sort;
    }

    public String getProductId() {
        return productId;
    }

    public void setProductId(String productId) {
        this.productId = productId;
    }

    public int getEventType() {
        return eventType;
    }

    public void setEventType(int eventType) {
        this.eventType = eventType;
    }

    public String getPackageName() {
        return packageName;
    }

    public void setPackageName(String packageName) {
        this.packageName = packageName;
    }

    public String getIconUrl() {
        return iconUrl;
    }

    public void setIconUrl(String iconUrl) {
        this.iconUrl = iconUrl;
    }

    public String getSubjectTitle() {
        return subjectTitle;
    }

    public void setSubjectTitle(String subjectTitle) {
        this.subjectTitle = subjectTitle;
    }

    public long getApkSize() {
        return apkSize;
    }

    public void setApkSize(long apkSize) {
        this.apkSize = apkSize;
    }

    public String getApkUrl() {
        return apkUrl;
    }

    public void setApkUrl(String apkUrl) {
        this.apkUrl = apkUrl;
    }

    public String getAppName() {
        return appName;
    }

    public void setAppName(String appName) {
        this.appName = appName;
    }

    public String getVersionCode() {
        return versionCode;
    }

    public void setVersionCode(String versionCode) {
        this.versionCode = versionCode;
    }

    public String getBannerUrl() {
        return bannerUrl;
    }

    public void setBannerUrl(String bannerUrl) {
        this.bannerUrl = bannerUrl;
    }

    public int getRate() {
        return rate;
    }

    public void setRate(int rate) {
        this.rate = rate;
    }

    public long getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(long updateTime) {
        this.updateTime = updateTime;
    }

    public int getAdId() {
        return adId;
    }

    public void setAdId(int adId) {
        this.adId = adId;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getRecommendType() {
        return recommendType;
    }

    public void setRecommendType(int recommendType) {
        this.recommendType = recommendType;
    }

    public long getAppDate() {
        return appDate;
    }

    public void setAppDate(long appDate) {
        this.appDate = appDate;
    }
}
