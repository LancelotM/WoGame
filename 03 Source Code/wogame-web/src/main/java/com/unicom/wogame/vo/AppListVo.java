package com.unicom.wogame.vo;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

/**
 * Created by jession on 14-6-20.
 */
public class AppListVo {

    private int sort;
    @JsonProperty("update_time")
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
