package com.unicom.game.center.vo;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

/**
 * Created by jession on 14-6-11.
 */
public class WeekHotVo extends BaseWogameVo {

    private WeekHotItemVo data;

    public WeekHotItemVo getData() {
        return data;
    }

    public void setData(WeekHotItemVo data) {
        this.data = data;
    }
}

