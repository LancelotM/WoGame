package com.unicom.game.center.persistence.db.dao;

import org.springframework.stereotype.Component;

import com.unicom.game.center.persistence.db.domain.ProductDomain;

@Component
public class ProductDao extends HibernateDao{
	
	public void save(ProductDomain product){
		getSession().save(product);
	}
	
	public ProductDomain getByProductId(int productId){
		return (ProductDomain)getSession().load(ProductDomain.class, productId);
	}

}
