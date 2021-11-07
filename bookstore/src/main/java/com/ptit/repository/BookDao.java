package com.ptit.repository;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.ptit.model.Book;
import com.ptit.model.Category;

@Repository
public interface BookDao extends JpaRepository<Book, Long>,JpaSpecificationExecutor<Book>{
	public List<Book> findByCategory(Category category); 
	public Page<Book> findByBookNameContainsOrDescribeBookContainsAllIgnoreCaseOrderByBookNameAsc(String bookName,String describe, Pageable pageable); 
	public Page<Book> findByCategory(Category category, Pageable pageable); 
	@Query("SELECT  MAX(idBook)\r\n"
			+ "from Book")
	public int getLastIdBook();
	
	
	
}
