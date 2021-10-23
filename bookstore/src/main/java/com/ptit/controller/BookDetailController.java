package com.ptit.controller;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import com.ptit.exception.ResourceNotFoundException;
import com.ptit.model.Author;
import com.ptit.model.Book;
import com.ptit.model.Category;

import com.ptit.model.PublishingCompany;
import com.ptit.repository.PublishingCompanyDao;
import com.ptit.service.AuthorService;
import com.ptit.service.BookService;
import com.ptit.service.CategoryService;
import com.ptit.service.PublishingCompanyService;

@Controller
@RequestMapping("/book")
public class BookDetailController {

	@Autowired
	BookService bookservice; 
	 
	@Autowired
	AuthorService authorService; 
	
	@Autowired
	CategoryService categoryService; 
	
	@Autowired
	PublishingCompanyService companyService; 
	
		@GetMapping("/{id}")
		public String productDetail(@PathVariable long id, ModelMap map) throws ResourceNotFoundException  {
			
			Book book;
			Author author; 
			PublishingCompany company; 
			try {
				book = bookservice.getBookById(id);
				author = authorService.getAuthorById(book.getAuthor().getIdAuthor()); 
				company = companyService.getCompanyById(id); 
			} catch (ResourceNotFoundException e) {
				return "notfound"; 
			}
			List<Category> listCategory = categoryService.getAllCategories();
			
			map.addAttribute("book", book); 
			map.addAttribute("author", author); 
			map.addAttribute("company", company); 
			map.addAttribute("categories", listCategory);
			return "product-detail"; 
		}
		
//		public String bookByCategory(String categoryId) {
//			List<Book> list = bookservice.getBookByCategory(categoryId); 
//			
//		}
}



















