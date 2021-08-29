package com.ptit.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ptit.model.Category;

@Repository
public interface CategoryDao extends JpaRepository<Category, Long>{
	
}
