package com.mstore.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import com.mstore.domain.Product;
import com.mstore.service.ProductService;

@Controller
@RequestMapping("/mstore/")
public class ProductSiteController {

	@Autowired
	ProductService productService;
	
	
	@GetMapping("list-product/product/{id}")
	public String getListProductByCategory(@PathVariable("id") int id,Model model) {
		
		List<Product> products = productService.getProductByCategory(id);
		
		model.addAttribute("products",products);
		
		return "site/products/product";
		
	}
	
}
