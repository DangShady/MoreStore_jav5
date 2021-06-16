package com.mstore.service;

import java.util.List;

import javax.validation.Valid;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.mstore.domain.Order;
import com.mstore.domain.OrderDetail;

@Service
public interface OrderService {

	void create(Order order, List<OrderDetail> details);
	
	
	public Page<Order> getAllOrderPage(int currentPage);
	
	public Page<Order> getAllOrderPageAndSearch(int currentPage,String keyword);
	
	public Page<Order> getTop8OrderPage(Pageable page);
	
	public List<Order> getAllOrder();


	void delete(Order order, List<OrderDetail> details);

}
