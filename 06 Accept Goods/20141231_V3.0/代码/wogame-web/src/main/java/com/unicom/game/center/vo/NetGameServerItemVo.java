package com.unicom.game.center.vo;

import com.fasterxml.jackson.annotation.JsonProperty;

public class NetGameServerItemVo {

    @JsonProperty("intro")
    private String intro;

    @JsonProperty("server_name")
    private String serverName;

    @JsonProperty("open_time")
    private long openTime;

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

    @JsonProperty("apk_size")
    private long apkSize;

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

    public long getOpenTime() {

        return openTime;
    }

    public void setOpenTime(long openTime) {
        this.openTime = openTime;
    }

    public String getServerName() {

        return serverName;
    }

    public void setServerName(String serverName) {
        this.serverName = serverName;
    }

    public String getIntro() {

        return intro;
    }

    public void setIntro(String intro) {
        this.intro = intro;
    }
}
