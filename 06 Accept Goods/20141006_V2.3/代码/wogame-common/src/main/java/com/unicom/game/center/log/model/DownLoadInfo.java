package com.unicom.game.center.log.model;

import java.io.Serializable;
import java.util.Date;

/**
 * Created with IntelliJ IDEA.
 * User: claire_chang
 * Date: 7/3/14
 * Time: 11:32 AM
 * To change this template use File | Settings | File Templates.
 */
public class DownLoadInfo implements Serializable{

	private static final long serialVersionUID = 1L;
    private Integer id;
    private String product_id;
    private String channel_code;
    private int download_count;
    private Date dateCreated;

    public DownLoadInfo() {
        super();
    }
    public DownLoadInfo(Integer id, String productId, String channelCode, int downloadCount,Date dateCreated) {
        super();
        this.id = id;
        this.product_id = productId;
        this.channel_code = channelCode;
        this.download_count = downloadCount;
        this.dateCreated = dateCreated;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getProduct_id() {
        return product_id;
    }
    public void setProduct_id(String productId) {
        product_id = productId;
    }

    public String getChannel_code() {
		return channel_code;
	}
    
	public void setChannel_code(String channel_code) {
		this.channel_code = channel_code;
	}
	
	public int getDownload_count() {
        return download_count;
    }
	
    public void setDownload_count(int downloadCount) {
        download_count = downloadCount;
    }

    public Date getDateCreated() {
        return dateCreated;
    }

    public void setDateCreated(Date dateCreated) {
        this.dateCreated = dateCreated;
    }
}
