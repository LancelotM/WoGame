package com.unicom.game.center.vo;

import com.fasterxml.jackson.annotation.JsonProperty;

public class SubjectListVo extends BaseWogameVo{

    @JsonProperty("data")
    private SubjectVo subjectList;

    public SubjectVo getSubjectList() {
        return subjectList;
    }

    public void setSubjectList(SubjectVo subjectList) {
        this.subjectList = subjectList;
    }
}
