package com.mstore.service;

import java.util.List;
import java.util.Optional;

import com.mstore.domain.OrderDetail;

public interface OrderDetailService {

	List<OrderDetail> findOrderById(int id);

}
