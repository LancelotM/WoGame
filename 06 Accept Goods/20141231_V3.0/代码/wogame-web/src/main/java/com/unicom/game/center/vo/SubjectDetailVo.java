package com.unicom.game.center.vo;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

public class SubjectDetailVo {

    @JsonProperty("banner_url")
    private String bannerUrl;

    @JsonProperty("description")
    private String description;

    @JsonProperty("id")
    private int id;

    @JsonProperty("items")
    private List<SubjectDetailItemVo> subjectDetailItemVoList;

    @JsonProperty("title")
    private String title;

    @JsonProperty("total_num")
    private long totalNum;

    public long getTotalNum() {
        return totalNum;
    }

    public void setTotalNum(long totalNum) {
        this.totalNum = totalNum;
    }

    public String getTitle() {

        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public List<SubjectDetailItemVo> getSubjectDetailItemVoList() {

        return subjectDetailItemVoList;
    }

    public void setSubjectDetailItemVoList(List<SubjectDetailItemVo> subjectDetailItemVoList) {
        this.subjectDetailItemVoList = subjectDetailItemVoList;
    }

    public int getId() {

        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getDescription() {

        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getBannerUrl() {

        return bannerUrl;
    }

    public void setBannerUrl(String bannerUrl) {
        this.bannerUrl = bannerUrl;
    }
}
