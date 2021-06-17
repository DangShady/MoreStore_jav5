package com.mstore.service.Impl;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;

import com.mstore.repository.OrderDetailDAO;
import com.mstore.service.ReportService;

@Transactional
@Repository
public class ReportServiceImpl implements ReportService{

	@Autowired
	OrderDetailDAO detailDao;
	
	
	@Override
	public Page<Object[]> inventory(int pageNumber) {
		
		Pageable pageable = PageRequest.of(pageNumber-1, 8);
		
		
		return detailDao.inventoryByProduct(pageable);
	}

	@Override
	public List<Object[]> revenueByCategory() {
		
		return detailDao.revenueByCategory();
	}

	@Override
	public Page<Object[]> revenueByCustomer(int pageNumber) {
		
		Pageable pageable = PageRequest.of(pageNumber-1, 8);
		
		return detailDao.revenueByCustomerAndPage(pageable);
	}

	@Override
	public List<Object[]> revenueByYear() {
		// TODO Auto-generated method stub
		return detailDao.revenueByYear();
	}

	@Override
	public List<Object[]> revenueByQuater() {
		// TODO Auto-generated method stub
		return detailDao.revenueByQuater();
	}

	@Override
	public List<Object[]> revenueByMonth() {
		// TODO Auto-generated method stub
		return detailDao.revenueByMonth();
	}

}
