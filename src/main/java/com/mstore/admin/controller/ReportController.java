package com.mstore.admin.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import com.mstore.domain.Product;
import com.mstore.service.ReportService;

@Controller
@RequestMapping("/admin/")

public class ReportController {
	
	@Autowired
	ReportService reportService;
	
	
	@GetMapping("inventory")
	public String inventory(Model model) {
		
		return inventoryAndPage(model,1);
		
	}
	
	@GetMapping("inventory/page/{pageNumber}")
	public String inventoryAndPage(Model model,@PathVariable("pageNumber") int currentPage) {
		
		Page<Object[]> page = this.reportService.inventory(currentPage);

		long totalItems = page.getTotalElements();

		int totalPages = page.getTotalPages();

		List<Object[]> inventory = page.getContent();

		model.addAttribute("currentPage", currentPage);

		model.addAttribute("totalItems", totalItems);
		model.addAttribute("totalPages", totalPages);

		model.addAttribute("data", inventory);
		
		return "admin/report/inventory";
		
	}
	
	@GetMapping("revenue/customer")
	public String revenueByCustomer(Model model) {
				
		return revenueByCustomerAndPage(model,1);
		
	}
	
	@GetMapping("revenue/customer/page/{pageNumber}")
	public String revenueByCustomerAndPage(Model model,@PathVariable("pageNumber") int currentPage) {
		
		Page<Object[]> page = this.reportService.revenueByCustomer(currentPage);

		long totalItems = page.getTotalElements();

		int totalPages = page.getTotalPages();

		List<Object[]> customer = page.getContent();

		model.addAttribute("currentPage", currentPage);

		model.addAttribute("totalItems", totalItems);
		model.addAttribute("totalPages", totalPages);

		model.addAttribute("data", customer);
		
		return "admin/report/revenue-customer";
		
	}
	
	@GetMapping("revenue/category")
	public String revenueByCategory(Model model) {
		
		model.addAttribute("data",reportService.revenueByCategory());
		
		return "admin/report/revenue-category";
		
	}
	
	
	
	@GetMapping("revenue/year")
	public String revenueByYear(Model model) {
		
		model.addAttribute("data",reportService.revenueByYear());
		
		return "admin/report/revenue-year";
		
	}
	
	@GetMapping("revenue/month")
	public String revenueByMonth(Model model) {
		
		model.addAttribute("data",reportService.revenueByMonth());
		
		return "admin/report/revenue-month";
		
	}
	
	@GetMapping("revenue/quater")
	public String revenueByQuater(Model model) {
		
		model.addAttribute("data",reportService.revenueByQuater());
		
		return "admin/report/revenue-quater";
		
	}
	
}
