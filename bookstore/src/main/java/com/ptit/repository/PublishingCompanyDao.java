package com.ptit.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.ptit.model.Author;
import com.ptit.model.PublishingCompany;
@Repository
public interface PublishingCompanyDao extends JpaRepository<PublishingCompany, Long>{
	public Optional<PublishingCompany> findByNameAllIgnoreCase(String name); 
	
	@Query("SELECT  MAX(idCompany) from PublishingCompany")
	public int getLastIdCompany();
	
	
}
