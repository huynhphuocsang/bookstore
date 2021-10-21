package com.ptit.controller;

import java.security.Principal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.ptit.service.UserService;

@RestController
public class AjaxCallController {
	@Autowired
	UserService userService; 
	
	
	
	@PostMapping("/verify-old-password")
	 @ResponseBody
	 public String checkOldPassword(@RequestParam String password, Principal principal) {
		
		if(userService.verifyOldPassword(password, principal.getName())) return "true"; 
		
		 return "false"; 
	 }
	
	
	@PostMapping("/update-new-password")
	 @ResponseBody
	 public String updateNewPassword(@RequestParam String password, @RequestParam String verifyPassword, Principal principal) {
		
		if(password.length()==0) return "1"; 
		if(password.equals(verifyPassword)==false) return "2"; 
		
		if(userService.updatePassword(password, principal.getName())) return "0"; 
		
		 return "3"; 
	 }

}
