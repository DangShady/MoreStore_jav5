package com.mstore.repository;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.mstore.domain.Order;
import com.mstore.domain.OrderDetail;

@Repository
public interface OrderDetailDAO extends JpaRepository<OrderDetail, Integer>{
	
	public List<OrderDetail> findByOrder(Order order);
	
	
//	History by customer
	@Query("SELECT o.id,o.orderdate,COUNT(d.product.name),"
			+ "o.amount,o.status FROM OrderDetail d,Order o WHERE o.id = d.order.id AND d.order.account.username = ?1"
			+ " GROUP BY o.id,o.orderdate,o.amount,o.status"
			+ "  ORDER BY o.id DESC")
	public List<Object[]> getHistoryByCustomer(String username);
	
	@Query("SELECT p.category.name,SUM(p.quantity),SUM(p.price*p.quantity),"
			+ "MIN(p.price),MAX(p.price),AVG(p.price) FROM Product p GROUP BY p.category.name")
	public List<Object[]> inventory();
	
	@Query("SELECT SUM(d.amount)"
			+ " FROM Order d WHERE d.status = true")
	public Object[] revenueByDashboardTotalSales();
	
	@Query("SELECT AVG(d.amount)"
			+ " FROM Order d WHERE d.status = true")
	public Object[] revenueByDashboardAvgSales();
	
	@Query("SELECT SUM(d.amount)"
			+ " FROM Order d"
			+ " WHERE DATEPART(MONTH,GETDATE()) = DATEPART(MONTH,d.orderdate) AND d.status = true")
	public Object[] revenueByDashboardByMonth();
	
	@Query("SELECT SUM(d.amount)"
			+ " FROM Order d"
			+ " WHERE DATEPART(WEEK,GETDATE()) = DATEPART(WEEK,d.orderdate) AND d.status = true")
	public Object[] revenueByDashboardByWeek();
	
	@Query("SELECT d.product.name,d.product.image,d.product.price,SUM(d.quantity),SUM(d.price*d.quantity)"
			+ " FROM OrderDetail d"
			+ " GROUP BY d.product.name,d.product.image,d.product.price ORDER BY SUM(d.quantity) DESC")
	public Page<Object[]> topProductDashboard(Pageable pageable);
	
	@Query("SELECT p.name,SUM(p.quantity),SUM(p.price*p.quantity),"
			+ "MIN(p.price),MAX(p.price),AVG(p.price) FROM Product p"
			+ " GROUP BY p.name ORDER BY SUM(p.quantity) ASC")
	public Page<Object[]> inventoryByProduct(Pageable page);
	
	@Query("SELECT d.product.category.name,SUM(d.quantity),SUM(d.price*d.quantity),"
			+ "MIN(d.price),MAX(d.price),AVG(d.price) FROM OrderDetail d WHERE d.order.status = true"
			+ " GROUP BY d.product.category.name ORDER BY SUM(d.price*d.quantity) DESC")
	public List<Object[]> revenueByCategory();
	
	@Query("SELECT d.order.account.fullname,SUM(d.quantity),SUM(d.price*d.quantity),"
			+ "MIN(d.price),MAX(d.price),AVG(d.price) FROM OrderDetail d WHERE d.order.status = true"
			+ " GROUP BY d.order.account.fullname ORDER BY SUM(d.price*d.quantity) DESC")
	public List<Object[]> revenueByCustomer();
	
	@Query("SELECT d.order.account.fullname,SUM(d.quantity),SUM(d.price*d.quantity),"
			+ "MIN(d.price),MAX(d.price),AVG(d.price) FROM OrderDetail d WHERE d.order.status = true"
			+ " GROUP BY d.order.account.fullname ORDER BY SUM(d.price*d.quantity) DESC")
	public Page<Object[]> revenueByCustomerAndPage(Pageable page);
	
	@Query("SELECT YEAR(d.order.orderdate),SUM(d.quantity),SUM(d.price*d.quantity),"
			+ "MIN(d.price),MAX(d.price),AVG(d.price) FROM OrderDetail d WHERE d.order.status = true"
			+ " GROUP BY YEAR(d.order.orderdate) ORDER BY YEAR(d.order.orderdate) DESC")
	public List<Object[]> revenueByYear();
	
	@Query("SELECT MONTH(d.order.orderdate),SUM(d.quantity),SUM(d.price*d.quantity),"
			+ "MIN(d.price),MAX(d.price),AVG(d.price) FROM OrderDetail d WHERE d.order.status = true"
			+ " GROUP BY MONTH(d.order.orderdate) ORDER BY MONTH(d.order.orderdate) DESC")
	public List<Object[]> revenueByMonth();
	
	@Query("SELECT CEILING(MONTH(d.order.orderdate)/3.0),SUM(d.quantity),SUM(d.price*d.quantity),"
			+ "MIN(d.price),MAX(d.price),AVG(d.price) FROM OrderDetail d WHERE d.order.status = true"
			+ " GROUP BY CEILING(MONTH(d.order.orderdate)/3.0)"
			+ " ORDER BY CEILING(MONTH(d.order.orderdate)/3.0)")
	public List<Object[]> revenueByQuater();
	
	
}
