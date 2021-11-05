package com.ptit.admin.controller;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import javax.persistence.CascadeType;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.ptit.exception.ResourceNotFoundException;
import com.ptit.model.Address;
import com.ptit.model.Book;
import com.ptit.model.Province;
import com.ptit.model.User;
import com.ptit.model.Village;
import com.ptit.repository.AddressDao;
import com.ptit.repository.DistrictDao;
import com.ptit.repository.ProvinceDao;
import com.ptit.repository.UserRoleDao;
import com.ptit.repository.VillageDao;
import com.ptit.service.UserService;
import com.ptit.service.VillageService;

@Controller
@RequestMapping("/admin/customer")
public class UserController {
	@Autowired
	UserService userService;
	
	@Autowired
    UserRoleDao userRoleDao;
	
	@Autowired
	VillageDao villageDao;
	
	@Autowired
	DistrictDao listVil;
	
	@Autowired
	ProvinceDao provinceDao;
	
	@Autowired
	DistrictDao districtDao;
	
	@Autowired
	VillageService villageService;
	
	@Autowired
	AddressDao addressDao;
	
	@GetMapping()
	public String getHomeCustomer(Model model) {
		model.addAttribute("user", new User());
		return getUser(model, 1, "username", "asc");
	}
	
	@GetMapping("/{pageNo}")
	public String getUser(Model model, @PathVariable(value = "pageNo") int pageNo,
			@RequestParam("sortField") String sortField, @RequestParam("sortDir") String sortDir) {
		
		int pageSize = 5;
		int pageFirst = 1;
		model.addAttribute("user", new User());
		model.addAttribute("address", new Address());
		Page<User> page = userService.findPaginated(pageNo, pageSize, sortField, sortDir);

		List<User> listUser = page.getContent()/*.stream().filter(u -> userRoleDao.getByUser(u).getRole().getIdRole()==2).collect(Collectors.toList())*/;
		model.addAttribute("listUser", listUser);
		model.addAttribute("sortField", sortField);
		model.addAttribute("sortDir", sortDir);
		model.addAttribute("reverseSortDir", sortDir.equals("asc") ? "desc" : "asc");

		model.addAttribute("pageFirst", pageFirst);
		model.addAttribute("currentPage", pageNo);
		model.addAttribute("totalPage", page.getTotalPages());
		model.addAttribute("totalItem", page.getTotalElements());
		
		model.addAttribute("village", villageDao.findAll());
		model.addAttribute("district", districtDao.findAll());
		model.addAttribute("province", provinceDao.findAll());
		return "admin/customers";
	}
	
	@PostMapping("/save")
	public String updateUser(@ModelAttribute("user") User user, 
			RedirectAttributes ra,
			@RequestParam(name="addressName") String addressName,
			@RequestParam(name="villageId") String villageId) {
		boolean isError=false;
		boolean existUsername=userService.checkExistUsernameInfo(user.getUsername());
		boolean existPhone=userService.checkExistUsernameInfo(user.getPhone());
		boolean existEmail=userService.checkExistUsernameInfo(user.getEmail());
		
		
		Address ad = new Address();
		ad.setAddressName(addressName);
		ad.setVillage(villageService.getById(villageId));
		user.getSetAddress().add(ad);
		userService.saveUser(user);
		
		
		if(existUsername || existPhone || existEmail) {
			isError=true;
			ra.addFlashAttribute("isError", isError);
			ra.addFlashAttribute("existUsername", existUsername);
			ra.addFlashAttribute("existPhone", existPhone);
			ra.addFlashAttribute("existEmail", existEmail);
			ra.addFlashAttribute("user2", user);
			return "redirect:/admin/customer";
		}
		return "redirect:/admin/customer";
	}
	
	@PostMapping("/edit")
	public String edit(Model model,
			RedirectAttributes ra,
			@RequestParam(name="id") long id) throws ResourceNotFoundException {
		
		User userEdit = userService.findById(id);
		List<Address> list = addressDao.findBySetUsers_UserId(id);
		ra.addFlashAttribute("user3", userEdit);
		ra.addFlashAttribute("address2", list);
		boolean edit=true;
		ra.addFlashAttribute("idEdit", edit);
		return "redirect:/admin/customer";
	}
	
}
