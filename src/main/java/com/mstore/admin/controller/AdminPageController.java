package com.mstore.admin.controller;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.mstore.domain.Order;
import com.mstore.repository.AccountDAO;
import com.mstore.repository.CategoryDAO;
import com.mstore.repository.OrderDAO;
import com.mstore.repository.ProductDAO;
import com.mstore.service.OrderService;

@Controller
@RequestMapping("/admin/")
public class AdminPageController {

	@Autowired 
	HttpSession session;
	
	@Autowired
	OrderService orderService;
	
	@Autowired
	OrderDAO orderDao;
	
	@Autowired
	AccountDAO accDao;
	
	@Autowired
	ProductDAO proDao;
	
	@Autowired
	
	CategoryDAO cateDao;
	
	
	@RequestMapping({"admin-page","dashboard"})
	public String adminPage(Model model) {
		
		Pageable page = PageRequest.of(0, 8);
		
		Page<Order> list = this.orderService.getTop8OrderPage(page);
		
		int orderCount = (int) orderDao.count();
		
		int accCount = (int) accDao.count();
		
		int proCount = (int) proDao.count();
		
		int cateCount = (int) cateDao.count();
		
		
		
		model.addAttribute("orderCount",orderCount);
		model.addAttribute("accCount",accCount);
		model.addAttribute("proCount",proCount);
		model.addAttribute("cateCount",cateCount);
		model.addAttribute("top8order",list);
		
		return "admin/index";
	}
	
	@RequestMapping("logoff")
	public String logOffAdmin() {
		session.removeAttribute("ADMININJD");
		
		return "redirect:/mstore/home";
	}
	
	@RequestMapping("setting")
	public String setting() {
		
		return "admin/setting";
	}
	
	
}
