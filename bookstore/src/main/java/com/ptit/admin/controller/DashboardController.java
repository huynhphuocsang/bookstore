package com.ptit.admin.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import com.ptit.repository.OrderDao;
import com.ptit.service.UserService;

@Controller
@RequestMapping("admin")
public class DashboardController {
	
	@Autowired
	UserService userService;
	@Autowired
	OrderDao orderDao;
	
	@RequestMapping(value={"", "/","statisticts"})
	public String index(Model model) {
		model.addAttribute("NumberUser", userService.countUsers());
		
		model.addAttribute("price", orderDao.getPrice(2021));
		return "admin/statisticts";
	}
	
	@RequestMapping("/statisticts/{year}")
	public String index2(Model model, @PathVariable int year) {
		model.addAttribute("NumberUser", userService.countUsers());
		
		model.addAttribute("price", orderDao.getPrice(year));
		return "admin/statisticts";
	}
	
	
	// Added to test 500 page
    @RequestMapping(path = "/tigger-error", produces = MediaType.APPLICATION_JSON_VALUE)
    public void error500() throws Exception {
        throw new Exception();
    }
}
