package com.ptit.admin.controller;

import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.ptit.exception.ResourceNotFoundException;
import com.ptit.model.Address;
import com.ptit.model.Book;
import com.ptit.model.Category;
import com.ptit.model.User;
import com.ptit.repository.CategoryDao;
import com.ptit.service.CategoryService;

@Controller
@RequestMapping("/admin")
public class CategoryController {
	@Autowired
	CategoryService categoryService;
	
	@Autowired
	CategoryDao categoryDao;
	

	@GetMapping("/category")
	public String getHomeCategory(Model model) {
		model.addAttribute("category", new Category());
		return getCategory(model, 1);
	}
	

	@GetMapping("/category/{pageNo}")
	public String getCategory(Model model, @PathVariable(value = "pageNo") int pageNo) {
		int pageSize = 2;
		int pageFirst = 1;
		model.addAttribute("category", new Category());
		Page<Category> page = categoryService.findPaginated(pageNo, pageSize);

		List<Category> listCategory = page.getContent();
		model.addAttribute("listBook", listCategory);

		model.addAttribute("pageFirst", pageFirst);
		model.addAttribute("currentPage", pageNo);
		model.addAttribute("totalPage", page.getTotalPages());
		model.addAttribute("totalItem", page.getTotalElements());

		return "/admin/category";
	}

	@PostMapping("/category/save")
	public String saveCategory(@ModelAttribute("category") Category category, @RequestParam("oldValue") String oldValue,
			RedirectAttributes ra)
			throws IOException, ResourceNotFoundException {
		Optional<Category> category2 = categoryDao.findById(category.getCategoryId());
		
		
		
		
		
		
		return "redirect:/admin/category";
	}
	
	@PostMapping("/category/delete")
	public String deleteCategory(@RequestParam("id") Long idBook,RedirectAttributes ra) {
		
		
		return "redirect:/admin/category";
	}
}
