package com.ptit.controller;


import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.ptit.model.Book;
import com.ptit.model.Category;
import com.ptit.model.Product;
import com.ptit.service.BookService;
import com.ptit.service.CategoryService;
import com.ptit.serviceImp.CategoryServiceImp;

@Controller
@RequestMapping("/home")
public class HomeController {
	

	@Autowired
	BookService bookservice; 
	
	@Autowired
	CategoryService categorySerivce; 
	
	@GetMapping
	public String home(ModelMap map) {
		List<Category> listCategory = categorySerivce.getAllCategory(); 
		List<Book> arraylist = bookservice.getAllBooks(); 

		map.addAttribute("list", arraylist); 
		map.addAttribute("categories", listCategory); 
		return "home"; 
	}
	
}























