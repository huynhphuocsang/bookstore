package com.ptit.model;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.Size;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name="address")
@Getter
@Setter
public class Address {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="address_id")
	private long addressId; 
	
	@Column(name="address_name")
	@Size(max=45)
	private String addressName; //appartmentNumber + streetName
	
	@JsonIgnore
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name="village_id")
	private Village village; 
	
	
	

	 @ManyToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	   @JoinTable(name = "user_address",
	            joinColumns = @JoinColumn(name = "address_id"),  
	            inverseJoinColumns = @JoinColumn(name = "user_id")
	    )
	    private Set<User> setUsers = new HashSet<User>(); 
}
