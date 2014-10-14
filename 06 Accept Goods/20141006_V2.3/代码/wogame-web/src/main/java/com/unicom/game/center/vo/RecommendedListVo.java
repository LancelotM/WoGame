package com.unicom.game.center.vo;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

/**
 * Created by jession on 14-6-11.
 */
public class RecommendedListVo extends BaseWogameVo {

    @JsonProperty("extra")
    private List<IndexExtraVo> extraList;

    @JsonProperty("extra1")
    private List<IndexExtra1Vo> extra1VoList;

    @JsonProperty("section_name")
    private String sectionName;

    @JsonProperty("app_list")
    private List<AppListVo> appList;

    public List<AppListVo> getAppList() {
        return appList;
    }

    public void setAppList(List<AppListVo> appList) {
        this.appList = appList;
    }

    public List<IndexExtraVo> getExtraList() {
        return extraList;
    }

    public void setExtraList(List<IndexExtraVo> extraList) {
        this.extraList = extraList;
    }

    public List<IndexExtra1Vo> getExtra1VoList() {
        return extra1VoList;
    }

    public void setExtra1VoList(List<IndexExtra1Vo> extra1VoList) {
        this.extra1VoList = extra1VoList;
    }

    public String getSectionName() {
        return sectionName;
    }

    public void setSectionName(String sectionName) {
        this.sectionName = sectionName;
    }
}

class IndexExtraVo {
    @JsonProperty("section_name")
    private String sectionName;

    @JsonProperty("items")
    private List<IndexExtraVoItem> items;

    public String getSectionName() {
        return sectionName;
    }

    public void setSectionName(String sectionName) {
        this.sectionName = sectionName;
    }

    public List<IndexExtraVoItem> getItems() {
        return items;
    }

    public void setItems(List<IndexExtraVoItem> items) {
        this.items = items;
    }
}

class IndexExtraVoItem {
    @JsonProperty("banner_url")
    private String bannerUrl;

    private String title;
    @JsonProperty("product_id")
    private Long productId;

    public String getBannerUrl() {
        return bannerUrl;
    }

    public void setBannerUrl(String bannerUrl) {
        this.bannerUrl = bannerUrl;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Long getProductId() {
        return productId;
    }

    public void setProductId(Long productId) {
        this.productId = productId;
    }
}

class IndexExtra1Vo {
    private Long id;
    private String title;
    private String tips;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getTips() {
        return tips;
    }

    public void setTips(String tips) {
        this.tips = tips;
    }
}
