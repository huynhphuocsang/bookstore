package com.ptit.admin.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.ptit.model.Book;
import com.ptit.model.Category;
import com.ptit.service.BookService;

@Controller
@RequestMapping("/admin/review")
public class ReviewController {
	@Autowired
	BookService bookService;
	
	@GetMapping()
	public String getHomeReviews(Model model) {
		model.addAttribute("book", new Book());
		return getReviews(model, 1, "bookName", "asc");
	}
	
	@GetMapping("/{pageNo}")
	public String getReviews(Model model, @PathVariable(value = "pageNo") int pageNo,
			@RequestParam("sortField") String sortField, @RequestParam("sortDir") String sortDir) {
		int pageSize = 5;
		int pageFirst = 1;
		model.addAttribute("book", new Book());
		Page<Book> page = bookService.findPaginated(pageNo, pageSize, sortField, sortDir);

		List<Book> listBook = page.getContent();
		model.addAttribute("listBook", listBook);
		model.addAttribute("sortField", sortField);
		model.addAttribute("sortDir", sortDir);
		model.addAttribute("reverseSortDir", sortDir.equals("asc") ? "desc" : "asc");

		model.addAttribute("pageFirst", pageFirst);
		model.addAttribute("currentPage", pageNo);
		model.addAttribute("totalPage", page.getTotalPages());
		model.addAttribute("totalItem", page.getTotalElements());
		return "/admin/review";
	}
}
