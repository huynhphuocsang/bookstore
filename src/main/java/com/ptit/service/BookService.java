package com.ptit.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.ptit.exception.ResourceNotFoundException;
import com.ptit.model.Book;
import com.ptit.model.Category;

public interface BookService{
	public Page<Book> getAllBooks(Pageable page); 
	public int getTotalQuantity(); 
	public Book getBookById(long id)  throws ResourceNotFoundException;
	public List<Book> getBookByCategory(Category category); 
	public List<Book> findBook(String key); 
	public Page<Book> findPage(int pageNo, int pageSize);
	public Page<Book> findPaginated(int pageNo, int pageSize, String sortField, String sortDirection);
	public int save(Book book);
	public void deleteById(long idBook);
}
