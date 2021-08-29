package com.ptit.serviceImp;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ptit.model.Category;
import com.ptit.repository.CategoryDao;
import com.ptit.service.CategoryService;

@Service
public class CategoryServiceImp implements CategoryService{

	@Autowired
	CategoryDao categorydao; 
	@Override
	public List<Category> getAllCategory() {
		
		return categorydao.findAll();
	}
	
}
