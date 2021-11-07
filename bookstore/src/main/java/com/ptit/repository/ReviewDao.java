package com.ptit.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.ptit.model.Review;
import com.ptit.model.ReviewId;

@Repository
public interface ReviewDao extends JpaRepository<Review, ReviewId>{
	public List<Review> findByIdIdBook(long idBook); 
	public Review findByIdIdBookAndIdUserId(long idBook, long userId); 
	
	
	
}
