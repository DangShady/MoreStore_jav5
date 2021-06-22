package com.mstore.domain;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

import org.springframework.format.annotation.DateTimeFormat;

import lombok.*;


@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "accounts")

public class Account{
	
	@Id
	@NotEmpty(message = "{account.username.notnull}")
	String username;
	@NotEmpty(message = "{account.password.notnull}")
	String password;
	@NotEmpty(message = "{account.fullname.notnull}")
	String fullname;
	
	@Pattern(regexp = "\\d{10}", message="{account.phone.pattern}")
	String phone;
	
	@NotEmpty(message = "{account.email.notnull}")
	@Email(message = "{account.email.pattern}")
	String email;
	
	@NotEmpty(message = "{account.address.notnull}")
	String address;
	@Temporal(TemporalType.DATE)
	@DateTimeFormat(pattern="dd/MM/YYYY")
	Date dateregister;
	Boolean activated;
	Boolean admin;
	
	@OneToMany(mappedBy = "account",fetch = FetchType.EAGER)
	List<Order> orders;
}
