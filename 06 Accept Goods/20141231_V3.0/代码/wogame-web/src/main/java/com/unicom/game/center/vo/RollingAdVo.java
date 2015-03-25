package com.unicom.game.center.vo;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * Created by jession on 14-6-20.
 */
public class RollingAdVo {
    @JsonProperty("title")
    private String title;
    @JsonProperty("banner")
    private Banner banner;
    @JsonProperty("corner_mark")
    private String cornerMark;

    public String getCornerMark() {
        return cornerMark;
    }

    public void setCornerMark(String cornerMark) {
        this.cornerMark = cornerMark;
    }

    public Banner getBanner() {

        return banner;
    }

    public void setBanner(Banner banner) {
        this.banner = banner;
    }

    public String getTitle() {

        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }
}

