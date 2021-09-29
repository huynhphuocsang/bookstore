package com.ptit.admin.controller;

import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;

import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.ptit.model.Book;
import com.ptit.model.Category;
import com.ptit.service.AuthorService;
import com.ptit.service.BookService;
import com.ptit.service.CategoryService;
import com.ptit.service.PublishingCompanyService;

@Controller
@RequestMapping("/admin")
public class BookController {

	@Autowired
	PublishingCompanyService publishingCompanyService;

	@Autowired
	AuthorService authorService;

	@Autowired
	BookService bookService;

	@Autowired
	CategoryService categoryService;

	@GetMapping("/book")
	public String getHomeBook(Model model) {
		model.addAttribute("book", new Book());
		return getBook(model, 1, "bookName", "asc");
	}

	@GetMapping("/book/{pageNo}")
	public String getBook(Model model, @PathVariable(value = "pageNo") int pageNo,
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

		// get Publishing Company
		model.addAttribute("listCompany", publishingCompanyService.getAllCompanyService());

		// Get category to view
		List<Category> listCategory = categoryService.getAllCategories();
		model.addAttribute("listCategory", listCategory);

		// get author
		model.addAttribute("listAuthor", authorService.getAllAuthor());

		return "/admin/book";
	}

	@PostMapping("/book/save")
	public String saveBook(@ModelAttribute("book") Book book, 
			@RequestParam("fileImage") MultipartFile fileImage)
			throws IOException {
		String fileName = StringUtils.cleanPath(fileImage.getOriginalFilename());
		book.setPicture(fileName);
		
		String uploadDir = "./src/main/resources/static/image/";

		Path uploadPath = Paths.get(uploadDir);

		if (!Files.exists(uploadPath)) {
			Files.createDirectories(uploadPath);
		}

		try (InputStream inputStream = fileImage.getInputStream()) {
			Path filePath = uploadPath.resolve(fileName);
			System.out.println(filePath.toFile().getAbsolutePath());

			Files.copy(inputStream, filePath, StandardCopyOption.REPLACE_EXISTING);
		} catch (Exception e) {
			e.printStackTrace();
		}
		bookService.save(book);
		return "redirect:/admin/book";
	}
	
	@PostMapping("/book/delete")
	public String deleteBook(@RequestParam("id") Long idBook) {
		
		bookService.deleteById(idBook);
		return "redirect:/admin/book";
	}
	
	

}
