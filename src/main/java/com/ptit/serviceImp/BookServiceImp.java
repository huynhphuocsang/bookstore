package com.ptit.serviceImp;


import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.ptit.exception.ResourceNotFoundException;
import com.ptit.model.Book;
import com.ptit.model.Category;
import com.ptit.repository.BookDao;
import com.ptit.service.BookService;


@Service
public class BookServiceImp implements BookService{
	@Autowired
	BookDao bookdao; 
	
	@Override
	public Page<Book> getAllBooks(Pageable page) {
		return bookdao.findAll(page);  
	}

	@Override
	public Book getBookById(long id) throws ResourceNotFoundException {
		Optional<Book> book = bookdao.findById(id);
		if(!book.isPresent()) {
			throw new ResourceNotFoundException("Book not found by id"); 
		}
		return book.get();
	}

	@Override
	public int getTotalQuantity() {
		return bookdao.findAll().size(); 
	}

	@Override
	public List<Book> getBookByCategory(Category category) {
		List<Book> listBooks = bookdao.findByCategory(category); 
		return listBooks; 
	}

	@Override
	public List<Book> findBook(String key) {
		List<Book> list = bookdao.findByBookNameContainsOrDescribeBookContainsAllIgnoreCaseOrderByBookNameAsc(key, key); 
		return list; 
	}

	@Override
	public Page<Book> findPage(int pageNo, int pageSize) {
		Pageable pageable = PageRequest.of(pageNo -1, pageSize);
		return bookdao.findAll(pageable);
	}

	@Override
	public Page<Book> findPaginated(int pageNo, int pageSize, String sortField, String sortDirection) {
		Sort sort = sortDirection.equalsIgnoreCase(Sort.Direction.ASC.name()) 
				? Sort.by(sortField).ascending() : Sort.by(sortField).descending() ;
		
		Pageable pageable = PageRequest.of(pageNo -1, pageSize,sort);
		return bookdao.findAll(pageable);
	}

}



















