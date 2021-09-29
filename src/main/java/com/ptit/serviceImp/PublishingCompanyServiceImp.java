package com.ptit.serviceImp;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ptit.model.PublishingCompany;
import com.ptit.repository.PublishingCompanyDao;
import com.ptit.service.PublishingCompanyService;
@Service
public class PublishingCompanyServiceImp implements PublishingCompanyService{

	@Autowired
	PublishingCompanyDao publishingCompanyDao;
	
	@Override
	public List<PublishingCompany> getAllCompanyService() {
		// TODO Auto-generated method stub
		return publishingCompanyDao.findAll();
	}

	@Override
	public int getTotalCompanyService() {
		// TODO Auto-generated method stub
		return publishingCompanyDao.findAll().size();
	}

}
