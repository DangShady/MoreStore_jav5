package com.mstore.admin.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import com.mstore.domain.Order;
import com.mstore.domain.OrderDetail;
import com.mstore.domain.Product;
import com.mstore.repository.OrderDAO;
import com.mstore.repository.OrderDetailDAO;
import com.mstore.service.OrderDetailService;
import com.mstore.service.OrderService;

@Controller
@RequestMapping("/admin/")
public class OrderAdminController {
	
	@Autowired
	OrderService orderService;
	
	@Autowired
	OrderDAO orderDao;
	
	@Autowired
	OrderDetailDAO detailDao;
	
	@Autowired(required = false)
	OrderDetailService detailService;
	
	
	
	@GetMapping("order")
	public String lisrOrder(Model model) {
		
//		List<Order> orders = orderService.getAllOrder();
//		
//		model.addAttribute("orders",orders);
		
		return listOrderByPage(model,1);
	}
	
	@GetMapping("order/page/{pageNumber}")
	public String listOrderByPage(Model model, @PathVariable("pageNumber") int currentPage) {
		
		
		Page<Order> page = this.orderService.getAllOrderPage(currentPage);
		
		long totalItems= page.getTotalElements();
		
		int totalPages = page.getTotalPages();
		
		
		List<Order> listOrders = page.getContent();
		
		model.addAttribute("currentPage",currentPage);	
		
		model.addAttribute("totalItems",totalItems);
		model.addAttribute("totalPages",totalPages);
		
		
		model.addAttribute("orders", listOrders);
		
		
		return "admin/orders/orders";
	}
	
	@GetMapping("order/confirm-order/{id}")
	public String confirmOrder(Model model,@PathVariable("id") int id) {
		
		Order order = orderDao.getById(id);
		
		order.setStatus(true);
		
		orderDao.save(order);
		
		return "redirect:/admin/order";
	}
	
	@GetMapping("order/detail-order/{id}")
	public String detailOrder(Model model,@PathVariable("id") int id) {
		
		Order order = orderDao.getById(id);
		
		
		model.addAttribute("details",detailDao.findByOrder(order));
		
		model.addAttribute("description",order.getDescription());
		
		return "admin/orders/order-detail";
	}
	
	@GetMapping("order/delete-order/{id}")
	public String deleteOrder(Model model,@PathVariable("id") int id) {
		
		Order order = orderDao.getById(id);
		
		if(order.getStatus() == false) {
			List<OrderDetail> details = detailDao.findByOrder(order);
			
			orderService.delete(order,details);
			
			orderDao.delete(order);
		}
		else {
			return "redirect:/admin/order";
		}
			
		return "redirect:/admin/order";
	}
	
}
