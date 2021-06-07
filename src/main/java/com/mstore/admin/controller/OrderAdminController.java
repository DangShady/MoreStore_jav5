package com.mstore.admin.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import com.mstore.domain.Order;
import com.mstore.domain.OrderDetail;
import com.mstore.repository.OrderDAO;
import com.mstore.service.OrderDetailService;
import com.mstore.service.OrderService;

@Controller
@RequestMapping("/admin/")
public class OrderAdminController {
	
	@Autowired
	OrderService orderService;
	
	@Autowired
	OrderDAO orderDao;
	
	@Autowired(required = false)
	OrderDetailService detailService;
	
	
	
	@GetMapping("order")
	public String lisrOrder(Model model) {
		
		List<Order> orders = orderService.getAllOrder();
		
		model.addAttribute("orders",orders);
		
		return "admin/orders/orders";
	}
	
	@GetMapping("order/confirm-order/{id}")
	public String confirmOrder(Model model,@PathVariable("id") int id) {
		
		Order order = orderDao.getById(id);
		
		order.setStatus(true);
		
		orderDao.save(order);
		
		return "redirect: order";
	}
	
	@GetMapping("order/detail-order/{id}")
	public String detailOrder(Model model,@PathVariable("id") int id) {
		
		List<OrderDetail> detail = detailService.findOrderById(id);
		
		model.addAttribute("details",detail);
		
		return "admin/orders/order-detail";
	}
	
}
