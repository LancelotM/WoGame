package com.unicom.game.center.log.model;

import java.io.Serializable;
import java.util.Date;

/**
 * Created with IntelliJ IDEA.
 * User: claire_chang
 * Date: 7/3/14
 * Time: 11:34 AM
 * To change this template use File | Settings | File Templates.
 */
public class GameTraffic implements Serializable{

	private static final long serialVersionUID = 1L;

    private int channelId;
    private int adId;
    private int adType;
    private int sort;
    private int clickThrough;
    private Date dateCreated;

    public GameTraffic() {
        super();
    }

    public GameTraffic(int channelId, int adId, int adType, int sort, int clickThrough, Date dateCreated) {
        super();
        this.channelId = channelId;
        this.adId = adId;
        this.adType = adType;
        this.sort = sort;
        this.clickThrough = clickThrough;
        this.dateCreated = dateCreated;
    }

    public int getSort() {
        return sort;
    }

    public Date getDateCreated() {
        return dateCreated;
    }

    public void setDateCreated(Date dateCreated) {
        this.dateCreated = dateCreated;
    }

    public int getClickThrough() {
        return clickThrough;
    }

    public void setClickThrough(int clickThrough) {
        this.clickThrough = clickThrough;
    }

    public void setSort(int sort) {
        this.sort = sort;
    }

    public int getAdType() {

        return adType;
    }

    public void setAdType(int adType) {
        this.adType = adType;
    }

    public int getAdId() {

        return adId;
    }

    public void setAdId(int adId) {
        this.adId = adId;
    }

    public int getChannelId() {

        return channelId;
    }

    public void setChannelId(int channelId) {
        this.channelId = channelId;
    }



}
