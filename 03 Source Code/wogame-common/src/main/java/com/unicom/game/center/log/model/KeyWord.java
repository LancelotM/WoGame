package com.unicom.game.center.log.model;

import java.io.Serializable;
import java.util.Date;

/**
 * Created with IntelliJ IDEA.
 * User: claire_chang
 * Date: 7/3/14
 * Time: 11:39 AM
 * To change this template use File | Settings | File Templates.
 */
public class KeyWord implements Serializable{

	private static final long serialVersionUID = 1L;
    private Integer id;
    private String keyword;
    private int channelId;
    private int count;
    private Date dateCreated;
    private Date dateModified;

    public KeyWord() {
        super();
    }
    public KeyWord(Integer id, String keyword,int channelId, int count, Date dateCreated, Date dateModified) {
        super();
        this.id = id;
        this.keyword = keyword;
        this.channelId = channelId;
        this.count = count;
        this.dateCreated = dateCreated;
        this.dateModified = dateModified;
    }
    public Integer getId(){
        return id;
    }
    public void setId(Integer id){
        this.id = id;
    }
    public String getKeyword() {
        return keyword;
    }
    public void setKeyword(String keyword) {
        this.keyword = keyword;
    }

    public int getChannelId() {
        return channelId;
    }

    public void setChannelId(int channelId) {
        this.channelId = channelId;
    }

    public int getCount() {
        return count;
    }
    public void setCount(int count) {
        this.count = count;
    }

    public Date getDateCreated() {
        return dateCreated;
    }

    public void setDateCreated(Date dateCreated) {
        this.dateCreated = dateCreated;
    }

    public Date getDateModified() {
        return dateModified;
    }

    public void setDateModified(Date dateModified) {
        this.dateModified = dateModified;
    }

}
