package com.unicom.game.center.vo;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

public class NetGameInfoVo {

    @JsonProperty("total_num")
    private long totalNum;

    @JsonProperty("items")
    private List<NetGameInfoItemVo> netGameInfoItemVoList;

    @JsonProperty("section_name")
    private String sectionName;

    public String getSectionName() {
        return sectionName;
    }

    public void setSectionName(String sectionName) {
        this.sectionName = sectionName;
    }

    public List<NetGameInfoItemVo> getNetGameInfoItemVoList() {
        return netGameInfoItemVoList;
    }

    public void setNetGameInfoItemVoList(List<NetGameInfoItemVo> netGameInfoItemVoList) {
        this.netGameInfoItemVoList = netGameInfoItemVoList;
    }

    public long getTotalNum() {

        return totalNum;
    }

    public void setTotalNum(long totalNum) {
        this.totalNum = totalNum;
    }
}
