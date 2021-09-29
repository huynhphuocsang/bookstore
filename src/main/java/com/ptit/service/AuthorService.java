package com.ptit.service;

import java.util.List;

import com.ptit.model.Author;

public interface AuthorService {
	public List<Author> getAllAuthor();
	public int getTotalAuthor();
}
