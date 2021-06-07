package com.mstore.admin.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/admin/")
public class AdminPageController {

	@RequestMapping({"admin-page","dashboard"})
	public String adminPage() {
		
		return "admin/index";
	}
	
//	@RequestMapping("order")
//	public String order() {
//		
//		return "admin/orders";
//	}
	
	
	@RequestMapping("customer")
	public String customer() {
		
		return "admin/customer";
	}
	
	@RequestMapping("setting")
	public String setting() {
		
		return "admin/setting";
	}
	
//	@RequestMapping("order-detail")
//	public String oderDetail() {
//		
//		return "admin/order-detail";
//	}
//	
//	@RequestMapping("customer-detail")
//	public String customerDetail() {
//		
//		return "admin/customer-detail";
//	}
//	
//	@RequestMapping("add-product")
//	public String addProduct() {
//		
//		return "admin/add-product";
//	}
//	
//	@RequestMapping("add-customer")
//	public String addCustomer() {
//		
//		return "admin/add-customer";
//	}
//	
//	@RequestMapping("add-category")
//	public String addCategory() {
//		
//		return "admin/add-category";
//	}
	
}
