package com.mstore.domain;

import java.io.Serializable;
import java.util.List;

import javax.persistence.*;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "Categories")

public class Category{

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	Integer id;
	String name;
	
	@OneToMany(mappedBy = "category",fetch = FetchType.EAGER)
	List<Product> products;
	
	
}
