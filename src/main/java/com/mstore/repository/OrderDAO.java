package com.mstore.repository;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.mstore.domain.Order;
import com.mstore.domain.OrderDetail;


@Repository
public interface OrderDAO extends JpaRepository<Order, Integer>{
	
	@Query("SELECT o FROM Order o ORDER BY o.id desc")
	public List<Order> getAllOrder();
	
	@Query("SELECT o FROM Order o ORDER BY o.id desc")
	public Page<Order> getAllOrderPage(Pageable pageable);

}
