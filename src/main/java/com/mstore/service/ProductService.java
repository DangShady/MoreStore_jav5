package com.mstore.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.mstore.domain.Product;

@Service
public interface ProductService {
	
	public List<Product> getProductByCategory(int id);
	
}
