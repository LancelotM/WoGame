package com.unicom.game.center.vo;

import com.fasterxml.jackson.annotation.JsonProperty;

public class Banner {
    @JsonProperty("link_id")
    private String linkId;
    @JsonProperty("res_type")
    private int resType;
    @JsonProperty("banner_url")
    private String bannerUrl;
    @JsonProperty("external_url")
    private String externalUrl;

    public String getExternalUrl() {
        return externalUrl;
    }

    public void setExternalUrl(String externalUrl) {
        this.externalUrl = externalUrl;
    }

    public String getBannerUrl() {

        return bannerUrl;
    }

    public void setBannerUrl(String bannerUrl) {
        this.bannerUrl = bannerUrl;
    }

    public int getResType() {

        return resType;
    }

    public void setResType(int resType) {
        this.resType = resType;
    }

    public String getLinkId() {

        return linkId;
    }

    public void setLinkId(String linkId) {
        this.linkId = linkId;
    }
}
