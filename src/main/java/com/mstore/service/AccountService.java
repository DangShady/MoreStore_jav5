package com.mstore.service;

import org.springframework.stereotype.Service;

import com.mstore.domain.Account;

@Service
public interface AccountService {
	
	
	public Account getByUsername(String username);
}
