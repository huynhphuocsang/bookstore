package com.ptit.controller;

import java.lang.StackWalker.Option;
import java.security.Principal;
import java.util.List;
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
import com.ptit.model.District;
import com.ptit.model.Items;
import com.ptit.model.Province;
import com.ptit.model.User;
import com.ptit.model.Village;
import com.ptit.service.BookService;
import com.ptit.service.DistrictService;
import com.ptit.service.ItemsService;
import com.ptit.service.ProvinceService;
import com.ptit.service.UserService;
import com.ptit.service.VillageService;
import com.ptit.serviceImp.ItemsServiceImp;

@RestController
public class AjaxCallController {
	@Autowired
	UserService userService; 
	
	@Autowired 
	BookService bookService; 
	
	
	@Autowired
	CartManager cartManager; 
	
	
	@Autowired
	ItemsService itemserService; 
	 
	
	@Autowired
	ProvinceService provinceService; 
	
	@Autowired
	DistrictService districtService; 
	
	@Autowired
	VillageService villageService; 
	
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
	public String addToCart(HttpSession session, @RequestParam long id, @RequestParam Optional<Integer> quantity, Principal principal) {
		Book book  =null; 
		try {
			 book = bookService.getBookById(id);
		} catch (ResourceNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			
		} 
		int quantityofBook = quantity.orElse(1); 
		Cart cart = cartManager.getCart(session); 
		int action = cart.addItem(book, quantityofBook); //1: update 2:insert
		
		if(action==1 && principal!=null) {
			String username = principal.getName(); 
			User user = userService.getUserByUsername(username); 
			itemserService.updateItemsCartIncreaseQuantity(user, book, quantityofBook); 
			
		}else if(action==2 && principal!=null) {

			
				String username = principal.getName(); 
				User user = userService.getUserByUsername(username); 
				itemserService.addNewItems(user, book, quantityofBook); 
		
			
		}
		
		
		return "true"; 
	}
	
	@PostMapping("/update-cart")
	@ResponseBody
	public String updateCart(HttpSession session, @RequestParam long id, @RequestParam Optional<Integer> quantity, Principal principal) {
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
		
		if(principal!=null) {
			User user = userService.getUserByUsername(principal.getName());
			itemserService.updateItemsCartChangeQuantity(user, book, quantityofBook); 
		}
		
		return cart.getTotal()+""; 
	}
	
	
	@PostMapping("/remove-item")
	@ResponseBody
	public String removeItem(HttpSession session, @RequestParam long id, Principal principal) {
		Book book  =null; 
		try {
			 book = bookService.getBookById(id);
		} catch (ResourceNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			
		} 
		 
		Cart cart = cartManager.getCart(session); 
		cart.removeItem(book); 
		if(principal!=null) {
			User user = userService.getUserByUsername(principal.getName()); 
			itemserService.deleteItems(user, book); 
		}
		return cart.getTotal()+""; 
	}
	
	
	@PostMapping("/get-district")
	@ResponseBody
	public String findAllDistrictByProvince(@RequestParam String provinceId) {
		Province province = provinceService.getProvinceById(provinceId); 
		List<District> listDistrict = districtService.getDistrictByProvince(province);
		String districtStr = ""; 
		for(District d: listDistrict) {
//			System.out.println(d.getDistrictName());
			districtStr += "<option value=\""+d.getDistrictId()+"\"> "+d.getDistrictName()+"</option> \n"; 
		}
		
		return districtStr;
	}
	
	
	
	@PostMapping("/get-village")
	@ResponseBody
	public String findAllVillageByDistrict(@RequestParam String districtId) {
		District district = districtService.getDistrictById(districtId); 
		List<Village> listVillage = villageService.getVillageByDistrict(district); 
		String villageStr = ""; 
		for(Village v: listVillage) {
//			System.out.println(d.getDistrictName());
			villageStr += "<option value=\""+v.getVillageId()+"\"> "+v.getVillageName()+"</option> \n"; 
		}
		
		return villageStr;
	}
}




























