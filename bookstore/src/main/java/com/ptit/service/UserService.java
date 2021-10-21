package com.ptit.service;

import org.springframework.web.bind.annotation.RequestParam;

import com.ptit.model.User;

public interface UserService {
	public Long countUsers();
	public boolean addAccount(String username, String password, String phone);
	public boolean checkExistAccountInfo(String username, String phone); 
	
	public boolean checkExistPhoneInfo(String phone); 
	public boolean checkExistUsernameInfo(String username); 
	public User getUserByUsername(String username); 
	public boolean updateUserInfo(String username, String email, String phone, boolean gender, int age ); 
	public boolean checkExistEmailInfo(String email, String username);
	public boolean checkExistPhoneInfo(String phone, String username); 
	public boolean verifyOldPassword(String oldPassword, String username); 
	public boolean updatePassword(String password, String username); 
}
