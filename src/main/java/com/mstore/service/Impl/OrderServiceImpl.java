package com.mstore.service.Impl;

import java.util.List;

import javax.transaction.Transactional;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Repository;

import com.mstore.domain.Order;
import com.mstore.domain.OrderDetail;
import com.mstore.domain.Product;
import com.mstore.repository.OrderDAO;
import com.mstore.repository.OrderDetailDAO;
import com.mstore.repository.ProductDAO;
import com.mstore.service.OrderService;


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
	public void delete(Order order, List<OrderDetail> details) {
		
		for(OrderDetail detail : details) {
			detailDao.delete(detail);
		}
		
		orderDao.delete(order);
		
	}

	@Override
	public List<Order> getAllOrder() {
		
		return orderDao.getAllOrder();
	}

	@Override
	public Page<Order> getAllOrderPageAndSearch(int currentPage,String keyword) {
		Pageable pageable = PageRequest.of(currentPage-1, 8);
		
		if(keyword != null) {
			return orderDao.getAllOrderPageAndSort(keyword,pageable);
		}
		
		return orderDao.findAll(pageable);
	}
	
	@Override
	public Page<Order> getAllOrderPage(int currentPage) {
		Pageable pageable = PageRequest.of(currentPage-1, 8);
		
		return orderDao.getAllOrderPage(pageable);
	}

	@Override
	public Page<Order> getTop8OrderPage(Pageable page) {
		return orderDao.getAllOrderPage(page);
	}


}
