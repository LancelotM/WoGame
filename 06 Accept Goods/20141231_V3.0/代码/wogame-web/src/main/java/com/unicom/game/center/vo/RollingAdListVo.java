package com.unicom.game.center.vo;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

/**
 * Created by jession on 14-6-11.
 */
public class RollingAdListVo extends BaseWogameVo {

    @JsonProperty("data")
    private List<RollingAdVo> data;

    public List<RollingAdVo> getData() {
        return data;
    }

    public void setData(List<RollingAdVo> data) {
        this.data = data;
    }

}
