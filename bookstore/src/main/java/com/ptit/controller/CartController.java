package com.ptit.controller;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.ptit.model.Cart;
import com.ptit.model.CartManager;
import com.ptit.model.Items;

@Controller
@RequestMapping("/cart")
public class CartController {
	
	@Autowired
	CartManager cartManager; 
	
	@GetMapping
	public String index(HttpSession session, Model model) {
		
		Cart cart = cartManager.getCart(session); 
		List<Items> list = cart.getItems(); 
		model.addAttribute("listItems", list);
		model.addAttribute("totalPrice", cart.getTotal()); 
		return "cart"; 
	}
}





















