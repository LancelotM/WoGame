package com.unicom.game.center.vo;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

public class ActivityInfoVo {

    @JsonProperty("total_num")
    private long totalNum;

    @JsonProperty("items")
    private List<ActivityInfoItemVo> activityInfoItemVoList;

    public List<ActivityInfoItemVo> getActivityInfoItemVoList() {
        return activityInfoItemVoList;
    }

    public void setActivityInfoItemVoList(List<ActivityInfoItemVo> activityInfoItemVoList) {
        this.activityInfoItemVoList = activityInfoItemVoList;
    }

    public long getTotalNum() {

        return totalNum;
    }

    public void setTotalNum(long totalNum) {
        this.totalNum = totalNum;
    }
}
