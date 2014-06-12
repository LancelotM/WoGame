package com.unicom.wogame.vo;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

/**
 * Created by jession on 14-6-11.
 */
public class CategoryListVo extends BaseWogameVo {

    @JsonProperty("category_list")
    private List<CategoryItemVo> categoryList;

    public List<CategoryItemVo> getCategoryList() {
        return categoryList;
    }

    public void setCategoryList(List<CategoryItemVo> categoryList) {
        this.categoryList = categoryList;
    }
}

class CategoryItemVo {
    @JsonProperty("category_title")
    private String categoryTitle;
    private int sort;
    private String source;

    @JsonProperty("update_time")
    private long updateTime;
    private int status;
    private String description;

    @JsonProperty("event_type")
    private int eventType;
    @JsonProperty("category_time")
    private long categoryTime;
    @JsonProperty("recommend_type")
    private int recommendType;
    @JsonProperty("icon_url")
    private String iconUrl;
    @JsonProperty("category_id")
    private int categoryId;
    @JsonProperty("app_count")
    private int appCount;

    public String getCategoryTitle() {
        return categoryTitle;
    }

    public void setCategoryTitle(String categoryTitle) {
        this.categoryTitle = categoryTitle;
    }

    public int getSort() {
        return sort;
    }

    public void setSort(int sort) {
        this.sort = sort;
    }

    public String getSource() {
        return source;
    }

    public void setSource(String source) {
        this.source = source;
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

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getEventType() {
        return eventType;
    }

    public void setEventType(int eventType) {
        this.eventType = eventType;
    }

    public long getCategoryTime() {
        return categoryTime;
    }

    public void setCategoryTime(long categoryTime) {
        this.categoryTime = categoryTime;
    }

    public int getRecommendType() {
        return recommendType;
    }

    public void setRecommendType(int recommendType) {
        this.recommendType = recommendType;
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
}