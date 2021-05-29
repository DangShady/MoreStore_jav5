package com.mstore.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.mstore.domain.Category;

@Repository
public interface CategoryDAO extends JpaRepository<Category, Integer>{
	
}
