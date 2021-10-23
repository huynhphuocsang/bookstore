package com.ptit.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.ptit.model.Author;
import com.ptit.model.Book;
@Repository
public interface AuthorDao extends JpaRepository<Author, Long>{
	public Optional<Author> findByNameAllIgnoreCase(String name); 
	
	@Query("SELECT  MAX(idAuthor) from Author")
	public int getLastIdAuthor();
	
	public Author findAuthorByIdAuthor(long idAuthor); 
}
