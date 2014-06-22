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

