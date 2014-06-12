package com.unicom.wogame.vo;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

/**
 * Created by jession on 14-6-11.
 */
public class ShowCategoryVo extends BaseWogameVo {

    private int sort;
    private int status;

    @JsonProperty("event_type")
    private int eventType;

    @JsonProperty("icon_url")
    private String iconUrl;

    @JsonProperty("category_id")
    private int categoryId;

    @JsonProperty("app_count")
    private int appCount;

    @JsonProperty("category_date")
    private long categoryDate;

    @JsonProperty("category_title")
    private String categoryTitle;

    @JsonProperty("update_time")
    private long updateTime;

    private String description;

    @JsonProperty("recommend_type")
    private int recommendType;

    @JsonProperty("app_list")
    private List<CategoryGameVo> appList;

    public int getSort() {
        return sort;
    }

    public void setSort(int sort) {
        this.sort = sort;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public int getEventType() {
        return eventType;
    }

    public void setEventType(int eventType) {
        this.eventType = eventType;
    }

    public String getIconUrl() {
        return iconUrl;
    }

    public void setIconUrl(String iconUrl) {
        this.iconUrl = iconUrl;
    }

    public int getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(int categoryId) {
        this.categoryId = categoryId;
    }

    public int getAppCount() {
        return appCount;
    }

    public void setAppCount(int appCount) {
        this.appCount = appCount;
    }

    public long getCategoryDate() {
        return categoryDate;
    }

    public void setCategoryDate(long categoryDate) {
        this.categoryDate = categoryDate;
    }

    public String getCategoryTitle() {
        return categoryTitle;
    }

    public void setCategoryTitle(String categoryTitle) {
        this.categoryTitle = categoryTitle;
    }

    public long getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(long updateTime) {
        this.updateTime = updateTime;
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

    public List<CategoryGameVo> getAppList() {
        return appList;
    }

    public void setAppList(List<CategoryGameVo> appList) {
        this.appList = appList;
    }
}

class CategoryGameVo {
    /*
    "version_name": "1.1.4",                  # 版本名称
    "apk_id": 8566,                          # 编号
    "product_id": "189610",                   # 产品ID(ZTE游戏ID)
    "event_type": 0,                         # 事件类型 0：进入详情 1：直接下载 2：进入专题
    "package_name": "com.popcap.pvz2cuhd",   # 游戏包名
    "icon_url": "http://xxxxx/large.png",         # 游戏Icon图标
    "apk_size": 52994,                       # 游戏包大小 单位：kb
    "app_name": "植物大战僵尸2高清版",     # 游戏名称
    "version_code": "1.1.4",                   # 游戏版本号
    "rate": 3.0,                              # 游戏星级
    "update_time": 1399259805000,            # 更新时间
    "description": "",                         # 游戏描述
    "recommend_type": 0                    # 推荐类型
*/

    @JsonProperty("version_name")
    private String versionName;

    @JsonProperty("apk_id")
    private long apkId;

    @JsonProperty("product_id")
    private String productId;

    @JsonProperty("event_type")
    private int eventType;

    @JsonProperty("package_name")
    private String packageName;

    @JsonProperty("icon_url")
    private String iconUrl;

    @JsonProperty("apk_size")
    private String apkSize;

    @JsonProperty("app_name")
    private String appName;

    @JsonProperty("version_code")
    private String versionCode;
    private float rate;

    @JsonProperty("update_time")
    private long updateTime;
    private String description;

    @JsonProperty("recommend_type")
    private int recommendType;

    public String getPackageName() {
        return packageName;
    }

    public void setPackageName(String packageName) {
        this.packageName = packageName;
    }

    public String getVersionName() {
        return versionName;
    }

    public void setVersionName(String versionName) {
        this.versionName = versionName;
    }

    public long getApkId() {
        return apkId;
    }

    public void setApkId(long apkId) {
        this.apkId = apkId;
    }

    public String getProductId() {
        return productId;
    }

    public void setProductId(String productId) {
        this.productId = productId;
    }

    public String getIconUrl() {
        return iconUrl;
    }

    public void setIconUrl(String iconUrl) {
        this.iconUrl = iconUrl;
    }

    public String getApkSize() {
        return apkSize;
    }

    public void setApkSize(String apkSize) {
        this.apkSize = apkSize;
    }

    public String getAppName() {
        return appName;
    }

    public void setAppName(String appName) {
        this.appName = appName;
    }

    public int getEventType() {
        return eventType;
    }

    public void setEventType(int eventType) {
        this.eventType = eventType;
    }

    public String getVersionCode() {
        return versionCode;
    }

    public void setVersionCode(String versionCode) {
        this.versionCode = versionCode;
    }

    public float getRate() {
        return rate;
    }

    public void setRate(float rate) {
        this.rate = rate;
    }

    public long getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(long updateTime) {
        this.updateTime = updateTime;
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
}
