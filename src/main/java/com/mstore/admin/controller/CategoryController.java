package com.mstore.admin.controller;

import java.io.IOException;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.mstore.domain.Category;
import com.mstore.domain.Product;
import com.mstore.repository.CategoryDAO;
import com.mstore.service.CategoryService;

@Controller
@RequestMapping("/admin/")
public class CategoryController {
	
	@Autowired
	private CategoryDAO cateDao;
	
	@Autowired
	CategoryService cateService;
	
	@GetMapping("category")
	public String categorys(Model model) {
		
		return categoryByPage(model,1,null);
		
	}
	
	@GetMapping("category/page/{pageNumber}")
	public String categoryByPage(Model model, @PathVariable("pageNumber") int currentPage,
			@Param("keyword") String keyword
			) {
		
		Page<Category> page = cateService.getAllCategoryAndSearch(currentPage,keyword);

		long totalItems = page.getTotalElements();

		int totalPages = page.getTotalPages();

		List<Category> listCategory = page.getContent();

		model.addAttribute("currentPage", currentPage);

		model.addAttribute("totalItems", totalItems);
		model.addAttribute("totalPages", totalPages);

		model.addAttribute("categorys", listCategory);
		
		model.addAttribute("keyword",keyword);
		
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
	    return "redirect:/admin/category";
	}
	
	@GetMapping("delete/{id}")
	public String deleteUser(@PathVariable("id") int id, Model model) {
	    Category category = cateDao.findById(id)
	      .orElseThrow(() -> new IllegalArgumentException("Invalid user Id:" + id));
	    
	    this.cateDao.delete(category);
	    
	    model.addAttribute("categorys", this.cateDao.findAll());
	    
	    return "redirect:/admin/category";
	}
	
}
