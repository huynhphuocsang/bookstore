package com.ptit.service;

import java.util.List;

import com.ptit.exception.ResourceNotFoundException;
import com.ptit.model.PublishingCompany;

public interface PublishingCompanyService {
	public List<PublishingCompany> getAllCompanyService();
	public int getTotalCompanyService();
	public long getCompanyIdByName(String name) throws ResourceNotFoundException;;
	public int save(PublishingCompany company);
	public PublishingCompany selectOrUpdateCompany(String name);
}
