package com.ptit.serviceImp;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ptit.model.Author;
import com.ptit.repository.AuthorDao;
import com.ptit.service.AuthorService;
@Service
public class AuthorServiceImp implements AuthorService{
	
	@Autowired
	AuthorDao authorDao;

	@Override
	public List<Author> getAllAuthor() {
		// TODO Auto-generated method stub
		return authorDao.findAll();
	}

	@Override
	public int getTotalAuthor() {
		// TODO Auto-generated method stub
		return authorDao.findAll().size();
	}

}
