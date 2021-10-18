package com.ptit.controller;


import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import javax.servlet.http.HttpServletRequest;

import org.hibernate.internal.build.AllowSysOut;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.ptit.exception.ResourceNotFoundException;
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
	


	@GetMapping("/view")
	public String getView(ModelMap map) {

		Optional<Integer> page = Optional.of(1);  
		
		
		return "redirect:/home/view/1"; 
	}
	@GetMapping("/view/{pageNo}")
	public String pagingHomePage(@PathVariable Optional<String> pageNo, ModelMap map) {
		int pageSize = 2; 
		String sortDir = "desc"; 
		String sortField = "idBook"; 
		int pageNoOffical = 1; 
		
		try {
			 pageNoOffical = Integer.parseInt(pageNo.get());
		}catch(Exception ex) {
			return "redirect:/home/view/1"; 
		}
		 
			List<Category> listCategory = categorySerivce.getAllCategories(); 
			Page<Book> page = bookservice.findPaginated(pageNoOffical, pageSize, sortField, sortDir); 
			List<Book> list = page.getContent(); 
			map.addAttribute("list", list);
			map.addAttribute("currentPage",pageNoOffical); 
			map.addAttribute("totalPage", page.getTotalPages()); 
			map.addAttribute("pageFirst", 1); 
			map.addAttribute("categories", listCategory); 
		
		
		return "home"; 
	}
	
	

	
	@GetMapping("/category")
	public String bookByCategoryDefault() {
		
		
		return "redirect:/home/view"; 
	}
	
	@GetMapping("/category/{categoryId}")
	public String bookByCategory(@PathVariable Optional<String> categoryId,@RequestParam Optional<String> pageNo, ModelMap map ) {
		
		int pageNoOffical = 1; 
		long categoryIdOffical = 1; 
		
		
		
		try {
		categoryIdOffical = Long.parseLong(categoryId.get());  	
		}catch(Exception ex) {
			return "redirect:/home/view"; 
		}
		try {
			pageNoOffical = Integer.parseInt(pageNo.get()); 
			}catch(Exception ex) {
				return "redirect:/home/category/"+categoryIdOffical+"?pageNo=1"; 
			}
		
		
		int pageSize = 2; 
		String sortDir = "desc"; 
		String sortField = "idBook"; 
		
		Category category=null;
		try {
			category = categorySerivce.getCategoryById(categoryIdOffical);
		} catch (ResourceNotFoundException e) {
			return "notfound"; 
		} 
		
		
		List<Book> listtemp = bookservice.getBookByCategory(category); 
		
		
		Page<Book> page =  bookservice.getPageViaCategory(pageNoOffical, pageSize, sortField, sortDir, category);
		
		List<Book> listShow = page.getContent();
		if(listShow.size()==0) return "notfound"; 
		
		map.addAttribute("categories", categorySerivce.getAllCategories());
		map.addAttribute("list", listShow);
		map.addAttribute("category", category.getCategoryId());
		map.addAttribute("categoryName", category.getName()); 
		map.addAttribute("currentPage",pageNoOffical);
		
		map.addAttribute("totalPage", page.getTotalPages()); 
		
		map.addAttribute("pageFirst", 1); 
		
		return "book_category"; 
	
			
	}
		
	@GetMapping("/search")
	public String searchDefault(@RequestParam String searchvalue, ModelMap map) {
		
		Optional<String> page = Optional.of("1"); 
		return search(searchvalue,map ,page); 
	}
		 
	
	
	@GetMapping("/search/{page}")
	public String search(@RequestParam String searchvalue,ModelMap map,@PathVariable("page") Optional<String> pageNo) {
		
		int pageSize = 2; 
		String sortDir = "desc"; 
		String sortField = "idBook"; 
		int pageOffical=0; 
		try {
			pageOffical = Integer.parseInt(pageNo.get());
		}catch(Exception ex) {
			return "redicrect:/home/view";
		}
		 
		searchvalue=searchvalue.trim();
		if(searchvalue.length()==0) return "redirect:/home/view";
	 
		Page<Book> page = bookservice.findBook(searchvalue,pageOffical,pageSize,sortField,sortDir); 
		List<Book> list = page.getContent();
		if(list.size()==0) return "notinfo"; 
		List<Category> listCategory = categorySerivce.getAllCategories(); 
		
		map.addAttribute("list", list); 
		map.addAttribute("categories", listCategory); 
		map.addAttribute("searchValue", searchvalue); 
		
		map.addAttribute("currentPage",pageOffical);
		map.addAttribute("totalPage", page.getTotalPages()); 
		map.addAttribute("pageFirst", 1); 
		return "search"; 
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}



















































