package com.unicom.game.center.business;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import com.unicom.game.center.utils.Constant;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.unicom.game.center.db.dao.ProductDao;
import com.unicom.game.center.db.domain.ProductDomain;
import com.unicom.game.center.log.model.Product;

/**
 * Created with IntelliJ IDEA.
 * User: claire_chang
 * Date: 7/4/14
 * Time: 11:28 AM
 * To change this template use File | Settings | File Templates.
 */
@Component
@Transactional
public class ProductBusiness {

    @Autowired
    private ProductDao productDao;

    public void typeConversion(Map<String,Product> productHashMap){
        List<ProductDomain> list = new ArrayList<ProductDomain>();
        Iterator iterator = productHashMap.entrySet().iterator();
        while (iterator.hasNext()){
            ProductDomain productDomain = new ProductDomain();
            Map.Entry<Integer, Product> entry = (Map.Entry)iterator.next();
            Product product = entry.getValue();
            productDomain.setProductId(product.getProduct_id());
            productDomain.setProductName(product.getProduct_name());
            productDomain.setProductIcon(product.getProduct_icon());
            productDomain.setDateCreated(product.getDateCreated());
            list.add(productDomain);
        }
        productDao.saveProductDomainList(list, Constant.HIBERNATE_FLUSH_NUM);
    }
    
    public boolean checkId(String value){
        boolean flag = false;
        ProductDomain productDomain = productDao.getByProductId(value);
        if(productDomain == null){
            flag = true;
        }
        return flag;
    }    

}
