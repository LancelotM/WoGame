package com.unicom.game.center.vo;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

public class GameDetailVo {

    @JsonProperty("rate")
    private int rate;

    @JsonProperty("description")
    private String description;

    @JsonProperty("intro")
    private String intro;

    @JsonProperty("score")
    private long score;

    @JsonProperty("attributes")
    private List<GameDetailAttributes> attributes;

    @JsonProperty("privilege")
    private PrivilegeVo privilege;

    @JsonProperty("categories")
    private List<GameDetailCategories> categories;

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

    @JsonProperty("apk_url")
    private String apkUrl;

    @JsonProperty("package_name")
    private String packageName;

    @JsonProperty("apk_size")
    private String apkSize;

    @JsonProperty("tags")
    private List<String> tags;

    public List<String> getPics() {
        return pics;
    }

    public void setPics(List<String> pics) {
        this.pics = pics;
    }

    @JsonProperty("pics")
    private List<String> pics;

    public List<String> getTags() {
        return tags;
    }

    public void setTags(List<String> tags) {
        this.tags = tags;
    }

    public String getApkSize() {

        return apkSize;
    }

    public void setApkSize(String apkSize) {
        this.apkSize = apkSize;
    }

    public String getPackageName() {

        return packageName;
    }

    public void setPackageName(String packageName) {
        this.packageName = packageName;
    }

    public String getApkUrl() {

        return apkUrl;
    }

    public void setApkUrl(String apkUrl) {
        this.apkUrl = apkUrl;
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

    public List<GameDetailCategories> getCategories() {

        return categories;
    }

    public void setCategories(List<GameDetailCategories> categories) {
        this.categories = categories;
    }

    public PrivilegeVo getPrivilege() {

        return privilege;
    }

    public void setPrivilege(PrivilegeVo privilege) {
        this.privilege = privilege;
    }

    public List<GameDetailAttributes> getAttributes() {

        return attributes;
    }

    public void setAttributes(List<GameDetailAttributes> attributes) {
        this.attributes = attributes;
    }

    public long getScore() {

        return score;
    }

    public void setScore(long score) {
        this.score = score;
    }

    public String getIntro() {

        return intro;
    }

    public void setIntro(String intro) {
        this.intro = intro;
    }

    public String getDescription() {

        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getRate() {

        return rate;
    }

    public void setRate(int rate) {
        this.rate = rate;
    }
}
