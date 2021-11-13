package com.ptit.admin.controller;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.ptit.exception.ResourceNotFoundException;
import com.ptit.model.Book;
import com.ptit.model.Category;
import com.ptit.model.Review;
import com.ptit.model.ReviewId;
import com.ptit.repository.ReviewDao;
import com.ptit.service.BookService;
import com.ptit.service.ReviewService;

@Controller
@RequestMapping("/admin/review")
public class ReviewController {
	@Autowired
	BookService bookService;
	
	@Autowired
	ReviewService reviewService;
	
	@Autowired
	ReviewDao reviewDao;
	
	@GetMapping()
	public String getHomeReviews(Model model) {
		model.addAttribute("book", new Book());
		return getReviews(model, 1, "bookName", "asc");
	}
	
	@GetMapping("/{pageNo}")
	public String getReviews(Model model, @PathVariable(value = "pageNo") int pageNo,
			@RequestParam("sortField") String sortField, @RequestParam("sortDir") String sortDir) {
		int pageSize = 4;
		int pageFirst = 1;
		model.addAttribute("book", new Book());
		Page<Book> page = bookService.findPaginated(pageNo, pageSize, sortField, sortDir);

		List<Book> listBook = page.getContent();
		
		List<List<int[]>> listBookStar = new ArrayList<List<int[]>>();
		
		List<int[]> emptyStar=new ArrayList<int[]>();
		emptyStar.add(new int[] {0,0});
		
		for(Book ele: listBook) {
			if(!reviewDao.getListStar(ele.getIdBook()).isEmpty()) {
				listBookStar.add(reviewDao.getListStar(ele.getIdBook()));
			}
			else {
				listBookStar.add(emptyStar);
			}
		}
		listBookStar.forEach((ele)->{
			System.out.println(ele.get(0)[0]+"--"+ele.get(0)[1]);
		});
		
//		 listBookStar.add(reviewDao.getListStar(1));
		
		model.addAttribute("listBook", listBook);
		model.addAttribute("listBookStar", listBookStar);
		
		model.addAttribute("sortField", sortField);
		model.addAttribute("sortDir", sortDir);
		model.addAttribute("reverseSortDir", sortDir.equals("asc") ? "desc" : "asc");
		
		model.addAttribute("pageFirst", pageFirst);
		model.addAttribute("currentPage", pageNo);
		model.addAttribute("totalPage", page.getTotalPages());
		model.addAttribute("totalItem", page.getTotalElements());
		return "/admin/review";
	}
	
	
	//=============BOOK REVIEW==================================
	@GetMapping("/bookReviews/{idBook}")
	public String getBookReview(Model model, @PathVariable("idBook") long idBook,
								@RequestParam(value = "leng",defaultValue = "2") int leng) throws ResourceNotFoundException {
		Book book=bookService.getBookById(idBook);
		List<Review> listReview=reviewService.findListByLeng(idBook, leng);
		int totalReview=reviewService.getAllReviewViaBook(book).size();
		
		model.addAttribute("leng", leng);
		model.addAttribute("book", book);
		model.addAttribute("listReview", listReview);
		model.addAttribute("totalReview", totalReview);
		return "admin/bookReviews";
	}
	
	@PostMapping("/bookReviews/delete")
	public String deleteReview(@RequestParam("idBook") long idBook,@RequestParam(value = "leng",defaultValue = "0") int leng,
								@RequestParam("idUser") long idUser) throws ResourceNotFoundException {
		ReviewId reviewId=new ReviewId(idUser,idBook);
		reviewService.deleteById(reviewId);
		return "redirect:/admin/review/bookReviews/"+idBook+"?leng="+ leng;
	}
}
