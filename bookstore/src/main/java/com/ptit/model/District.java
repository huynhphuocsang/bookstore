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
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.Size;

import lombok.Data;

@Entity
@Table(name="district")
@Data
public class District {
	
	@Id
	@Column(name="district_id")
	private String districtId; 
	
	@Column(name="district_name")
	@Size(max=45)
	private String districtName; 
	
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name="province_id")
	private Province province; 
	
	@OneToMany(mappedBy = "district")
	Set<Village> setVillage= new HashSet<Village>(); 
	
	@Column(name="district_type")
	private String districtType; 
}













