package com.ptit.controller;

import java.lang.StackWalker.Option;
import java.math.BigDecimal;
import java.security.Principal;
import java.util.List;
import java.util.Optional;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.ptit.exception.OverMaximumQuantityException;
import com.ptit.exception.ResourceNotFoundException;
import com.ptit.model.Book;
import com.ptit.model.Cart;
import com.ptit.model.CartManager;
import com.ptit.model.District;
import com.ptit.model.Items;
import com.ptit.model.Province;
import com.ptit.model.User;
import com.ptit.model.Village;
import com.ptit.service.AddressService;
import com.ptit.service.BookService;
import com.ptit.service.DistrictService;
import com.ptit.service.ItemsService;
import com.ptit.service.OrderService;
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

	@Autowired
	AddressService addressService;

	@Autowired
	OrderService orderService;

	@PostMapping("/verify-old-password")
	@ResponseBody
	public String checkOldPassword(@RequestParam String password, Principal principal) {

		if (userService.verifyOldPassword(password, principal.getName()))
			return "true";

		return "false";
	}

	@PostMapping("/update-new-password")
	@ResponseBody
	public String updateNewPassword(@RequestParam String password, @RequestParam String verifyPassword,
			Principal principal) {

		if (password.length() == 0)
			return "1";
		if (password.equals(verifyPassword) == false)
			return "2";

		if (userService.updatePassword(password, principal.getName()))
			return "0";

		return "3";
	}

	@PostMapping("/add-to-cart")
	@ResponseBody
	public String addToCart(HttpSession session, @RequestParam long id, @RequestParam Optional<Integer> quantity,
			Principal principal) {
		Book book = null;
		try {
			book = bookService.getBookById(id);
		} catch (ResourceNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();

		}
		int quantityofBook = quantity.orElse(1);
		Cart cart = cartManager.getCart(session);
		int action = cart.addItem(book, quantityofBook); // 1: update 2:insert

		if (action == 1 && principal != null) {
			String username = principal.getName();
			User user = userService.getUserByUsername(username);
			itemserService.updateItemsCartIncreaseQuantity(user, book, quantityofBook);

		} else if (action == 2 && principal != null) {

			String username = principal.getName();
			User user = userService.getUserByUsername(username);
			itemserService.addNewItems(user, book, quantityofBook);

		}

		return "true";
	}

	@PostMapping("/update-cart")
	@ResponseBody
	public String updateCart(HttpSession session, @RequestParam long id, @RequestParam Optional<Integer> quantity,
			Principal principal) {
		Book book = null;
		try {
			book = bookService.getBookById(id);
		} catch (ResourceNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();

		}
		int quantityofBook = quantity.orElse(1);
		int maximumQuantity = book.getTotalQuantity();
		if (quantityofBook > maximumQuantity) {
			return "1"; // vượt quá số lượng hiện có
		}
		Cart cart = cartManager.getCart(session);
		cart.updateItem(book, quantityofBook);

		if (principal != null) {
			User user = userService.getUserByUsername(principal.getName());
			itemserService.updateItemsCartChangeQuantity(user, book, quantityofBook);
		}

		return cart.getTotal() + "";
	}

	@PostMapping("/remove-item")
	@ResponseBody
	public String removeItem(HttpSession session, @RequestParam long id, Principal principal) {
		Book book = null;
		try {
			book = bookService.getBookById(id);
		} catch (ResourceNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();

		}

		Cart cart = cartManager.getCart(session);
		cart.removeItem(book);
		if (principal != null) {
			User user = userService.getUserByUsername(principal.getName());
			itemserService.deleteItems(user, book);
		}
		return cart.getTotal() + "";
	}

	@PostMapping("/check-can-buy")
	public boolean canBuy(HttpSession session) {
		
		Cart cart = cartManager.getCart(session); 
		
		BigDecimal zeroValue = new BigDecimal(0); 
		if(cart.getTotal().compareTo(zeroValue)==0) return false; 
		return true; 
	}
	
	
	
	@PostMapping("/get-district")
	@ResponseBody
	public String findAllDistrictByProvince(@RequestParam String provinceId) {
		Province province = provinceService.getProvinceById(provinceId);
		List<District> listDistrict = districtService.getDistrictByProvince(province);
		String districtStr =""; 
		
		districtStr+="<select id=\"dropdown-district\" class=\"form-select\" aria-label=\"Default select example\" name=\"district\">\n"; 
		for (District d : listDistrict) {

			districtStr+="<option value=\"" + d.getDistrictId() + "\"> " + d.getDistrictName() + "</option> \n";
		}
		districtStr+="</select> "; 

		return districtStr;
	}

	@PostMapping("/get-village")
	@ResponseBody
	public String findAllVillageByDistrict(@RequestParam String districtId) {
		District district = districtService.getDistrictById(districtId);
		List<Village> listVillage = villageService.getVillageByDistrict(district);
		String villageStr = "";
		villageStr +="<select id=\"dropdown-village\" class=\"form-select\" aria-label=\"Default select example\" name=\"village\">\n";
		for (Village v : listVillage) {

			villageStr += "<option value=\"" + v.getVillageId() + "\"> " + v.getVillageName() + "</option> \n";
		}

		return villageStr;
	}

	@PostMapping("/order")
	public String order(@RequestParam String fullname, @RequestParam String phone, @RequestParam String village, @RequestParam String address, HttpSession session, Principal principal) {
		
		 
		//kiểm tra số lượng hàng tồn còn đủ hay không?
		
		Cart cart  = cartManager.getCart(session);
		List<Items> list = cart.getItems(); 
		User user=null; 
		if(principal!=null) {
			 user = userService.getUserByUsername(principal.getName()); 
		}
		boolean  result=false;
		try {
			result = orderService.createNewOrder(fullname, phone, user, list, cart.getTotal(), address, village);
		} catch (OverMaximumQuantityException e) {
			return e.getMessage(); 
		} 
		if(result) {
			
			cartManager.removeCart(session); 
			if(principal!=null) {
				itemserService.deleteItemsAfterBuy(user); 
			}
			
			return "true"; 
		}
		return "false";
	}
}



















