package com.mstore.controller;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
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
import com.mstore.service.CategoryService;
import com.mstore.service.ProductService;

@Controller
@RequestMapping("/mstore/")
public class ProductSiteController {

	@Autowired
	CategoryService cateService;
	
	@ModelAttribute("shirts")
	public List<Category> getCategoryShirt(){
		return cateService.getCategoryToShirt();
	}
	@ModelAttribute("pants")
	public List<Category> getCategoryPant(){
		return cateService.getCategoryToPant();
	}
	@ModelAttribute("accessories")
	public List<Category> getCategoryAccessories(){
		return cateService.getCategoryToAccessories();
	}
	
	@Autowired
	ProductService productService;
	
	
	@GetMapping("test")
	public String test() {
		return "site/products/test";
	}
	
	@GetMapping("list-product/paginated")
	public String getListProductByCategoryPage(ModelMap model,
			@RequestParam(name = "id", required = false) int id,
			@RequestParam("page") Optional<Integer> page			
			) {
		
		int currentPage = page.orElse(1);
		
		int pageSize = 4;
		
		Pageable pageable = PageRequest.of(currentPage, pageSize);
		
		Page<Product> resultPage = null;
		
		
		resultPage = productService.getProductByCategoryPage(id, pageable);
		
		int totalPages = resultPage.getTotalPages();
		
		if(totalPages > 0) {
			int start = Math.max(1, currentPage - 2);
			int end = Math.min(currentPage + 2, totalPages);
			
			if(totalPages > 5) {
				if(end == totalPages) start = end-5;
				else if(start == 1) end = start +5;
			}
			
			List<Integer> pageNumbers = IntStream.rangeClosed(start, end)
					.boxed().collect(Collectors.toList());
			
			model.addAttribute("pageNumbers",pageNumbers);
		}
		
		
		model.addAttribute("productPage",resultPage);
		
//		model.addAttribute("products",products);
		
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
		
		model.addAttribute("productDetail",product);
		
		return "site/products/product-detail";
		
	}
	
}
