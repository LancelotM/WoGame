package com.unicom.game.center.vo;

import com.fasterxml.jackson.annotation.JsonProperty;

public class SubjectDetailListVo  extends BaseWogameVo{

    @JsonProperty("data")
    private SubjectDetailVo subjectDetailList;

    public SubjectDetailVo getSubjectDetailList() {
        return subjectDetailList;
    }

    public void setSubjectDetailList(SubjectDetailVo subjectDetailList) {
        this.subjectDetailList = subjectDetailList;
    }
}
