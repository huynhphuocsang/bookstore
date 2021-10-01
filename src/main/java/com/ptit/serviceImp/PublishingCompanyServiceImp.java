package com.ptit.serviceImp;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ptit.exception.ResourceNotFoundException;
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

	@Override
	public long getCompanyIdByName(String name) throws ResourceNotFoundException{
		Optional<PublishingCompany> company=publishingCompanyDao.findByNameAllIgnoreCase(name);
		if(!company.isPresent()) {
			throw new ResourceNotFoundException("Author doesn't exists"); 
		}
		return company.get().getIdCompany();
	}
	
	@Override
	public int save(PublishingCompany company) {
		publishingCompanyDao.save(company);
		return publishingCompanyDao.getLastIdCompany();
	}
	
	@Override
	public PublishingCompany selectOrUpdateCompany(String name) {
		long id = 0;
		try {
			id=this.getCompanyIdByName(name);
			return publishingCompanyDao.getById(id);
		} catch (ResourceNotFoundException e) {
			PublishingCompany newComany=new PublishingCompany();
			newComany.setName(name);
			id=this.save(newComany);
			return publishingCompanyDao.getById(id);
		}
	}
}
