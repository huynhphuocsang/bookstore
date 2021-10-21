package com.ptit.controller;

import java.security.Principal;
import java.time.LocalDate;

import javax.swing.event.TableColumnModelListener;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.ptit.service.UserService;
import com.ptit.util.WebUtils;

@Controller
@RequestMapping("/account")
public class AccountController {
	@Autowired
	UserService userService; 
	
	@GetMapping("/login")
	public String login() {
		return "login"; 
	}

	@GetMapping("/signup")
	public String signUpDefault() {
		return "signup"; 
	}
	
	
	@PostMapping("/signup")
	public String signUp(ModelMap map, @RequestParam String username, @RequestParam String phone, @RequestParam String password, @RequestParam String verifyPassword) {
		
		boolean checkPhone = userService.checkExistPhoneInfo(phone);
		boolean checkUsername = userService.checkExistUsernameInfo(username); 
		boolean checkPhoneLength = false; 
		boolean verifyPasswordFailed = false; 
		boolean emptyPassword = false; 
		int minLengthPass = 5; 
		
		if(phone.length()!=10) checkPhoneLength = true; 
		if(password.equals(verifyPassword)==false) verifyPasswordFailed = true; 
		if(password.length()<minLengthPass) {
			emptyPassword = true;  
			
		}
		
		if(checkPhone==true || checkUsername==true || checkPhoneLength==true || verifyPasswordFailed==true ||emptyPassword==true) {
			map.addAttribute("checkPhone", checkPhone); 
			map.addAttribute("checkUsername", checkUsername); 
			map.addAttribute("phoneLength", checkPhoneLength);
			map.addAttribute("failedPassword", verifyPasswordFailed); 
			map.addAttribute("emptyPassword", emptyPassword); 
			map.addAttribute("messagePass", "Mật khẩu phải có tối thiểu "+minLengthPass+" ký tự!"); 
			
			map.addAttribute("username", username);
			map.addAttribute("phone", phone); 
			return "signup"; 
		}
		else {
			boolean checkAdd = userService.addAccount(username, password, phone); 
			
		}
		return "redirect:/account/login";
	}
	
	@RequestMapping(value = "/userInfo", method = RequestMethod.GET)
    public String userInfo(Model model, Principal principal) {
		
		
		if(principal!=null) {
			 // Sau khi user login thanh cong se co principal
	        String userName = principal.getName();

	        

	        User loginedUser = (User) ((Authentication) principal).getPrincipal();

	        String userInfo = WebUtils.toString(loginedUser);
	        
	        model.addAttribute("userInfo", userInfo);
	        model.addAttribute("username", userName); 
	        
	        com.ptit.model.User user = userService.getUserByUsername(userName); 
	        model.addAttribute("user", user); 
	        
	        LocalDate date = LocalDate.now(); 
	        int currentYear = date.getYear(); 
	        int minimumYear = currentYear - 80;  
	        model.addAttribute("minimumYear", minimumYear); 
	        model.addAttribute("year", currentYear-3); 
	        
	        
	        return "userInfo";
		}else {
			return "redirect:/account/login"; 
		}
       
    }
	
	
	
	@GetMapping(value = "/logout-success")
    public String logoutSuccess(ModelMap model) {

        
        model.addAttribute("logoutSuccess", true);

        return "login";
    }
	
	
	
	 @GetMapping(value = "/check-login")
	 public String accessDenied(Model model, Principal principal) {

       if (principal != null) {
           return "redirect:/account/userInfo"; 

       }else {
       	return "redirect:/account/login"; 
       }
       
	 }
	 
//	 @PostMapping("/update")
//	 public String updateInfo(@RequestBody User user) {
//		 System.out.println("gia tri cua user: "+user.toString());
//		 return "userInfo"; 
//	 }
	 
	 @PostMapping("/update")
	 public String updateInfo(@RequestParam String email, @RequestParam String phone, @RequestParam boolean gender, @RequestParam int age, Principal principal, Model model) {
		 
		 
		 
		 
		 
		 if(principal!=null) {
			 
			 boolean checkPhone = userService.checkExistPhoneInfo(phone, principal.getName()); 
			 boolean checkEmail = userService.checkExistEmailInfo(email, principal.getName()); 
			 boolean checkPhoneLength = false; 
			 if(phone.length()!=10) checkPhoneLength = true; 
			 
			 com.ptit.model.User user = userService.getUserByUsername(principal.getName()); 
		        user.setEmail(email); 
		        user.setPhone(phone);
		        user.setAge(age); 
		        model.addAttribute("user", user);
		        
		        
		        LocalDate date = LocalDate.now(); 
		        int currentYear = date.getYear(); 
		        int minimumYear = currentYear - 80;  
		        model.addAttribute("minimumYear", minimumYear); 
		        model.addAttribute("year", currentYear-3); 
		        
		        
			 if(checkPhone==true || checkEmail==true || checkPhoneLength==true) {
				
				 
			        model.addAttribute("checkPhone", checkPhone); 
					model.addAttribute("checkEmail", checkEmail); 
					model.addAttribute("checkPhoneLength", checkPhoneLength);
					return "userInfo"; 
			 }else {
				
				 boolean checkUpdate = userService.updateUserInfo(principal.getName(), email, phone, gender, age); 
				 return "redirect:/account/userInfo?updateSuccess=true"; 
				 
			 }
			 
			 
			
		 }else {
			 return "redirect:/account/login"; 
		 }
		 
		 
		 
		
	 }
	 
	 @GetMapping("/update-password")
	 public String updatePassword(Principal principal) {
		 if(principal==null) return "redirect:/account/login"; 
		 return "updatePassword"; 
	 }
	 
	 
	 
}
	
	
	
	
	
	
	





//	 @RequestMapping(value = "/403", method = RequestMethod.GET)
//	    public String accessDenied(Model model, Principal principal) {
//
//	        if (principal != null) {
//	            User loginedUser = (User) ((Authentication) principal).getPrincipal();
//
//	            String userInfo = WebUtils.toString(loginedUser);
//
//	            model.addAttribute("userInfo", userInfo);
//
//	            String message = "Hi " + principal.getName() //
//	                    + "<br> You do not have permission to access this page!";
//	            model.addAttribute("message", message);
//
//	        }
//
//	        return "403Page";
//	    }
	
//  @RequestMapping(value = "/admin", method = RequestMethod.GET)
//  public String adminPage(Model model, Principal principal) {
//
//      User loginedUser = (User) ((Authentication) principal).getPrincipal();
//
//      String userInfo = WebUtils.toString(loginedUser);
//      model.addAttribute("userInfo", userInfo);
//
//      return "adminPage";
//  }

