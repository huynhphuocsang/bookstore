package com.ptit.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/admin/statisticts")
public class StatistictsController {

	@GetMapping()
	public String getDashboard() {
		
		return "admin/statisticts";
	}
}
