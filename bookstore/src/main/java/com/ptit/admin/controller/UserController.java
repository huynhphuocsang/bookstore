package com.ptit.admin.controller;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.ptit.model.Book;
import com.ptit.model.User;
import com.ptit.repository.UserRoleDao;
import com.ptit.service.UserService;

@Controller
@RequestMapping("/admin/customer")
public class UserController {
	@Autowired
	UserService userService;
	
	@Autowired
    UserRoleDao userRoleDao;
	
	@GetMapping()
	public String getHomeCustomer(Model model) {
		model.addAttribute("user", new User());
		return getUser(model, 1, "username", "asc");
	}
	
	@GetMapping("/{pageNo}")
	public String getUser(Model model, @PathVariable(value = "pageNo") int pageNo,
			@RequestParam("sortField") String sortField, @RequestParam("sortDir") String sortDir) {
		
		int pageSize = 5;
		int pageFirst = 1;
		model.addAttribute("user", new User());
		
		Page<User> page = userService.findPaginated(pageNo, pageSize, sortField, sortDir);

		List<User> listUser = page.getContent()/*.stream().filter(u -> userRoleDao.getByUser(u).getRole().getIdRole()==2).collect(Collectors.toList())*/;
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
	
	@PostMapping("/save")
	public String updateUser(@ModelAttribute("user") User user, RedirectAttributes ra) {
		boolean isError=false;
		boolean existUsername=userService.checkExistUsernameInfo(user.getUsername());
		boolean existPhone=userService.checkExistUsernameInfo(user.getPhone());
		boolean existEmail=userService.checkExistUsernameInfo(user.getEmail());
		
		if(existUsername || existPhone || existEmail) {
			isError=true;
			ra.addFlashAttribute("isError", isError);
			ra.addFlashAttribute("existUsername", existUsername);
			ra.addFlashAttribute("existPhone", existPhone);
			ra.addFlashAttribute("existEmail", existEmail);
			ra.addFlashAttribute("user2", user);
			return "redirect:/admin/customer";
		}
		return "redirect:/admin/customer";
	}
	
}
