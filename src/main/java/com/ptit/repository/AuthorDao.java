package com.ptit.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ptit.model.Author;
@Repository
public interface AuthorDao extends JpaRepository<Author, Long>{

}
