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

	
	
	
	@Autowired
	CartService cartService;
	
	@Autowired()
	HttpSession session;
	
	@Autowired
	HttpServletRequest request;
	
	@Autowired
	CookieService cookie;
	
	
	@ResponseBody
	@RequestMapping("product/add-to-cart/{id}/{quantity}")
	public String addToCart(@PathVariable("id") Integer id,@PathVariable("quantity") Integer quantity) {
		
		System.out.println("Quantity  " +  quantity);
		
		cartService.add(id,quantity);
		
		session.setAttribute("amountCart", cartService.getAmount());
		
		session.setAttribute("Cart", cartService.getItems());
		
		session.setAttribute("countCart", cartService.getCount());
		
		
		return "";
	}
	
	@ResponseBody
	@RequestMapping("product/update-to-cart/{id}/{quantity}")
	public String updateToCart(@PathVariable("id") Integer id,@PathVariable("quantity") Integer quantity) {
				
		cartService.update(id,quantity);
		
		if(quantity == 0) {
			cartService.remove(id);
		}

		
		request.getSession().setAttribute("amountCart", cartService.getAmount());
		
		session.setAttribute("countCart", cartService.getCount());
		
		return "update";
	}
	
	
	@ResponseBody
	@RequestMapping("product/remove-to-cart/{id}")
	public String removeToCart(@PathVariable("id") Integer id) {
			
		cartService.remove(id);
		
		request.getSession().setAttribute("amountCart", cartService.getAmount());
		
		session.setAttribute("Cart", cartService.getItems());
		
		
		session.setAttribute("countCart", cartService.getCount());
		
		return "remove";
	}
	
	
	
}
