package com.mstore.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

import com.mstore.domain.Category;
import com.mstore.service.CategoryService;


@Controller
@RequestMapping("/mstore/")
public class HomeController {
//	
//	@Autowired
//	CategoryService cateService;
//	
//	@ModelAttribute("shirts")
//	public List<Category> getCategoryShirt(){
//		return cateService.getCategoryToShirt();
//	}
//	@ModelAttribute("pants")
//	public List<Category> getCategoryPant(){
//		return cateService.getCategoryToPant();
//	}
//	@ModelAttribute("accessories")
//	public List<Category> getCategoryAccessories(){
//		return cateService.getCategoryToAccessories();
//	}
	
	@RequestMapping({"home", "/"})
	public String homePage() {
		
		return "site/index";
	}
	
}
