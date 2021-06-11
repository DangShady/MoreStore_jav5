package com.mstore.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

import com.mstore.domain.Category;
import com.mstore.domain.Product;
import com.mstore.repository.ProductDAO;
import com.mstore.service.CategoryService;


@Controller
@RequestMapping("/mstore/")
public class HomeController {

	
	@Autowired
	ProductDAO proDao;
	
	@RequestMapping({"home", "/"})
	public String homePage() {
		
		return "site/index";
	}
	
	// Sản phẩm mới
	
	@ModelAttribute("newProducts")
	public List<Product> newProduct(){
		
		Pageable page = PageRequest.of(0, 8);
		
		return proDao.newProducts(page);
		
	}
	
	@RequestMapping({"about"})
	public String homeAbout() {
		
		return "site/about";
	}
	
	@RequestMapping({"contact"})
	public String homeContact() {
		
		return "site/contact";
	}
	
}
