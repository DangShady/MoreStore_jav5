package com.mstore.service;

import java.util.List;

import javax.validation.Valid;

import org.springframework.stereotype.Service;

import com.mstore.domain.Order;
import com.mstore.domain.OrderDetail;

@Service
public interface OrderService {

	public void create(Order order, List<OrderDetail> details);
	

	public List<Order> getAllOrder();

}
