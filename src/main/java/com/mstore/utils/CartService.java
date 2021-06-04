package com.mstore.utils;

import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Service;
import org.springframework.web.context.annotation.SessionScope;

import com.mstore.domain.Product;
import com.mstore.service.ProductService;


@SessionScope	//Name: scopedTarget.cartService
@Service
public class CartService {
	
	
	@Autowired
	ProductService proService;
	
	Map<Integer, Product> map = new HashMap<>();
	

	public void add(Integer id) {
		
		Product p = map.get(id);
		if(p == null) {
			p = proService.getProductDetailById(id);
			p.setQuantity(1);
			
			map.put(id, p);
		}
		else {
			p.setQuantity(p.getQuantity()+1);
		}
	}
	
	public void remove(Integer id) {
		map.remove(id);
	}
	
	public void update(Integer id, int qty) {
		Collection<Product> ps = this.getItems();

		for(Product p : ps){
			p = map.get(id);
			p.setQuantity(qty);
		};
	}
	
	public void clear() {
		map.clear();
	}
	
	public int getCount() {
		
		Collection<Product> ps = this.getItems();
		int count = 0;
		for(Product p : ps){
			count += p.getQuantity();
		};
		
		return count;
	}
	
	public double getAmount() {
		
		Collection<Product> ps = this.getItems();
		double amount = 0;
		for(Product p : ps){
			amount += p.getQuantity() * p.getPrice();
		};
		
		return amount;
	}
	
	public Collection<Product> getItems(){
		return map.values();
	}
}
