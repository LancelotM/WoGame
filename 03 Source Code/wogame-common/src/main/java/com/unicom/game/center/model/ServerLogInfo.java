package com.unicom.game.center.model;

public class ServerLogInfo {

    private String pageName;

    private String channelCode;

    private String ip;

    private String date;

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getIp() {

        return ip;
    }

    public void setIp(String ip) {
        this.ip = ip;
    }

    public String getChannelCode() {

        return channelCode;
    }

    public void setChannelCode(String channelCode) {
        this.channelCode = channelCode;
    }

    public String getPageName() {

        return pageName;
    }

    public void setPageName(String pageName) {
        this.pageName = pageName;
    }
}
