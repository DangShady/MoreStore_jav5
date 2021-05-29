package com.mstore.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.mstore.domain.Order;


@Repository
public interface OrderDAO extends JpaRepository<Order, Integer>{

}
