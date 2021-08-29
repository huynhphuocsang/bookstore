package com.ptit.model;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.Email;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

import lombok.Data;

@Entity
@Table(name="user")
@Data
public class User {

	//bo: idgiohang
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="user_id")
	private long userId; 
	
	@NotEmpty
	private String username; 
	
	@Size(min=10,max=10)
	private String phone; 
	
	@Email
	private String email; 
	
	@Min(10)
	private int age; 
	
	@Size(max=45)
	private String password; 
	
	@ManyToOne
	@JoinColumn(name="id_role")
	private Role role; 
	
	@ManyToOne
	@JoinColumn(name="id_gender")
	private Gender gender; 
	
	@OneToMany(fetch = FetchType.LAZY,mappedBy = "user")
	private Set<Items> setItems = new HashSet<Items>(); 
	
	
	@ManyToMany(fetch = FetchType.LAZY,mappedBy = "setUsers")
	Set<Address> setAddress = new HashSet<Address>(); 
	
//	@OneToMany(fetch = FetchType.LAZY,mappedBy = "user")
//	private Set<Order> setOrders = new HashSet<Order>(); 
}

















