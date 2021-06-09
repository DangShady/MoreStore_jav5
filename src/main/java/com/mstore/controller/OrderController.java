package com.mstore.controller;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.mstore.domain.Account;
import com.mstore.domain.Order;
import com.mstore.domain.OrderDetail;
import com.mstore.domain.Product;
import com.mstore.repository.ProductDAO;
import com.mstore.service.OrderService;
import com.mstore.utils.CartService;

@Controller
@RequestMapping("/mstore/")
public class OrderController {

	@Autowired
	HttpSession session;
	
	@Autowired
	CartService cart;
	
	@Autowired
	ProductDAO proDao;
	
	@Autowired
	OrderService orderService;
	
	
	@GetMapping("thank-you-and-wellcome-back")
	public String thankbuyproduct(){
		
		return "/site/products/thank-buy";
	}
	
	
	@GetMapping("product/shoping-cart-view")
	public String viewShoppingCart(Order order) {
		
		return "/site/products/shopping-cart";
	}
	
	
	@PostMapping("order-cart/checkout")
	public String order(@Valid Order order,BindingResult result,Model model) {
		
		if(result.hasErrors()) {
			
			return "/site/products/shopping-cart";
		}
		Account account = (Account) session.getAttribute("USERINJD");
		order.setOrderdate(new Date());
		order.setAccount(account);
		order.setAmount(cart.getAmount());
		order.setStatus(false);
		
		Collection<Product> list = cart.getItems();
		
		List<OrderDetail> details = new ArrayList<>();
		
		for(Product product : list) {
			OrderDetail detail = new OrderDetail();
			detail.setOrder(order);
			detail.setProduct(product);
			detail.setPrice(product.getPrice());
			detail.setQuantity(product.getQuantity());
			details.add(detail);
			
			Product pr = proDao.getById(product.getId());
			
			pr.setQuantity(pr.getQuantity() - product.getQuantity());
			
			proDao.save(pr);
		}
		orderService.create(order,details);
		
		cart.clear();	
		session.setAttribute("amountCart", cart.getAmount());
		return "redirect:/mstore/thank-you-and-wellcome-back";
	}
	
}
