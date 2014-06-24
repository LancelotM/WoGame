package com.unicom.game.center.vo;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

/**
 * Created by jession on 14-6-11.
 */
public class NewListVo extends BaseWogameVo {

    @JsonProperty("app_list")
    private List<NewVo> appList;

    public List<NewVo> getAppList() {
        return appList;
    }

    public void setAppList(List<NewVo> appList) {
        this.appList = appList;
    }
}

