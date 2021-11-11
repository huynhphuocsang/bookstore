package com.ptit.serviceImp;

import java.util.Calendar;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ptit.model.Book;
import com.ptit.model.Review;
import com.ptit.model.ReviewId;
import com.ptit.model.User;
import com.ptit.repository.ReviewDao;
import com.ptit.service.ReviewService;

@Service
public class ReviewServiceImp implements ReviewService{
	
	@Autowired
	ReviewDao reviewDao; 
	
	@Override
	public List<Review> getAllReviewViaBook(Book book) {
		return reviewDao.findByIdIdBook(book.getIdBook()); 
	}

	@Override
	public void addReview(User user, Book book, float star, String comments) {
		java.sql.Date date = new java.sql.Date(Calendar.getInstance().getTime().getTime());
		Review review = new Review(); 
		ReviewId id = new ReviewId(); 
		id.setIdBook(book.getIdBook());
		id.setUserId(user.getUserId());
		
		review.setId(id);
		review.setUser(user);
		review.setBook(book);
		review.setComments(comments);
		review.setStar(star);
		review.setTime(date);
		
		reviewDao.save(review); 
		
	}

	@Override
	public boolean checkReviewed(User user, Book book) {
		Review review = reviewDao.findByIdIdBookAndIdUserId(book.getIdBook(), user.getUserId()); 
		if(review!=null) return true; 
		return false; 
	}

}
