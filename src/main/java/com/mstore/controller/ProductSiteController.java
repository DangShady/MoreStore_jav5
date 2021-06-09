package com.mstore.controller;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.mstore.domain.Category;
import com.mstore.domain.Product;
import com.mstore.repository.ProductDAO;
import com.mstore.service.CategoryService;
import com.mstore.service.ProductService;

@Controller
@RequestMapping("/mstore/")
public class ProductSiteController {

	
	@Autowired
	ProductService productService;

	
	@Autowired
	ProductDAO proDao;
	
	
	
	@GetMapping("product/shirt")
	public String getAllShirt(Model model) {

		List<Product> listByName = proDao.findAllByProductName("%áo%");
		
		model.addAttribute("products",listByName);
		
		
		return "site/products/product";
	}
	
	
	@GetMapping("product/trousers")
	public String getAllPants(Model model) {

		List<Product> listByName = proDao.findAllByProductName("%quần%");
		
		model.addAttribute("products",listByName);
		
		
		return "site/products/product";
	}
	
	@GetMapping("product/accessories")
	public String getAllAccessories (Model model) {

		List<Product> listByName = proDao.findAllByAsscess(7);
		
		model.addAttribute("products",listByName);
		
		
		return "site/products/product";
	}
	
	@GetMapping("list-product/find-by-name")
	public String findByName(Model model, @RequestParam("search-product") String keyword) {
		
		
		List<Product> listByName = productService.findProductByName(keyword);
		
		
		model.addAttribute("products",listByName);
		
		return "site/products/product";
	}
	
	
	@GetMapping("list-product/product/{id}")
	public String getListProductByCategory(@PathVariable("id") int id,Model model) {
		
		
		
		List<Product> products = productService.getProductByCategory(id);
		
		model.addAttribute("products",products);
		
		return "site/products/product";
		
	}
	
	
	@GetMapping("product-detail/product/{id}")
	public String getProductDetailById(@PathVariable("id") int id,Model model) {
		
		Product product = productService.getProductDetailById(id);
		
		List<Product> listRelatedProduct = productService.getProductByCategory(product.getCategory().getId());
		
		model.addAttribute("relatedProduct",listRelatedProduct);
		model.addAttribute("productDetail",product);
	
		
		return "site/products/product-detail";
		
	}
	
}
