package com.unicom.game.center.vo;

import com.fasterxml.jackson.annotation.JsonProperty;

public class ActivityInfoListVo extends BaseWogameVo{

    @JsonProperty("data")
    private ActivityInfoVo activityInfoVo;

    public ActivityInfoVo getActivityInfoVo() {
        return activityInfoVo;
    }

    public void setActivityInfoVo(ActivityInfoVo activityInfoVo) {
        this.activityInfoVo = activityInfoVo;
    }
}
