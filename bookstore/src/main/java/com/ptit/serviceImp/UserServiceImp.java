package com.ptit.serviceImp;

import java.util.List;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.stereotype.Service;

import com.ptit.model.Role;
import com.ptit.model.User;
import com.ptit.model.UserRole;
import com.ptit.repository.RoleDao;
import com.ptit.repository.UserDao;
import com.ptit.repository.UserRoleDao;
import com.ptit.service.UserService;

@Service
public class UserServiceImp implements UserService{

	@Autowired
	UserDao userDao;
	
	@Autowired
	RoleDao roleDao; 
	
	@Autowired
	UserRoleDao userRoleDao; 
	
	

	
	@Override
	public Long countUsers() {
		// TODO Auto-generated method stub
		return userDao.countUsers();
	}

	@Override
	public boolean addAccount(String username, String password, String phone) {
		
		
		User user = new User(); 
		
		String passwordEncrypt =BCrypt.hashpw(password, BCrypt.gensalt(12)); 
		user.setUsername(username);
		user.setPassword(passwordEncrypt);
		user.setPhone(phone);
		user.setAge(10);
		
		userDao.save(user); 
		
		User userAfterSave = userDao.findByUsername(username); 
		Role role = roleDao.findByRoleName("ROLE_USER"); 
		UserRole userRole = new UserRole(0, userAfterSave, role);
		userRoleDao.save(userRole); 
		
		return true;
	}

	@Override
	public boolean checkExistAccountInfo(String username, String phone) {
		List<User> list = userDao.findByUsernameOrPhoneAllIgnoreCase(username, phone); 
		if(list.size()>0) return true;
		return false;
	}

	@Override
	public boolean checkExistPhoneInfo(String phone) {
		List<User> list = userDao.findByPhone(phone); 
		if(list.size()>0) return true; 
		return false;
	}

	@Override
	public boolean checkExistUsernameInfo(String username) {
		List<User> list = userDao.findByUsernameAllIgnoreCase(username); 
		if(list.size()>0) return true; 
		return false;
	}

}
