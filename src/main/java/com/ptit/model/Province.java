package com.ptit.model;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.Size;

import lombok.Data;

@Entity
@Table(name="province")
@Data
public class Province {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="province_id")
	private long provinceId; 
	
	@Column(name="province_name")
	@Size(max=45)
	private String provinceName; 
	
	
	@OneToMany(mappedBy = "province")
	Set<District> setDistrict = new HashSet<District>(); 
	
	
}













