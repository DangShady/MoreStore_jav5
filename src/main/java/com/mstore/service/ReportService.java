package com.mstore.service;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface ReportService {
	
	public Page<Object[]> inventory(int pageNumber);
	
	public List<Object[]> revenueByCategory();
	
	public Page<Object[]> revenueByCustomer(int pageNumber);
	
	public List<Object[]> revenueByYear();
	
	public List<Object[]> revenueByQuater();
	
	public List<Object[]> revenueByMonth();
	
	public Page<Object[]> topProductDashboard(Pageable page);
	
}
