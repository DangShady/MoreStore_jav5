package com.mstore.domain;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import javax.persistence.*;

import org.springframework.format.annotation.DateTimeFormat;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "orders")

public class Order{
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	Integer id;
	
	@Temporal(TemporalType.DATE)
	@DateTimeFormat(pattern="dd/MM/YYYY")
	Date orderdate;
	String address;
	String fullname;
	String phone;
	Double amount;
	Boolean status;
	String description;
	
	@ManyToOne @JoinColumn(name = "username")
	Account account;
	
	@OneToMany(mappedBy = "order",fetch = FetchType.EAGER)
	List<OrderDetail> orderDetails;
}
