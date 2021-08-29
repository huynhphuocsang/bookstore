package com.ptit.serviceImp;


import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ptit.exception.ResourceNotFoundException;
import com.ptit.model.Book;
import com.ptit.repository.BookDao;
import com.ptit.service.BookService;


@Service
public class BookServiceImp implements BookService{
	@Autowired
	BookDao bookdao; 
	
	@Override
	public List<Book> getAllBooks() {
		return bookdao.findAll(); 
	}

	@Override
	public Book getBookById(long id) throws ResourceNotFoundException {
		Optional<Book> book = bookdao.findById(id);
		if(!book.isPresent()) {
			throw new ResourceNotFoundException("Book not found by id"); 
		}
		return book.get();
	}

}



















