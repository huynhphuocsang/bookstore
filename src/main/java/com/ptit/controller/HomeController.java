package com.ptit.controller;


import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.ptit.model.Book;
import com.ptit.model.Category;

import com.ptit.repository.BookDao;
import com.ptit.service.BookService;
import com.ptit.service.CategoryService;

@Controller
@RequestMapping("/home")
public class HomeController {
	

	@Autowired
	BookService bookservice; 
	
	
	
	@Autowired
	CategoryService categorySerivce; 
	
//	@GetMapping
//	public String home(ModelMap map) {
//		List<Category> listCategory = categorySerivce.getAllCategory(); 
//		List<Book> arraylist = bookservice.getAllBooks(); 
//
//		map.addAttribute("list", arraylist); 
//		map.addAttribute("categories", listCategory); 
//		return "home"; 
//	}
	
	@GetMapping("/view")
	public String pagingHomePage(@RequestParam("page") Optional<Integer> page, ModelMap map) {
		int quantityBooksOnPage = 2; 
		Pageable pageable; 
		
		if(!page.isPresent()) {
			return "redirect:/home/view?page=0"; 
		}
		else {
			if(page.get()<0) {
				return "redirect:/home/view?page=0"; 
			}else if(page.get()>= Math.ceil((float)bookservice.getTotalQuantity()/quantityBooksOnPage)) {
				
				return "redirect:/home/view?page="+(int) (Math.ceil((float)bookservice.getTotalQuantity()/quantityBooksOnPage)-1); 
			}
			
			
			pageable =  PageRequest.of(page.get(), quantityBooksOnPage); 
			Page<Book> listPage = bookservice.getAllBooks(pageable); 
			map.addAttribute("categories", categorySerivce.getAllCategory());
			map.addAttribute("list", listPage);
			
		}
		
		return "home"; 
	}
	
}























