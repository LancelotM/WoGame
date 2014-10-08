package com.unicom.game.center.vo;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.unicom.game.center.vo.PrivilegeVo;

public class WeekHotItemListVo {

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

    public Long getApkSize() {
        return apkSize;
    }

    public void setApkSize(Long apkSize) {
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

    @JsonProperty("package_name")
    private String packageName;

    @JsonProperty("apk_size")
    private Long apkSize;
}
