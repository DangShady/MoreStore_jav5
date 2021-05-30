package com.mstore.service.Impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.mstore.domain.Product;
import com.mstore.repository.ProductDAO;
import com.mstore.service.ProductService;

@Component
public class ProductServiceImpl implements ProductService{

	@Autowired
	ProductDAO productDao;
	
	
	@Override
	public List<Product> getProductByCategory(int id) {
		
		return productDao.getListProductByCategory(id);
	}

}
