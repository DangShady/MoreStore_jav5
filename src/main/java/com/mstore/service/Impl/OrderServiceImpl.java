package com.mstore.service.Impl;

import java.util.List;

import javax.transaction.Transactional;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.mstore.domain.Order;
import com.mstore.domain.OrderDetail;
import com.mstore.repository.OrderDAO;
import com.mstore.repository.OrderDetailDAO;
import com.mstore.service.OrderService;

@Transactional
@Repository
public class OrderServiceImpl implements OrderService{

	@Autowired
	OrderDAO orderDao;
	
	@Autowired
	OrderDetailDAO detailDao;
	
	@Override
	public void create(Order order, List<OrderDetail> details) {
		
		orderDao.save(order);
		
		for(OrderDetail detail : details) {
			detailDao.save(detail);	
		}
	}

	@Override
	public List<Order> getAllOrder() {
		
		return orderDao.getAllOrder();
	}

}
