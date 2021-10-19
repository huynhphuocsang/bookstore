package com.ptit.service;

public interface UserService {
	public Long countUsers();
	public boolean addAccount(String username, String password, String phone);
	public boolean checkExistAccountInfo(String username, String phone); 
	
	public boolean checkExistPhoneInfo(String phone); 
	public boolean checkExistUsernameInfo(String username); 
}
