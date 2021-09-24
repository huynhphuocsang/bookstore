package com.ptit.admin.controller;

import java.util.List;
import java.util.Optional;

import javax.websocket.server.PathParam;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.ptit.model.Book;
import com.ptit.model.Category;
import com.ptit.service.BookService;
import com.ptit.service.CategoryService;

@Controller
@RequestMapping("/admin")
public class BookController {
	@Autowired
	BookService bookService;
	
	@Autowired
	CategoryService categoryService;
	
	@GetMapping("/book")
	public String getHomeBook(Model model) {	
		model.addAttribute("book", new Book());
		return getBook(model, 1 , "bookName", "asc");
	}
	
	
	@GetMapping("/book/{pageNo}")
	public String getBook(Model model,
			@PathVariable (value = "pageNo") int pageNo,
			@RequestParam("sortField") String sortField,
			@RequestParam("sortDir") String sortDir) {
		int pageSize = 5;
		int pageFirst = 1;
		
		Page<Book> page = bookService.findPaginated(pageNo, pageSize,sortField,sortDir);
		
		
		List<Book> listBook = page.getContent();
		model.addAttribute("listBook", listBook);
		model.addAttribute("sortField", sortField);
		model.addAttribute("sortDir", sortDir);
		model.addAttribute("reverseSortDir", sortDir.equals("asc") ? "desc" : "asc");
		
		model.addAttribute("pageFirst", pageFirst);
		model.addAttribute("currentPage", pageNo);
		model.addAttribute("totalPage", page.getTotalPages());
		model.addAttribute("totalItem", page.getTotalElements());
		
		//Get category to view
		List<Category> listCategory= categoryService.getAllCategories();
		model.addAttribute("listCategory", listCategory);
		
		
		return "/admin/book";
	}
	
	
	@PostMapping("/book/save")
	public String saveBook(
			@ModelAttribute("book") Book book,
			@RequestParam("fileImage") MultipartFile multipartFile
			) {
		String fileName = StringUtils.cleanPath(multipartFile.getOriginalFilename());
		book.setPicture(fileName);
		
		String uploadDir = "/image/" + save
		
		
		bookService.save(book);
		
		return "/admin/book";
	}
	
	
	
}
