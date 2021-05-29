package com.mstore.domain;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import javax.persistence.*;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "Orders")

public class Order implements Serializable{
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	Integer id;
	
	@ManyToOne @JoinColumn(name = "Username")
	Account account;
	
	@Temporal(TemporalType.DATE)
	@Column(name = "OrderDate")
	Date orderDate = new Date();
	String address;
	Double amount;
	Boolean status;
	String description;
	
	@OneToMany(mappedBy = "order")
	List<OrderDetail> orderDetails;
}
