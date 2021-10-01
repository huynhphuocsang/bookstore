package com.ptit.serviceImp;


import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.ptit.exception.ResourceNotFoundException;
import com.ptit.model.Category;
import com.ptit.repository.CategoryDao;
import com.ptit.service.CategoryService;

@Service
public class CategoryServiceImp implements CategoryService{

	@Autowired
	CategoryDao categorydao; 
	
	@Override
	public Category getCategoryById(long id) throws ResourceNotFoundException {
		Optional<Category> category= categorydao.findById(id); 
		if(!category.isPresent()) {
			throw new ResourceNotFoundException("Category not found!"); 
		}
		return category.get(); 
	}
	@Override
	public int getTotalCategory() {
		
		return categorydao.findAll().size();
	}
	@Override
	public Page<Category> getAllCategory(Pageable page) {
		return categorydao.findAll(page);   
	}
	@Override
	public List<Category> getAllCategories() {
		return categorydao.findAll(); 
	}
	
	@Override
	public long getCategoryIdByName(String name) throws ResourceNotFoundException {
		Optional<Category> category = categorydao.findByNameAllIgnoreCase(name);
		if(!category.isPresent()) {
			throw new ResourceNotFoundException("Author doesn't exists"); 
		}
		return category.get().getCategoryId();
	}
	
	@Override
	public int save(Category category) {
		categorydao.save(category);
		return categorydao.getLastIdCategory();
	}
	
	@Override
	public Category selectOrUpdateCategory(String name) {
		long id = 0;
		try {
			id=this.getCategoryIdByName(name);
			return categorydao.getById(id);
		} catch (ResourceNotFoundException e) {
			Category newCategory=new Category();
			newCategory.setName(name);
			id=this.save(newCategory);
			return categorydao.getById(id);
		}
	}
}
