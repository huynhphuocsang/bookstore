package com.ptit.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.Size;

import lombok.Data;

@Entity
@Table(name="village")
@Data
public class Village {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="village_id")
	private long villageId; 
	
	@Column(name="village_name")
	@Size(max=45)
	private String villageName; 
	
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name="district_id")
	private District district; 
	
	
}
















