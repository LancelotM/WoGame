package com.unicom.game.center.vo;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

public class NetGameServerVo {

    @JsonProperty("total_num")
    private long totalNum;

    @JsonProperty("items")
    private List<NetGameServerItemVo> netGameServerItemVoList;

    @JsonProperty("section_name")
    private String sectionName;

    public String getSectionName() {
        return sectionName;
    }

    public void setSectionName(String sectionName) {
        this.sectionName = sectionName;
    }

    public List<NetGameServerItemVo> getNetGameServerItemVoList() {

        return netGameServerItemVoList;
    }

    public void setNetGameServerItemVoList(List<NetGameServerItemVo> netGameServerItemVoList) {
        this.netGameServerItemVoList = netGameServerItemVoList;
    }

    public long getTotalNum() {

        return totalNum;
    }

    public void setTotalNum(long totalNum) {
        this.totalNum = totalNum;
    }
}
