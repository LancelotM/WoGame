package com.unicom.game.center.vo;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

/**
 * Created by jession on 14-6-17.
 */
public class NewVo {

    @JsonProperty("total_num")
    private Long totalNum;

    @JsonProperty("items")
    private List<NewItemListVo> newItemListVo;

    public List<NewItemListVo> getNewItemListVo() {
        return newItemListVo;
    }

    public void setNewItemListVo(List<NewItemListVo> newItemListVo) {
        this.newItemListVo = newItemListVo;
    }

    public Long getTotalNum() {

        return totalNum;
    }

    public void setTotalNum(Long totalNum) {
        this.totalNum = totalNum;
    }
}
