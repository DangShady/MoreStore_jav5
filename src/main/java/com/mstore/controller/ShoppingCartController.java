package com.mstore.controller;

import java.util.Collection;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.mstore.domain.Category;
import com.mstore.domain.Product;
import com.mstore.service.CategoryService;
import com.mstore.utils.CartService;
import com.mstore.utils.CookieService;

@Controller
@RequestMapping("/mstore/")

public class ShoppingCartController {
	
	
//	Dành riêng cho header
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
	CartService cartService;
	
	@Autowired
	HttpSession session;
	
	@Autowired
	HttpServletRequest request;
	
	@Autowired
	CookieService cookie;
	
	
	@ResponseBody
	@RequestMapping("product/add-to-cart/{id}")
	public String addToCart(@PathVariable("id") Integer id) {
		
		cartService.add(id);
		
		session.setAttribute("amountCart", cartService.getAmount());
		
		session.setAttribute("Cart", cartService.getItems());
		
		
		return "";
	}
	
	
	@GetMapping("product/shoping-cart-view")
	public String viewShoppingCart() {
		
//		session.setAttribute("view-cart", cartService.getItems());
		
		return "/site/products/shopping-cart";
	}
}
