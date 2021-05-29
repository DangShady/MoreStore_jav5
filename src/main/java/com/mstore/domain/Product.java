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
@Table(name = "Products")

public class Product implements Serializable {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	Integer id;
	String name;
	Double price;
	String image;
	
//	@DateTimeFormat(pattern="dd/MM/YYYY")
	@Temporal(TemporalType.DATE)
	Date productdate;

	@ManyToOne @JoinColumn(name = "Categoryid")
	Category category;
	
	@OneToMany(mappedBy = "product")
	List<OrderDetail> orderDetails;
	
	Integer quantity;
	String description;
}
