package com.ptit.admin.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/admin/customer")
public class UserController {
	@GetMapping()
	public String getDashboard() {
		
		return "admin/customers";
	}
}
