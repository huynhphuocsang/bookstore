package com.ptit.admin.controller;

import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.ptit.exception.ResourceNotFoundException;
import com.ptit.model.Address;
import com.ptit.model.User;
import com.ptit.repository.AddressDao;
import com.ptit.repository.DistrictDao;
import com.ptit.repository.ProvinceDao;
import com.ptit.repository.VillageDao;
import com.ptit.service.AddressService;
import com.ptit.service.UserService;

@Controller
@RequestMapping("/admin/userAddress")
public class UserAddressController {
	@Autowired
	UserService userService;
	
	@Autowired
	VillageDao villageDao;
	
	@Autowired
	ProvinceDao provinceDao;
	
	@Autowired
	DistrictDao districtDao;
	
	@Autowired
	AddressDao addressDao;
	
	@Autowired
	AddressService addressService;
	
	@GetMapping()
	public String getUserAddress(Model model, @RequestParam(value = "id") long userId) throws ResourceNotFoundException {
		User user =userService.findById(userId);
		Set<Address> listAddress=user.getSetAddress();
		model.addAttribute("user", user);
		model.addAttribute("address", new Address());
		model.addAttribute("listAddress", listAddress);
		
		model.addAttribute("village", villageDao.findAll());
		model.addAttribute("district", districtDao.findAll());
		model.addAttribute("province", provinceDao.findAll());
		
		return "admin/userAddress";
	}
	
	@PostMapping("/back")
	public String turnBack() {
		return "redirect:/admin/customer/edit";
	}
	
	@PostMapping("/save")
	public String save(Model model, 
			@RequestParam(value = "userId") long userId,
			@ModelAttribute("address") Address address) throws ResourceNotFoundException {
		
		
		User user =userService.findById(userId);
		addressService.addOrUpdateAddress(address, user);
		Set<Address> listAddress=user.getSetAddress();
		model.addAttribute("user", user);
	
		model.addAttribute("listAddress", listAddress);
		model.addAttribute("village", villageDao.findAll());
		model.addAttribute("district", districtDao.findAll());
		model.addAttribute("province", provinceDao.findAll());
		
		return "admin/userAddress";
	}
}
