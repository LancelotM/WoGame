package com.unicom.game.center.vo;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

/**
 * Created by jession on 14-6-11.
 */
public class NewListVo extends BaseWogameVo {

    @JsonProperty("data")
    private NewVo dataList;

    public NewVo getDataList() {
        return dataList;
    }

    public void setDataList(NewVo dataList) {
        this.dataList = dataList;
    }
}

