package com.unicom.game.center.db.domain;

import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.Id;
import javax.persistence.GeneratedValue;
import javax.persistence.Column;
import javax.persistence.GenerationType;
import java.io.Serializable;
import java.util.Date;

/**
 * Created with IntelliJ IDEA.
 * User: lenovo
 * Date: 14-7-10
 * Time: 下午2:10
 * To change this template use File | Settings | File Templates.
 */
@Entity
@Table(name="package_report")
public class PackageReportDomain implements Serializable{

    private static final long serialVersionUID = 1L;
    private int id;
    private String appid;
    private String appname;
    private int packageStatus;
    private int receiptStatus;
    private String channelId;
    private Date dateCreated;

    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    @Column(name = "id")
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Column(name="appid")
    public String getAppid() {
        return appid;
    }

    public void setAppid(String appid) {
        this.appid = appid;
    }

    @Column(name="appname")
    public String getAppname() {
        return appname;
    }

    public void setAppname(String appname) {
        this.appname = appname;
    }

    @Column(name="package_status")
    public int getPackageStatus() {
        return packageStatus;
    }

    public void setPackageStatus(int packageStatus) {
        this.packageStatus = packageStatus;
    }

    @Column(name="receipt_status")
    public int getReceiptStatus() {
        return receiptStatus;
    }

    public void setReceiptStatus(int receiptStatus) {
        this.receiptStatus = receiptStatus;
    }

    @Column(name="channel_id")
    public String getChannelId() {
        return channelId;
    }

    public void setChannelId(String channelId) {
        this.channelId = channelId;
    }

    @Column(name="date_created")
    public Date getDateCreated() {
        return dateCreated;
    }

    public void setDateCreated(Date dateCreated) {
        this.dateCreated = dateCreated;
    }
}
