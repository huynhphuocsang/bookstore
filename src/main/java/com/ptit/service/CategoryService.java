package com.ptit.service;


import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.ptit.exception.ResourceNotFoundException;
import com.ptit.model.Category;

public interface CategoryService {
	public Page<Category> getAllCategory(Pageable page);
	public Category getCategoryById(long id) throws ResourceNotFoundException; 
	public int getTotalCategory(); 
	public List<Category> getAllCategories(); 
}
