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
@Table(name = "OrderDetails")

public class OrderDetail implements Serializable{
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	Integer id;
	@ManyToOne @JoinColumn(name = "Orderid")
	Order order;
	
	@ManyToOne @JoinColumn(name = "Productid")
	Product product;
	
	String size;
	String color;
	Double price;
	Integer quantity;

}
