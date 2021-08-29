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



import lombok.Data;

@Entity
@Table(name="address")
@Data
public class Address {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="address_id")
	private long addressId; 
	
	@Column(name="address_name")
	@Size(max=45)
	private String addressName; //appartmentNumber + streetName
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name="village_id")
	private Village village; 
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name="district_id")
	private District district; 
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name="province_id")
	private Province province; 
	
	
	 @ManyToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	   @JoinTable(name = "user_address",
	            joinColumns = @JoinColumn(name = "address_id"),  
	            inverseJoinColumns = @JoinColumn(name = "user_id")
	    )
	    private Set<User> setUsers = new HashSet<User>(); 
}
