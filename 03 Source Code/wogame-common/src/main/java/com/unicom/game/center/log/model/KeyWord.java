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
	
    private String keyword;
    private int count;
    private Date dateCreated;
    private Date dateModified;

    public KeyWord() {
        super();
    }
    public KeyWord(String keyword, int count, Date dateCreated, Date dateModified) {
        super();
        this.keyword = keyword;
        this.count = count;
        this.dateCreated = dateCreated;
        this.dateModified = dateModified;
    }
    public String getKeyword() {
        return keyword;
    }
    public void setKeyword(String keyword) {
        this.keyword = keyword;
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
