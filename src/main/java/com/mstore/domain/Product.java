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
@Table(name = "products")

public class Product{
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	Integer id;
	String name;
	Double price;
	String image;
	
	@Column
	@Temporal(TemporalType.DATE)
	@DateTimeFormat(pattern="MM/dd/yyyy")
	Date productdate;

	@ManyToOne @JoinColumn(name = "categoryid")
	Category category;
	
	Integer quantity;
	String description;
	
	@OneToMany(mappedBy = "product",fetch = FetchType.EAGER)
	List<OrderDetail> orderdetails;
}
