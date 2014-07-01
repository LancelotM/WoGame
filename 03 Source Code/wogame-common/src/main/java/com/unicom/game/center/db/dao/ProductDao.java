package com.unicom.game.center.db.dao;

import org.springframework.stereotype.Component;

import com.unicom.game.center.db.domain.ProductDomain;

@Component
public class ProductDao extends HibernateDao{
	
	public void save(ProductDomain product){
		getSession().save(product);
		getSession().flush();
	}
	
	public void update(ProductDomain product){
		getSession().update(product);
		getSession().flush();
	}	
	
	public ProductDomain getByProductId(String productId){
		return (ProductDomain)getSession().get(ProductDomain.class, productId);
	}
	
}
