package com.unicom.game.center.vo;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

public class RecommendDataListVo {

    @JsonProperty("total_num")
    private long totalNum;

    @JsonProperty("items")
    private List<RecommendDataVo> recommendData;

    @JsonProperty("section_name")
    private String sectionName;

    public List<RecommendDataVo> getRecommendData() {
        return recommendData;
    }

    public void setRecommendData(List<RecommendDataVo> recommendData) {
        this.recommendData = recommendData;
    }

    public Long getTotalNum() {
        return totalNum;
    }

    public void setTotalNum(Long totalNum) {
        this.totalNum = totalNum;
    }


    public String getSectionName() {
        return sectionName;
    }

    public void setSectionName(String sectionName) {
        this.sectionName = sectionName;
    }

}
