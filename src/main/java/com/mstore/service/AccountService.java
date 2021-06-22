package com.mstore.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.mstore.domain.Account;

@Service
public interface AccountService {
	
	
	public Account getByUsername(String username);
	
	public List<Object[]> getHistory(String username);
}
