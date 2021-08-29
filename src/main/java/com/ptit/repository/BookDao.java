package com.ptit.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ptit.model.Book;

@Repository
public interface BookDao extends JpaRepository<Book, Long>{

}
