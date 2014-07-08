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
public class Product implements Serializable{

	private static final long serialVersionUID = 1L;
	
    private String product_id;
    private String product_name;
    private String product_icon;
    private Date dateCreated;

    public Product() {
        super();
    }
    public Product(String productId, String productName, String productIcon,Date dateCreated) {
        super();
        this.product_id = productId;
        this.product_name = productName;
        this.product_icon = productIcon;
        this.dateCreated = dateCreated;
    }
    public String getProduct_id() {
        return product_id;
    }
    public void setProduct_id(String productId) {
        product_id = productId;
    }
    public String getProduct_name() {
        return product_name;
    }
    public void setProduct_name(String productName) {
        product_name = productName;
    }
    public String getProduct_icon() {
        return product_icon;
    }
    public void setProduct_icon(String productIcon) {
        product_icon = productIcon;
    }
    public Date getDateCreated() {
        return dateCreated;
    }
    public void setDateCreated(Date dateCreated) {
        this.dateCreated = dateCreated;
    }
}
