package com.mstore.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.mstore.domain.Order;
import com.mstore.domain.OrderDetail;

@Repository
public interface OrderDetailDAO extends JpaRepository<OrderDetail, Integer>{
	
	public List<OrderDetail> findByOrder(Order order);
	
//	@Query("DELETE d FROM OrderDetail d WHERE d.order.id =?1")
//	public void deleteByOrder(Integer orderId);
	
	
}
