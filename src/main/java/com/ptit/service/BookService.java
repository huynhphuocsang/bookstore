package com.ptit.service;

import java.util.ArrayList;
import java.util.List;

import com.ptit.exception.ResourceNotFoundException;
import com.ptit.model.Book;

public interface BookService{
	public List<Book> getAllBooks(); 
	public Book getBookById(long id)  throws ResourceNotFoundException; 
}
