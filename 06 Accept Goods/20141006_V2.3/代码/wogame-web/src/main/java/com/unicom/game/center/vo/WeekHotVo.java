package com.unicom.game.center.vo;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

/**
 * Created by jession on 14-6-11.
 */
public class WeekHotVo extends BaseWogameVo {

    @JsonProperty("total_page")
    private int totalPage;

    private List<WeekHotItemVo> items;

    public int getTotalPage() {
        return totalPage;
    }

    public void setTotalPage(int totalPage) {
        this.totalPage = totalPage;
    }

    public List<WeekHotItemVo> getItems() {
        return items;
    }

    public void setItems(List<WeekHotItemVo> items) {
        this.items = items;
    }
}

