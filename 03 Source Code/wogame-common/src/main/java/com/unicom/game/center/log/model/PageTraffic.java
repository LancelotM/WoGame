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
public class PageTraffic implements Serializable{

	private static final long serialVersionUID = 1L;
	
    private int homepage;
    private int category;
    private int hotlist;
    private int latest;
    private int channelId;
    private Date dateCreated;

    public PageTraffic() {
        super();
    }

    public PageTraffic(int homepage, int category, int hotlist, int latest,int channelId, Date dateCreated) {
        super();
        this.homepage = homepage;
        this.category = category;
        this.hotlist = hotlist;
        this.latest = latest;
        this.channelId = channelId;
        this.dateCreated = dateCreated;
    }
    public int getHomepage() {
        return homepage;
    }

    public void setHomepage(int homepage) {
        this.homepage = homepage;
    }

    public int getCategory() {
        return category;
    }

    public void setCategory(int category) {
        this.category = category;
    }

    public int getHotlist() {
        return hotlist;
    }

    public void setHotlist(int hotlist) {
        this.hotlist = hotlist;
    }

    public int getLatest() {
        return latest;
    }

    public void setLatest(int latest) {
        this.latest = latest;
    }

    public int getChannelId() {
        return channelId;
    }

    public void setChannelId(int channelId) {
        this.channelId = channelId;
    }

    public Date getDateCreated() {
        return dateCreated;
    }

    public void setDateCreated(Date dateCreated) {
        this.dateCreated = dateCreated;
    }
}
