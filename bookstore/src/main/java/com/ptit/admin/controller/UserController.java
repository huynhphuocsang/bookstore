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
import com.ptit.model.User;
import com.ptit.service.UserService;

@Controller
@RequestMapping("/admin/customer")
public class UserController {
	@Autowired
	UserService userService;
	
	@GetMapping()
	public String getDashboard(Model model) {
		model.addAttribute("user", new User());
		return getUser(model, 1, "username", "asc");
	}
	
	@GetMapping("/{pageNo}")
	public String getUser(Model model, @PathVariable(value = "pageNo") int pageNo,
			@RequestParam("sortField") String sortField, @RequestParam("sortDir") String sortDir) {
		
		int pageSize = 5;
		int pageFirst = 1;
		model.addAttribute("book", new Book());
		Page<User> page = userService.findPaginated(pageNo, pageSize, sortField, sortDir);

		List<User> listUser = page.getContent();
		model.addAttribute("listUser", listUser);
		model.addAttribute("sortField", sortField);
		model.addAttribute("sortDir", sortDir);
		model.addAttribute("reverseSortDir", sortDir.equals("asc") ? "desc" : "asc");

		model.addAttribute("pageFirst", pageFirst);
		model.addAttribute("currentPage", pageNo);
		model.addAttribute("totalPage", page.getTotalPages());
		model.addAttribute("totalItem", page.getTotalElements());
		
		return "admin/customers";
	}
}
