package com.unicom.game.center.vo;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

public class SubjectVo {

    @JsonProperty("total_num")
    private long totalNum;

    @JsonProperty("items")
    private List<SubjectItemVo> subjectItemList;

    public List<SubjectItemVo> getSubjectItemList() {
        return subjectItemList;
    }

    public void setSubjectItemList(List<SubjectItemVo> subjectItemList) {
        this.subjectItemList = subjectItemList;
    }

    public long getTotalNum() {

        return totalNum;
    }

    public void setTotalNum(long totalNum) {
        this.totalNum = totalNum;
    }
}
