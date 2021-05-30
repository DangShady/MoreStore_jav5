package com.mstore.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.mstore.domain.Product;

@Repository
public interface ProductDAO extends JpaRepository<Product, Integer>{
	
	
	@Query("SELECT p FROM Product p WHERE p.category.id =?1")
	public List<Product> getListProductByCategory(Integer categoryId);
	
//	@Query("SELECT p FROM Product p WHERE p.id =?1")
//	public Product getProductById(Integer productId);
}
