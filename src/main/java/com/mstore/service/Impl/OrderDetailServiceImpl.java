package com.mstore.service.Impl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;

import com.mstore.domain.OrderDetail;
import com.mstore.repository.OrderDetailDAO;
import com.mstore.service.OrderDetailService;

public class OrderDetailServiceImpl implements OrderDetailService{

	@Autowired
	OrderDetailDAO detailDao;
	
	@Override
	public List<OrderDetail> findOrderById(int id) {
		return detailDao.getDetailById(id);
	}

}
