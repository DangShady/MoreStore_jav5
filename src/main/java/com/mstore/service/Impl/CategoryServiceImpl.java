package com.mstore.service.Impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.mstore.domain.Category;
import com.mstore.repository.CategoryDAO;
import com.mstore.service.CategoryService;

@Component
public class CategoryServiceImpl implements CategoryService{
	
	@Autowired
	CategoryDAO categoryDao;

	@Override
	public List<Category> getCategoryToShirt() {
		String shirt = "áo%";
		return categoryDao.selectCategorybyShirt(shirt);
	}
	
	@Override
	public List<Category> getCategoryToPant() {
		String pant = "quần%";
		return categoryDao.selectCategorybyPant(pant);
	}
	
	@Override
	public List<Category> getCategoryToAccessories() {
		String accessories = "phụ%";
		return categoryDao.selectCategorybyAccess(accessories);
	}

	@Override
	public Category insert(Category category) {
		
		return categoryDao.save(category);
	}

	@Override
	public Category update(Category category) {
		
		return categoryDao.save(category);
	}

	@Override
	public void delete(int id) {
		
		categoryDao.deleteById(id);	
	}

	
}
