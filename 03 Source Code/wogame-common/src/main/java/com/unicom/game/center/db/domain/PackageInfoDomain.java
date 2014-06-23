package com.unicom.game.center.db.domain;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.AttributeOverride;
import javax.persistence.AttributeOverrides;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="package_info")
public class PackageInfoDomain implements Serializable{

	
	private static final long serialVersionUID = 1L;
	@Id
    @AttributeOverrides({
    @AttributeOverride(name = "appId", column = @Column(name = "app_id")),
    @AttributeOverride(name = "channelId", column = @Column(name = "channel_id"))
            })
	private PackageInfoKey key;
	
	@Column(name="cp_id")
	private String cpId;
	
	@Column(name="app_name")
	private String appName;
	
	@Column(name="update_type")
	private Integer updateType;
	
	@Column(name="soft_id")
	private String softId;
	
	@Column(name="onlinetime")
	private String onlinetime;
	
	@Column(name="original_file_path")
	private String originalFilePath;
	
	@Column(name="apk_file_path")
	private String apkFilePath;
	
	@Column(name="apk_online_time")
	private String apkOnlineTime;

	@Column(name="status")
	private String status;
	
	@Column(name="reserve1")
	private String reserve1;
	
	@Column(name="reserve2")
	private String reserve2;
	
	@Column(name="reserve3")
	private String reserve3;
	
	@Column(name="reserve4")
	private String reserve4;
		
	@Column(name="reserve5")
	private String reserve5;
	
	@Column(name="date_created")
	private Date dateCreated;
	
	@Column(name="date_modified")
	private Date dateModified;

	public PackageInfoKey getKey() {
		return key;
	}

	public void setKey(PackageInfoKey key) {
		this.key = key;
	}

	public String getCpId() {
		return cpId;
	}

	public void setCpId(String cpId) {
		this.cpId = cpId;
	}

	public String getAppName() {
		return appName;
	}

	public void setAppName(String appName) {
		this.appName = appName;
	}

	public Integer getUpdateType() {
		return updateType;
	}

	public void setUpdateType(Integer updateType) {
		this.updateType = updateType;
	}

	public String getSoftId() {
		return softId;
	}

	public void setSoftId(String softId) {
		this.softId = softId;
	}

	public String getOnlinetime() {
		return onlinetime;
	}

	public void setOnlinetime(String onlinetime) {
		this.onlinetime = onlinetime;
	}

	public String getOriginalFilePath() {
		return originalFilePath;
	}

	public void setOriginalFilePath(String originalFilePath) {
		this.originalFilePath = originalFilePath;
	}

	public String getApkFilePath() {
		return apkFilePath;
	}

	public void setApkFilePath(String apkFilePath) {
		this.apkFilePath = apkFilePath;
	}

	public String getApkOnlineTime() {
		return apkOnlineTime;
	}

	public void setApkOnlineTime(String apkOnlineTime) {
		this.apkOnlineTime = apkOnlineTime;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getReserve1() {
		return reserve1;
	}

	public void setReserve1(String reserve1) {
		this.reserve1 = reserve1;
	}

	public String getReserve2() {
		return reserve2;
	}

	public void setReserve2(String reserve2) {
		this.reserve2 = reserve2;
	}

	public String getReserve3() {
		return reserve3;
	}

	public void setReserve3(String reserve3) {
		this.reserve3 = reserve3;
	}

	public String getReserve4() {
		return reserve4;
	}

	public void setReserve4(String reserve4) {
		this.reserve4 = reserve4;
	}

	public String getReserve5() {
		return reserve5;
	}

	public void setReserve5(String reserve5) {
		this.reserve5 = reserve5;
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
