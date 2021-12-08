package com.ptit.admin.controller;

import java.io.IOException;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.ptit.exception.ResourceNotFoundException;
import com.ptit.model.Author;
import com.ptit.repository.AuthorDao;
import com.ptit.service.AuthorService;

public class AuthorController {
	
	@Autowired
	AuthorService authorService;
	
	@Autowired
	AuthorDao authorDao;
	
	
	@GetMapping("/author")
	public String getHomeauthor(Model model) {
		model.addAttribute("author", new Author());
		return getauthor(model, 1);
	}
	

	@GetMapping("/author/{pageNo}")
	public String getauthor(Model model, @PathVariable(value = "pageNo") int pageNo) {
		int pageSize = 2;
		int pageFirst = 1;
		model.addAttribute("author", new Author());
		Page<Author> page = authorService.findPaginated(pageNo, pageSize);

		List<Author> listauthor = page.getContent();
		model.addAttribute("listauthor", listauthor);

		model.addAttribute("pageFirst", pageFirst);
		model.addAttribute("currentPage", pageNo);
		model.addAttribute("totalPage", page.getTotalPages());
		model.addAttribute("totalItem", page.getTotalElements());

		return "/admin/author";
	}

	@PostMapping("/author/save")
	public String saveauthor(@ModelAttribute("author") Author author,
			RedirectAttributes ra)
			throws IOException, ResourceNotFoundException {
		Optional<Author> author2 = authorDao.findById(author.getIdAuthor());
		
		if(!author2.isPresent()) {
			boolean checkName = authorService.checkNameExitWhenInsert(author.getName());
			// nếu tồn tại
			if(checkName) {
				
			}else { // nếu không tồn tại
				authorService.save(author);
			}
		}else {
			boolean checkName = authorService.checkNameExitWhenUpdate(author.getName(), author.getIdAuthor());
			// nếu tồn tại
			if(checkName) {
				
			}else { // nếu không tồn tại
				authorService.save(author);
			}
		}
		
		
		
		
		return "redirect:/admin/author";
	}
	
	@PostMapping("/author/delete")
	public String deleteauthor(@RequestParam("id") Long id,RedirectAttributes ra) {
		
		try {
			if(authorService.checkExitAuthorInBook(authorService.getAuthorById(id))) {
				// nếu như tồn tại
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return "redirect:/admin/author";
	}
}
