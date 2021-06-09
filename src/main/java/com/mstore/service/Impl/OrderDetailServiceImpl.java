package com.mstore.service.Impl;

import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.mstore.domain.Order;
import com.mstore.domain.OrderDetail;
import com.mstore.repository.OrderDetailDAO;
import com.mstore.service.OrderDetailService;

@Repository
public class OrderDetailServiceImpl implements OrderDetailService{

	@Autowired
	OrderDetailDAO detailDao;
	

}
