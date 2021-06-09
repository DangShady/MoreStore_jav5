package com.mstore.admin.controller;

import java.util.Date;
import java.util.Iterator;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.mstore.domain.Account;
import com.mstore.domain.Order;
import com.mstore.service.CustomerService;
import com.mstore.service.OrderService;

@Controller
@RequestMapping("/admin/")
public class CustomerController {
	
	@Autowired()
	CustomerService cusService;
	
	@Autowired
	OrderService orderService;
	
	
	@GetMapping("customer")
	public String customer(Model model) {
		
		
		List<Account> listCustomer = cusService.getAllCustomer();

		
		model.addAttribute("customers",listCustomer);
		
		return "admin/customers/customer";
	}
}
