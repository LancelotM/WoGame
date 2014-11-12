package com.unicom.game.center.vo;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

public class CategoryGameItemsVo {

    @JsonProperty("intro")
    private String intro;

    @JsonProperty("privilege")
    private PrivilegeVo privilege;

    @JsonProperty("product_id")
    private String productId;

    @JsonProperty("game_name")
    private String gameName;

    @JsonProperty("version_code")
    private String versionCode;

    @JsonProperty("version_name")
    private String versionName;

    @JsonProperty("icon_url")
    private String iconUrl;

    @JsonProperty("package_name")
    private String packageName;

    @JsonProperty("apk_url")
    private String apkUrl;

    @JsonProperty("apk_size")
    private long apkSize;

    @JsonProperty("tags")
    private List<String> tags;

    @JsonProperty("pics")
    private List<String> pics;

    @JsonProperty("download_url")
    private String download_url;

    public String getDownload_url() {
        return download_url;
    }

    public void setDownload_url(String download_url) {
        this.download_url = download_url;
    }

    public List<String> getPics() {
        return pics;
    }

    public void setPics(List<String> pics) {
        this.pics = pics;
    }

    public List<String> getTags() {

        return tags;
    }

    public void setTags(List<String> tags) {
        this.tags = tags;
    }

    public long getApkSize() {
        return apkSize;
    }

    public void setApkSize(long apkSize) {
        this.apkSize = apkSize;
    }

    public String getApkUrl() {
        return apkUrl;
    }

    public void setApkUrl(String apkUrl) {
        this.apkUrl = apkUrl;
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

    public String getGameName() {

        return gameName;
    }

    public void setGameName(String gameName) {
        this.gameName = gameName;
    }

    public String getProductId() {

        return productId;
    }

    public void setProductId(String productId) {
        this.productId = productId;
    }

    public String getIntro() {

        return intro;
    }

    public void setIntro(String intro) {
        this.intro = intro;
    }


    public PrivilegeVo getPrivilege() {
        return privilege;
    }

    public void setPrivilege(PrivilegeVo privilege) {
        this.privilege = privilege;
    }
}
