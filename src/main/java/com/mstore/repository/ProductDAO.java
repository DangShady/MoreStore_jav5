package com.mstore.repository;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.mstore.domain.Product;

@Repository
public interface ProductDAO extends JpaRepository<Product, Integer>{
	
	
	@Query("SELECT p FROM Product p WHERE p.category.id =?1")
	public Page<Product> getListProductByCategoryPage(Integer categoryId,Pageable pageable);
	
	@Query("SELECT p FROM Product p WHERE p.category.id =?1")
	public List<Product> getListProductByCategory(Integer categoryId);
	
	@Query("SELECT p FROM Product p WHERE p.id =?1")
	public Product getProductDetailById(Integer productId);
	
	
	@Query("SELECT p FROM Product p WHERE p.name LIKE %?1%")
	public List<Product> findProductByName(String keyword);
	
	@Query("SELECT p FROM Product p WHERE "
			+ "CONCAT(p.name, p.category.name) LIKE %?1%")
	public List<Product> findAllProductAdmin(String keyword);
	
	@Query("SELECT p FROM Product p WHERE p.name LIKE %?1%")
	public List<Product> findAllByProductName(String productName);
	
	@Query("SELECT p FROM Product p WHERE p.category.id =?1")
	public List<Product> findAllByAsscess(Integer id);
	
	@Query("SELECT p FROM Product p ORDER BY p.productdate DESC")
	public List<Product> newProducts(Pageable pageable);
	
	
}
