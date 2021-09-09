package com.ptit.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.ptit.repository.BookDao;

@Controller
@RequestMapping("/admin/book")
public class BookManagerController {
	@Autowired
	BookDao bookDao;
	
	@GetMapping()
	public String getBook(ModelMap map) {
		map.addAttribute("listBook", bookDao.findAll());
		return "admin/book";
	}
}