package com.mstore.service;

import java.util.List;

import javax.validation.Valid;

import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

import com.mstore.domain.Order;
import com.mstore.domain.OrderDetail;

@Service
public interface OrderService {

	void create(Order order, List<OrderDetail> details);
	
	
	public Page<Order> getAllOrderPage(int currentPage);
	
	public List<Order> getAllOrder();


	void delete(Order order, List<OrderDetail> details);

}
