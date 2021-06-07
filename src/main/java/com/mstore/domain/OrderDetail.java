package com.mstore.domain;

import java.io.Serializable;

import javax.persistence.*;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "orderdetails")

public class OrderDetail{
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	Integer id;
	
	Double price;
	Integer quantity;
	
	@ManyToOne @JoinColumn(name = "orderid")
	Order order;
	
	@ManyToOne @JoinColumn(name = "productid")
	Product product;
	
	

}
