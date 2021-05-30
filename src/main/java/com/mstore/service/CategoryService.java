package com.mstore.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.mstore.domain.Category;

@Service
public interface CategoryService{
	
	public Category insert(Category category);
	
	public Category update(Category category);
	
	public void delete(int id);
	
	public List<Category> getCategoryToShirt();
	
	public List<Category> getCategoryToPant();
	
	public List<Category> getCategoryToAccessories();
}
