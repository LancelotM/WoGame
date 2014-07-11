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
	
    private int channel_id;
    private String product_id;
    private int sort;
    private int click_through;
    private int download_count;
    private boolean banner_flag;
    private Date dateCreated;
    private Product product;

    public GameTraffic() {
        super();
    }
    public GameTraffic(int channelId, String productId, int sort,int clickThrough, int downloadCount, boolean bannerFlag, Date dateCreated,Product product) {
        super();
        this.channel_id = channelId;
        this.product_id = productId;
        this.sort = sort;
        this.click_through = clickThrough;
        this.download_count = downloadCount;
        this.banner_flag = bannerFlag;
        this.dateCreated = dateCreated;
        this.product = product;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }
    public int getChannel_id() {
        return channel_id;
    }
    public void setChannel_id(int channelId) {
        channel_id = channelId;
    }
    public String getProduct_id() {
        return product_id;
    }
    public void setProduct_id(String productId) {
        product_id = productId;
    }
    public int getSort() {
        return sort;
    }
    public void setSort(int sort) {
        this.sort = sort;
    }
    public int getClick_through() {
        return click_through;
    }
    public void setClick_through(int clickThrough) {
        click_through = clickThrough;
    }
    public int getDownload_count() {
        return download_count;
    }
    public void setDownload_count(int downloadCount) {
        download_count = downloadCount;
    }
    public boolean isBanner_flag() {
        return banner_flag;
    }
    public void setBanner_flag(boolean bannerFlag) {
        banner_flag = bannerFlag;
    }

    public Date getDateCreated() {
        return dateCreated;
    }

    public void setDateCreated(Date dateCreated) {
        this.dateCreated = dateCreated;
    }
}
