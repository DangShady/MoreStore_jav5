package com.mstore.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.mstore.domain.Account;

@Service
public interface CustomerService {

	public List<Account> getAllCustomer();
	
	
}
