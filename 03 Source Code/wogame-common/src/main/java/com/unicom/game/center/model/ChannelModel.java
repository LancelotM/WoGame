package com.unicom.game.center.model;

import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: lenovo
 * Date: 14-7-22
 * Time: 下午1:49
 * To change this template use File | Settings | File Templates.
 */
public class ChannelModel {

    private String key;
    private List<String> channels;

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public List<String> getChannels() {
        return channels;
    }

    public void setChannels(List<String> channels) {
        this.channels = channels;
    }
}
