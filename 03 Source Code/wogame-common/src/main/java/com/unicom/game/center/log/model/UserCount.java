package com.unicom.game.center.log.model;

import java.io.Serializable;
import java.util.Date;

/**
 * Created with IntelliJ IDEA.
 * User: claire_chang
 * Date: 7/1/14
 * Time: 8:40 PM
 * To change this template use File | Settings | File Templates.
 */
public class UserCount implements Serializable{

	private static final long serialVersionUID = 1L;
	
    private int old_user_count;
    private int new_user_count;
    private int channelId;
    private Date dateCreated;

    public UserCount() {
        super();
    }

    public UserCount(int oldUserCount, int newUserCount, int channelId, Date dateCreated) {
        super();
        old_user_count = oldUserCount;
        new_user_count = newUserCount;
        this.dateCreated = dateCreated;
    }
    public int getOld_user_count() {
        return old_user_count;
    }
    public void setOld_user_count(int oldUserCount) {
        old_user_count = oldUserCount;
    }
    public int getNew_user_count() {
        return new_user_count;
    }
    public void setNew_user_count(int newUserCount) {
        new_user_count = newUserCount;
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
