package com.ptit.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ptit.model.Address;

@Repository
public interface AddressDao extends JpaRepository<Address, Long>{
	
}