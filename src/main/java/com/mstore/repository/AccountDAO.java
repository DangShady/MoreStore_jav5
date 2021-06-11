package com.mstore.repository;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.mstore.domain.Account;


@Repository
public interface AccountDAO extends JpaRepository<Account, String>{

	@Query("SELECT a FROM Account a ORDER BY a.dateregister desc")
	public Page<Account> getAllCustomer(Pageable pageable);
	
}
