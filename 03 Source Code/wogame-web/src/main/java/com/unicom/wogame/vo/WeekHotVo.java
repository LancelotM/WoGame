package com.unicom.wogame.vo;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

/**
 * Created by jession on 14-6-11.
 */
public class WeekHotVo extends BaseWogameVo {

    @JsonProperty("total_page")
    private int totalPage;

    private List<WeekHotItemVo> items;

    public int getTotalPage() {
        return totalPage;
    }

    public void setTotalPage(int totalPage) {
        this.totalPage = totalPage;
    }

    public List<WeekHotItemVo> getItems() {
        return items;
    }

    public void setItems(List<WeekHotItemVo> items) {
        this.items = items;
    }
}

class WeekHotItemVo {
    @JsonProperty("package_name")
    private String packageName;

    private String description;
    private String category;

    @JsonProperty("icon_url")
    private String iconUrl;
    @JsonProperty("product_id")
    private String productId;
    @JsonProperty("apk_size")
    private int apkSize;
    @JsonProperty("version_name")
    private String versionName;
    private String title;

    public String getPackageName() {
        return packageName;
    }

    public void setPackageName(String packageName) {
        this.packageName = packageName;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getIconUrl() {
        return iconUrl;
    }

    public void setIconUrl(String iconUrl) {
        this.iconUrl = iconUrl;
    }

    public String getProductId() {
        return productId;
    }

    public void setProductId(String productId) {
        this.productId = productId;
    }

    public int getApkSize() {
        return apkSize;
    }

    public void setApkSize(int apkSize) {
        this.apkSize = apkSize;
    }

    public String getVersionName() {
        return versionName;
    }

    public void setVersionName(String versionName) {
        this.versionName = versionName;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }
}
