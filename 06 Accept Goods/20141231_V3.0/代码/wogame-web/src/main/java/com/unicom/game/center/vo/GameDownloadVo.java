package com.unicom.game.center.vo;

/**
 * Created by jession on 14-6-23.
 */
public class GameDownloadVo {
    private String id;
    private int result;
    private String description;
    private String downloadUrl;
    private String isFitApp;
    private String onlineTime;
    private String fileName;
    private String context;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public int getResult() {
        return result;
    }

    public void setResult(int result) {
        this.result = result;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getDownloadUrl() {
        return downloadUrl;
    }

    public void setDownloadUrl(String downloadUrl) {
        this.downloadUrl = downloadUrl;
    }

    public String getIsFitApp() {
        return isFitApp;
    }

    public void setIsFitApp(String isFitApp) {
        this.isFitApp = isFitApp;
    }

    public String getOnlineTime() {
        return onlineTime;
    }

    public void setOnlineTime(String onlineTime) {
        this.onlineTime = onlineTime;
    }

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    public String getContext() {
        return context;
    }

    public void setContext(String context) {
        this.context = context;
    }
}
