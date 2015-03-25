package com.unicom.game.center.model;

/**
 * Created with IntelliJ IDEA.
 * User: lenovo
 * Date: 14-6-26
 * Time: 下午5:34
 * To change this template use File | Settings | File Templates.
 */
public class GameDisplayModel {
    private String gameName;
    private String thisTimeData;
    private String lastTimeData;
    private String last2TimeData;
    private String last3TimeData;
    private String last4TimeData;

    public String getGameName() {
        return gameName;
    }

    public void setGameName(String gameName) {
        this.gameName = gameName;
    }

    public String getThisTimeData() {
        return thisTimeData;
    }

    public void setThisTimeData(String thisTimeData) {
        this.thisTimeData = thisTimeData;
    }

    public String getLastTimeData() {
        return lastTimeData;
    }

    public void setLastTimeData(String lastTimeData) {
        this.lastTimeData = lastTimeData;
    }

    public String getLast2TimeData() {
        return last2TimeData;
    }

    public void setLast2TimeData(String last2TimeData) {
        this.last2TimeData = last2TimeData;
    }

    public String getLast3TimeData() {
        return last3TimeData;
    }

    public void setLast3TimeData(String last3TimeData) {
        this.last3TimeData = last3TimeData;
    }

    public String getLast4TimeData() {
        return last4TimeData;
    }

    public void setLast4TimeData(String last4TimeData) {
        this.last4TimeData = last4TimeData;
    }
}
