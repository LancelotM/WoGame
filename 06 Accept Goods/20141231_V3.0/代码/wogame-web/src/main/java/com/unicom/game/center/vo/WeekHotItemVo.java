package com.unicom.game.center.vo;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

/**
 * Created by jession on 14-6-20.
 */
public class WeekHotItemVo {
    @JsonProperty("total_num")
    private String totalNum;

    @JsonProperty("items")
    private List<WeekHotItemListVo> weekHotItemList;

    public List<WeekHotItemListVo> getWeekHotItemList() {
        return weekHotItemList;
    }

    public void setWeekHotItemList(List<WeekHotItemListVo> weekHotItemList) {
        this.weekHotItemList = weekHotItemList;
    }

    public String getTotalNum() {

        return totalNum;
    }

    public void setTotalNum(String totalNum) {
        this.totalNum = totalNum;
    }
}
