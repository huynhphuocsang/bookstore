package com.ptit.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import com.ptit.exception.ResourceNotFoundException;
import com.ptit.model.Book;
import com.ptit.model.Product;
import com.ptit.service.BookService;

@Controller
@RequestMapping("/book")
public class BookDetailController {

	@Autowired
	BookService bookservice; 
	
		@GetMapping("/{id}")
		public String productDetail(@PathVariable long id, ModelMap map)  {
			
			Book book;
			try {
				book = bookservice.getBookById(id);
			} catch (ResourceNotFoundException e) {
				return "notfound"; 
			} 
			map.addAttribute("book", book); 
			return "product-detail"; 
		}
}



















