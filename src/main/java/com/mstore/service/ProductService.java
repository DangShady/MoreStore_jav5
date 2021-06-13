package com.mstore.service;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.mstore.domain.Product;

@Service
public interface ProductService {
	
	public List<Product> getProductByCategory(int id);
	
	public Page<Product> getProductByCategoryPage(int id,Pageable page);
	
	
	public Product getProductDetailById(int id);
	
	public Page<Product> getAllProductAdmin(String name,int pageNumber);
	
	public List<Product> findProductByName(String keyword);
	
	public Page<Product> listAll(int pageNumber);	
	
}
