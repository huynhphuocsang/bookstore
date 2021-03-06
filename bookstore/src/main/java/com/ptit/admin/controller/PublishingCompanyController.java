package com.ptit.admin.controller;

import java.io.IOException;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.security.access.prepost.PostAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.ptit.exception.ResourceNotFoundException;
import com.ptit.model.Author;
import com.ptit.model.Book;
import com.ptit.model.PublishingCompany;
import com.ptit.repository.PublishingCompanyDao;
import com.ptit.service.PublishingCompanyService;

@Controller
@RequestMapping("/admin")
public class PublishingCompanyController {
	
	
	@Autowired
	PublishingCompanyService publishingCompanyService;
	
	@Autowired
	PublishingCompanyDao publishingCompanyDao;
	
	@GetMapping("/company")
	public String getHomecompany(Model model) {
		model.addAttribute("company", new PublishingCompany());
		return getcompany(model, 1);
	}
	

	@GetMapping("/company/{pageNo}")
	public String getcompany(Model model, @PathVariable(value = "pageNo") int pageNo) {
		int pageSize = 6;
		int pageFirst = 1;
		model.addAttribute("company", new PublishingCompany());
		Page<PublishingCompany> page = publishingCompanyService.findPaginated(pageNo, pageSize);

		List<PublishingCompany> listCompany = page.getContent();
		model.addAttribute("listCompany", listCompany);

		model.addAttribute("pageFirst", pageFirst);
		model.addAttribute("currentPage", pageNo);
		model.addAttribute("totalPage", page.getTotalPages());
		model.addAttribute("totalItem", page.getTotalElements());

		return "/admin/company";
	}
	
	@GetMapping("/company/search")
	public String searchDefault(Model model,@RequestParam String searchvalue, ModelMap map) {
		model.addAttribute("book", new Book());
		return getCompanySearch(model, 1,searchvalue);
		
	}
	
	
	@GetMapping("/company/search/{pageNo}")
	public String getCompanySearch(Model model, @PathVariable(value = "pageNo") int pageNo,@RequestParam String searchvalue) {
		int pageSize = 6;
		int pageFirst = 1;
		model.addAttribute("company", new PublishingCompany());
		Page<PublishingCompany> page = publishingCompanyService.findCompany(searchvalue,pageNo, pageSize);

		List<PublishingCompany> listCompany = page.getContent();
		model.addAttribute("listCompany", listCompany);

		model.addAttribute("pageFirst", pageFirst);
		model.addAttribute("currentPage", pageNo);
		model.addAttribute("totalPage", page.getTotalPages());
		model.addAttribute("totalItem", page.getTotalElements());

		return "/admin/company";
	}

	@PostMapping("/company/save")
	public String savecompany(@ModelAttribute("publishingCompany") PublishingCompany publishingCompany,
			RedirectAttributes ra)
			throws IOException, ResourceNotFoundException {
		Optional<PublishingCompany> company2 = publishingCompanyDao.findById(publishingCompany.getIdCompany());
		
		if(!company2.isPresent()) {
			boolean checkName = publishingCompanyService.checkNameExitWhenInsert(publishingCompany.getName());
			// n???u t???n t???i
			if(checkName) {
				ra.addFlashAttribute("erorrMes", "Th??m th???t b???i, Nh?? xu???t b???n \""+publishingCompany.getName()+"\" ???? c?? trong h??? th???ng.");
			}else { // n???u kh??ng t???n t???i
				publishingCompanyService.save(publishingCompany);
				ra.addFlashAttribute("successMes", "Th??m nh?? xu???t b???n th??nh c??ng");
			}
		}else {
			boolean checkName = publishingCompanyService.checkNameExitWhenUpdate(publishingCompany.getName(), publishingCompany.getIdCompany());
			// n???u t???n t???i
			if(checkName) {
				ra.addFlashAttribute("erorrMes", "S???a th???t b???i, Nh?? xu???t b???n \""+publishingCompany.getName()+"\" ???? c?? trong h??? th???ng.");
			}else { // n???u kh??ng t???n t???i
				publishingCompanyService.save(publishingCompany);
				ra.addFlashAttribute("successMes", "S???a nh?? xu???t b???n th??nh c??ng");
			}
		}
		
		return "redirect:/admin/company";
	}
	
	@PostMapping("/company/delete")
	public String deletecompany(@RequestParam("id") Long id,RedirectAttributes ra) {
		
		try {
			if(publishingCompanyService.checkExitPublishingCompanyInBook(publishingCompanyService.getCompanyById(id))) {
				// n???u nh?? t???n t???i
				ra.addFlashAttribute("erorrMes", "X??a th???t b???i, ???? c?? s??ch thu???c Nh?? xu???t b???n n??y trong h??? th???ng.");
			}else {
				publishingCompanyService.deleteById(id);
				ra.addFlashAttribute("successMes", "Xo?? nh?? xu???t b???n th??nh c??ng");
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return "redirect:/admin/company";
	}
}
