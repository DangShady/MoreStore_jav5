package com.mstore.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.mstore.domain.Product;

@Repository
public interface ProductDAO extends JpaRepository<Product, Integer>{

}
