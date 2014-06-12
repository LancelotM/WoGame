package com.unicom.wogame.vo;

import com.fasterxml.jackson.annotation.JsonProperty;
import org.springside.modules.persistence.vo.BaseVo;

import java.util.List;

/**
 * Created by jession on 14-6-11.
 */
public class RecommendedListVo extends BaseWogameVo {

    @JsonProperty("app_list")
    private List<AppListVo> appList;

    public List<AppListVo> getAppList() {
        return appList;
    }

    public void setAppList(List<AppListVo> appList) {
        this.appList = appList;
    }
}

class AppListVo {

    private int sort;
    @JsonProperty("app_list")
    private long updateTime;

    private int status;

    @JsonProperty("ad_id")
    private int adId;

    private String description;

    @JsonProperty("create_time")
    private long createTime;

    @JsonProperty("ad_type")
    private int adType;

    private List<AppVo> apps;

    public int getSort() {
        return sort;
    }

    public void setSort(int sort) {
        this.sort = sort;
    }

    public long getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(long updateTime) {
        this.updateTime = updateTime;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
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

    public long getCreateTime() {
        return createTime;
    }

    public void setCreateTime(long createTime) {
        this.createTime = createTime;
    }

    public int getAdType() {
        return adType;
    }

    public void setAdType(int adType) {
        this.adType = adType;
    }

    public List<AppVo> getApps() {
        return apps;
    }

    public void setApps(List<AppVo> apps) {
        this.apps = apps;
    }
}

class AppVo {
    @JsonProperty("ad_id")
    private String versionName;
    @JsonProperty("ad_id")
    private int apkId;

    private int sort;
    @JsonProperty("productId")
    private String productId;
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
    @JsonProperty("apk_name")
    private String apkName;
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
    @JsonProperty("recommend_tyep")
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

    public String getApkName() {
        return apkName;
    }

    public void setApkName(String apkName) {
        this.apkName = apkName;
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