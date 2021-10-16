package com.ptit.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.ptit.model.Book;
import com.ptit.model.Category;

@Repository
public interface BookDao extends JpaRepository<Book, Long>{
	public List<Book> findByCategory(Category category); 
	public List<Book> findByBookNameContainsOrDescribeBookContainsAllIgnoreCaseOrderByBookNameAsc(String bookName,String describe); 
	
	@Query("SELECT  MAX(idBook)\r\n"
			+ "from Book")
	public int getLastIdBook();
}
