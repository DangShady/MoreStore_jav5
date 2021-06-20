package com.mstore.domain;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.PositiveOrZero;

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
	@NotEmpty(message = "{product.name.notempty}")
	String name;
	
	@NotNull(message = "{product.price.notempty}")
	@PositiveOrZero(message = "{product.price.zero}")
	Double price;
	
	String image;
	
	@Column
	@Temporal(TemporalType.DATE)
	@DateTimeFormat(pattern="MM/dd/yyyy")
	@NotNull(message = "{product.date.notempty}")
	Date productdate;

	@ManyToOne @JoinColumn(name = "categoryid")
	Category category;
	
	@NotNull(message = "{product.quantity.notempty}")
	@PositiveOrZero(message = "{product.quantity.zero}")
	Integer quantity;
	String description;
	
	@OneToMany(mappedBy = "product",fetch = FetchType.EAGER)
	List<OrderDetail> orderdetails;
}
