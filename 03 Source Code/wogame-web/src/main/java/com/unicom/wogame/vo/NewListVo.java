package com.unicom.wogame.vo;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

/**
 * Created by jession on 14-6-11.
 */
public class NewListVo extends BaseWogameVo {

    @JsonProperty("app_list")
    private List<NewVo> appList;

    public List<NewVo> getAppList() {
        return appList;
    }

    public void setAppList(List<NewVo> appList) {
        this.appList = appList;
    }
}

class NewVo {
    private String icon;
    private String promotionend;
    private String packageName;
    private String sizeinfo;
    private long uucid;
    private String versionName;
    private String promprice;
    private long size;
    private long id;
    private String catagory;
    private float rate;
    private float price;
    private String nosiziinfo;
    private String name;
    private String ispromotion;
    private String supplier;
    private String promotionstart;

    public String getIcon() {
        return icon;
    }

    public void setIcon(String icon) {
        this.icon = icon;
    }

    public String getPromotionend() {
        return promotionend;
    }

    public void setPromotionend(String promotionend) {
        this.promotionend = promotionend;
    }

    public String getPackageName() {
        return packageName;
    }

    public void setPackageName(String packageName) {
        this.packageName = packageName;
    }

    public String getSizeinfo() {
        return sizeinfo;
    }

    public void setSizeinfo(String sizeinfo) {
        this.sizeinfo = sizeinfo;
    }

    public long getUucid() {
        return uucid;
    }

    public void setUucid(long uucid) {
        this.uucid = uucid;
    }

    public String getVersionName() {
        return versionName;
    }

    public void setVersionName(String versionName) {
        this.versionName = versionName;
    }

    public String getPromprice() {
        return promprice;
    }

    public void setPromprice(String promprice) {
        this.promprice = promprice;
    }

    public long getSize() {
        return size;
    }

    public void setSize(long size) {
        this.size = size;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getCatagory() {
        return catagory;
    }

    public void setCatagory(String catagory) {
        this.catagory = catagory;
    }

    public float getRate() {
        return rate;
    }

    public void setRate(float rate) {
        this.rate = rate;
    }

    public float getPrice() {
        return price;
    }

    public void setPrice(float price) {
        this.price = price;
    }

    public String getNosiziinfo() {
        return nosiziinfo;
    }

    public void setNosiziinfo(String nosiziinfo) {
        this.nosiziinfo = nosiziinfo;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getIspromotion() {
        return ispromotion;
    }

    public void setIspromotion(String ispromotion) {
        this.ispromotion = ispromotion;
    }

    public String getSupplier() {
        return supplier;
    }

    public void setSupplier(String supplier) {
        this.supplier = supplier;
    }

    public String getPromotionstart() {
        return promotionstart;
    }

    public void setPromotionstart(String promotionstart) {
        this.promotionstart = promotionstart;
    }
}
