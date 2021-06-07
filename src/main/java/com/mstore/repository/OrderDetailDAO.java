package com.mstore.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.mstore.domain.Order;
import com.mstore.domain.OrderDetail;

@Repository
public interface OrderDetailDAO extends JpaRepository<OrderDetail, Integer>{

	@Query("SELECT d FROM OrderDetail d WHERE d.order = ?1")
	public List<OrderDetail> getDetailById(Integer orderId);
	
}
