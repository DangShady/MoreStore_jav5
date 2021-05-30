package com.mstore.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/mstore")
public class SitePageController {

	
	@RequestMapping("/about")
	public String about() {
		return "site/about";
	}
	@RequestMapping("/contact")
	public String contact() {
		return "site/contact";
	}
	
	@RequestMapping("/product-detail")
	public String productDetail() {
		return "site/products/product-detail";
	}
	
	@RequestMapping("/checkout")
	public String viewCart() {
		
		return "site/shopping-cart";
	}
	
	
	@RequestMapping("/login")
	public String login() {
		
		return "site/login";
	}
	
	@RequestMapping("/register")
	public String register() {
		
		return "site/register";
	}
	
	@RequestMapping("/profile")
	public String profile() {
		
		return "site/user-profile";
	}
	
	@RequestMapping("/profile/password")
	public String password() {
		
		return "site/share/password";
	}
	
	
}
