package com.unicom.game.center.db.dao;

import org.springframework.stereotype.Component;

import com.unicom.game.center.db.domain.ProductDomain;

@Component
public class ProductDao extends HibernateDao{
	
	public void save(ProductDomain product){
		getSession().save(product);
	}
	
	public void update(ProductDomain product){
		getSession().update(product);
	}	
	
	public ProductDomain getByProductId(String productId){
		return (ProductDomain)getSession().get(ProductDomain.class, productId);
	}
	
}
