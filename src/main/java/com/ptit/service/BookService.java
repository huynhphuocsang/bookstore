package com.ptit.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.ptit.exception.ResourceNotFoundException;
import com.ptit.model.Book;

public interface BookService{
	public Page<Book> getAllBooks(Pageable page); 
	public int getTotalQuantity(); 
	public Book getBookById(long id)  throws ResourceNotFoundException;
	
}
