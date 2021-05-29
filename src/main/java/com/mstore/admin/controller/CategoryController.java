package com.mstore.admin.controller;

import java.io.IOException;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.mstore.domain.Category;
import com.mstore.repository.CategoryDAO;

@Controller
@RequestMapping("/admin/")
public class CategoryController {
	
	@Autowired
	private CategoryDAO cateDao;
	
	@GetMapping("category")
	public String categorys(Model model) {
		
		model.addAttribute("categorys", this.cateDao.findAll());
		
		return "admin/category/category";
		
	}
	
	@GetMapping("form-category")
	public String formAdd(Category category) {
		return "admin/category/add-category";
	}
	
	@PostMapping("add-category")
	public String addCategory(@Valid Category category,BindingResult result,Model model){
		
		if(result.hasErrors()) {
			return "admin/category/add-category";
		}
		
		this.cateDao.save(category);
		
		return "redirect:category";
	}
	
	@GetMapping("edit/{id}")
	public String showUpdateForm(@PathVariable("id") int id, Model model) {
	    Category category = cateDao.findById(id)
	      .orElseThrow(() -> new IllegalArgumentException("Invalid user Id:" + id));
	    
	    model.addAttribute("category", category);
	    return "admin/category/update-category";
	}
	
	@PostMapping("update/{id}")
	public String updateUser(@PathVariable("id") int id, @Valid Category category,BindingResult result, Model model) {
	    if (result.hasErrors()) {
	        category.setId(id);
	        return "admin/category/update-category";
	    }
	        
	    cateDao.save(category);
	    
	    model.addAttribute("categorys", this.cateDao.findAll());
	    return "admin/category/category";
	}
	
	@GetMapping("delete/{id}")
	public String deleteUser(@PathVariable("id") int id, Model model) {
	    Category category = cateDao.findById(id)
	      .orElseThrow(() -> new IllegalArgumentException("Invalid user Id:" + id));
	    
	    this.cateDao.delete(category);
	    
	    model.addAttribute("categorys", this.cateDao.findAll());
	    
	    return "admin/category/category";
	}
	
}
