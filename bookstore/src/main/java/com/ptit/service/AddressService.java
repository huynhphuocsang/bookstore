package com.ptit.service;

import com.ptit.model.Address;
import com.ptit.model.User;

public interface AddressService {
	public Address createAddress(String addressName, String villageId); 
	public void addOrUpdateAddress();
	void addOrUpdateAddress(Address address, User user);
}
