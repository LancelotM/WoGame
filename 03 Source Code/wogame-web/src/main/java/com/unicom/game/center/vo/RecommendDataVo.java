package com.unicom.game.center.vo;

import com.fasterxml.jackson.annotation.JsonProperty;

public class RecommendDataVo {

    @JsonProperty("title")
    private String title;

    @JsonProperty("intro")
    private String intro;

    @JsonProperty("privilege")
    private PrivilegeVo privilege;

    @JsonProperty("corner_mark")
    private String cornerMark;

    @JsonProperty("product_id")
    private String productId;

    @JsonProperty("version_code")
    private String versionCode;

    @JsonProperty("version_name")
    private String versionName;

    @JsonProperty("icon_url")
    private String iconUrl;

    @JsonProperty("package_name")
    private String packageName;

    @JsonProperty("apk_size")
    private long apkSize;

    @JsonProperty("banner")
    private Banner banner;

    @JsonProperty("download_url")
    private String download_url;



    public Banner getBanner() {
        return banner;
    }

    public void setBanner(Banner banner) {
        this.banner = banner;
    }

    public long getApkSize() {
        return apkSize;
    }

    public void setApkSize(long apkSize) {
        this.apkSize = apkSize;
    }

    public String getPackageName() {

        return packageName;
    }

    public void setPackageName(String packageName) {
        this.packageName = packageName;
    }

    public String getIconUrl() {

        return iconUrl;
    }

    public void setIconUrl(String iconUrl) {
        this.iconUrl = iconUrl;
    }

    public String getVersionName() {

        return versionName;
    }

    public void setVersionName(String versionName) {
        this.versionName = versionName;
    }

    public String getVersionCode() {

        return versionCode;
    }

    public void setVersionCode(String versionCode) {
        this.versionCode = versionCode;
    }

    public String getProductId() {

        return productId;
    }

    public void setProductId(String productId) {
        this.productId = productId;
    }

    public String getCornerMark() {

        return cornerMark;
    }

    public void setCornerMark(String cornerMark) {
        this.cornerMark = cornerMark;
    }

    public PrivilegeVo getPrivilege() {

        return privilege;
    }

    public void setPrivilege(PrivilegeVo privilege) {
        this.privilege = privilege;
    }

    public String getIntro() {

        return intro;
    }

    public void setIntro(String intro) {
        this.intro = intro;
    }

    public String getTitle() {

        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDownload_url() {
        return download_url;
    }

    public void setDownload_url(String download_url) {
        this.download_url = download_url;
    }
}
