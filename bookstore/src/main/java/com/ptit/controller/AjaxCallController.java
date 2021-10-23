package com.ptit.controller;

import java.lang.StackWalker.Option;
import java.security.Principal;
import java.util.Optional;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.ptit.exception.ResourceNotFoundException;
import com.ptit.model.Book;
import com.ptit.model.Cart;
import com.ptit.model.CartManager;
import com.ptit.model.Items;
import com.ptit.service.BookService;
import com.ptit.service.UserService;

@RestController
public class AjaxCallController {
	@Autowired
	UserService userService; 
	
	@Autowired 
	BookService bookService; 
	
	
	@Autowired
	CartManager cartManager; 
	
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
	
	@PostMapping("/add-to-cart")
	@ResponseBody
	public String addToCart(HttpSession session, @RequestParam long id, @RequestParam Optional<Integer> quantity) {
		Book book  =null; 
		try {
			 book = bookService.getBookById(id);
		} catch (ResourceNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			
		} 
		int quantityofBook = quantity.orElse(1); 
		Cart cart = cartManager.getCart(session); 
		cart.addItem(book, quantityofBook);
		return "true"; 
	}
	
	@PostMapping("/update-cart")
	@ResponseBody
	public String updateCart(HttpSession session, @RequestParam long id, @RequestParam Optional<Integer> quantity) {
		Book book  =null; 
		try {
			 book = bookService.getBookById(id);
		} catch (ResourceNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			
		} 
		int quantityofBook = quantity.orElse(1); 
		int maximumQuantity = book.getTotalQuantity(); 
		if(quantityofBook>maximumQuantity) {
			return "1"; //vượt quá số lượng hiện có
		}
		Cart cart = cartManager.getCart(session); 
		cart.updateItem(book, quantityofBook); 
//		for(Items item: cart.getItems()) {
//			System.out.println(item.getBook().getBookName()+"-"+item.getQuantityOfBooks());
//		}
		return cart.getTotal()+""; 
	}
	
	
	@PostMapping("/remove-item")
	@ResponseBody
	public String removeItem(HttpSession session, @RequestParam long id, @RequestParam Optional<Integer> quantity) {
		Book book  =null; 
		try {
			 book = bookService.getBookById(id);
		} catch (ResourceNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			
		} 
		 
		Cart cart = cartManager.getCart(session); 
		cart.removeItem(book); 
		
		return "true"; 
	}
}




























