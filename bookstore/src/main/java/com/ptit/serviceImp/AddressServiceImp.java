package com.ptit.serviceImp;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ptit.model.Address;
import com.ptit.model.Village;
import com.ptit.repository.AddressDao;
import com.ptit.repository.VillageDao;
import com.ptit.service.AddressService;

@Service
public class AddressServiceImp implements AddressService{
	
	@Autowired
	VillageDao villageDao; 
	
	@Autowired
	AddressDao addressDao; 
	@Override
	public Address createAddress(String addressName,String villageId) {
		Address address = new Address(); 
		Village village = villageDao.getById(villageId); 
		
		address.setAddressName(addressName);
		address.setVillage(village);
		return addressDao.save(address); 
		
	}

}

























