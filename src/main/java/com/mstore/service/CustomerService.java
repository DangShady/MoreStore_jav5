package com.mstore.service;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

import com.mstore.domain.Account;

@Service
public interface CustomerService {

	public Page<Account> getAllCustomer(int currentPage,String keyword);
	
	public Page<Account> getAdmin(int currentPage,String keyword);
	
	public Page<Account> getCustomer(int currentPage,String keyword);
	
	
}
