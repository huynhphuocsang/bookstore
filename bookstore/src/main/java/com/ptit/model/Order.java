package com.ptit.model;

import java.io.Serializable;
import java.math.BigDecimal;
import java.sql.Date;
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
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.Size;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name="orders")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Order  implements Serializable{
	//bo: ngayketthuc
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="order_id")
	private long orderId; 
	
	@Column(name="order_status")
	private int orderStatus; 
	
	@Column(name="total_price")
	private BigDecimal totalPrice;
	
	@Column(name="order_day")
	private Date orderDay; 
	
	@Column(name="name_of_customer")
	@Size(max=45)
	private String nameOfCustomer; 
	
	@Column(name="phone_of_customer")
	@Size(max=10)
	private String phoneOfCustomer; 
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name="user_id")
	private User user; 
	
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="address_id")
	private Address address; 
	
//	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER, mappedBy = "order")
	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
	@JoinColumn(name="order_id")
	private Set<OrderDetail> setDetails = new HashSet<OrderDetail>(); 
	
	
	

}














