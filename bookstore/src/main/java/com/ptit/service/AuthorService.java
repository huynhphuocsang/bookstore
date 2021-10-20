package com.ptit.service;

import java.util.List;

import com.ptit.exception.ResourceNotFoundException;
import com.ptit.model.Author;

public interface AuthorService {
	public List<Author> getAllAuthor();
	public int getTotalAuthor();
	public Author getAuthorByName(String name) throws ResourceNotFoundException;
	public long getAuthorIdByName(String name) throws ResourceNotFoundException;
	public int save(Author author);
	public Author selectOrUpdateAuthor(String name);
}
