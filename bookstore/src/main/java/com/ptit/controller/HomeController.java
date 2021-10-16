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
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
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
			map.addAttribute("categories", categorySerivce.getAllCategories());
			map.addAttribute("list", listPage);
			
		}
		
		return "home"; 
	}
	
	
	
	@GetMapping("/category")
	public String bookByCategory(@RequestParam long categoryId,@RequestParam("page") Optional<Integer> page,ModelMap map ) {

		Category category=null;
		try {
			category = categorySerivce.getCategoryById(categoryId);
		} catch (ResourceNotFoundException e) {
			return "notfound"; 
		} 
		
		
		int quantityBooksOnPage = 2; 
		
		List<Book> list = bookservice.getBookByCategory(category); 
		if(list.size()==0) return "notfound"; 
		List<Book> listShow=new ArrayList<Book>(); 
		
		int lastPage = (int) Math.ceil((float)list.size()/quantityBooksOnPage);
		
		
		if(!page.isPresent()) {
			return "redirect:/home/category?categoryId="+categoryId+"&page=0"; 
		}
		else {
			if(page.get()<0) {
				return "redirect:/home/category?categoryId="+categoryId+"&page=0"; 
			}else if(page.get()>= lastPage) {
				
				return "redirect:/home/category?categoryId="+categoryId+"&page="+(lastPage-1); 
			}
			
			
			
		}
		
		
		int start = page.get()*quantityBooksOnPage;
		int end = start+quantityBooksOnPage;
		int pageCurrent = page.get(); 
		
		int j = 0; 
		if(end> list.size()) {
			end= list.size(); 
		}
			
			
		for(int i=start;i<end;i++) {
			listShow.add(null); 
			listShow.set(j++, list.get(i)); 
		}
		 
		map.addAttribute("categories", categorySerivce.getAllCategories());
		map.addAttribute("list", listShow);
		map.addAttribute("category", category.getCategoryId());
		map.addAttribute("last", lastPage-1); 
		map.addAttribute("pageCurrent", pageCurrent); 
		map.addAttribute("categoryName", category.getName()); 
		
		return "book_category"; 
	}
	
	
	@GetMapping("/search")
	public String search(@RequestParam String searchvalue,ModelMap map,@RequestParam("page") Optional<Integer> page,HttpServletRequest request) {
		
		searchvalue=searchvalue.trim();
		
		String queryTemp = request.getQueryString(); 
		if(!page.isPresent()) {
			return "redirect:/home/search?"+queryTemp+"&page=0"; 
		}
		
		String query[] = queryTemp.substring(0,queryTemp.length()-1).split("&"); 
		
		if(page.get()<0) {
			return "redirect:/home/search?"+query[0]+"&page=0";
		}
		int quantityOfBookOnPage = 2; 
		List<Book> list = bookservice.findBook(searchvalue); 
		if(list.isEmpty()) {
			return "notfound";
		}
		
		List<Book> listShow = new ArrayList<Book>();
		
		//call total page
		int lastPage = (int) Math.ceil((float)list.size()/quantityOfBookOnPage);
		
		if(page.get()>=lastPage) {
			return "redirect:/home/search?"+query[0]+"&page="+(lastPage-1);  
		}
		
		int start = quantityOfBookOnPage*page.get();
		int end = start + quantityOfBookOnPage; 
		if(end>list.size()) {
			end = list.size();
		}
		int j=0;
		for(int i=start;i<end;i++) {
			listShow.add(null); 
			listShow.set(j++, list.get(i)); 
		}
//		
		
		map.addAttribute("list", listShow); 
		map.addAttribute("query", query[0]); 
		map.addAttribute("pageCurrent", page.get());
		map.addAttribute("last", lastPage-1); 
		return "search"; 
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}



















































