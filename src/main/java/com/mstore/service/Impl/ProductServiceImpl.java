package com.mstore.service.Impl;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Repository;

import com.mstore.domain.Product;
import com.mstore.repository.ProductDAO;
import com.mstore.service.ProductService;

@Repository
public class ProductServiceImpl implements ProductService{

	@Autowired
	ProductDAO productDao;
	
	
	@Override
	public List<Product> getProductByCategory(int id) {
		
		return productDao.getListProductByCategory(id);
		
	}
	
	@Override
	public List<Product> findProductByName(String keyword) {
		
		return productDao.findProductByName(keyword);
		
	}
	
	@Override
	public Page<Product> getProductByCategoryPage(int id,Pageable pageable) {
		return productDao.getListProductByCategoryPage(id,pageable);
	}


	@Override
	public Product getProductDetailById(int id) {
		
		return productDao.getProductDetailById(id);
	}

	@Override
	public Page<Product> getAllProductAdmin(String keyword, int pageNumber) {
		Pageable pageable = PageRequest.of(pageNumber-1, 8);
		if(keyword != null) {
			return productDao.findAllProductAdmin(keyword,pageable);
		}
		
		return productDao.listAll(pageable);
		
	}

	@Override
	public Page<Product> listAll(int pageNumber) {
		
		Pageable pageable = PageRequest.of(pageNumber-1, 8);
		
		return productDao.findAll(pageable);
	}

	


}
