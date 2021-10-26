package com.ptit.serviceImp;

import java.util.List;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
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

	@Override //use for signup
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

	@Override
	public User getUserByUsername(String username) {
		return userDao.findByUsername(username); 
	}

	@Override
	public boolean updateUserInfo(String username, String email, String phone, boolean gender, int age) {
		User user = userDao.findByUsername(username);
		user.setEmail(email);
		user.setPhone(phone);
		user.setGender(gender);
		user.setAge(age);
		userDao.save(user); 
		return true;
	}

	@Override
	public boolean checkExistEmailInfo(String email, String username) {
		
		List<User> list = userDao.verifyDuplicateEmail(email, username); 
		
		if(list.size()>0) return true; 
		return false;
	}

	@Override //use for user update info
	public boolean checkExistPhoneInfo(String phone, String username) {
		List<User> list = userDao.verifyDuplicateEmail(phone, username); 
		
		if(list.size()>0) return true; 
		return false;
		
	}

	@Override
	public boolean verifyOldPassword(String oldPassword, String username) {
		BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder(); 
		User user = userDao.findByUsername(username); 
		
		
		if(passwordEncoder.matches(oldPassword, user.getPassword())) return true; 
		return false;
	}

	@Override
	public boolean updatePassword(String password, String username) {
		String passwordEncrypt = BCrypt.hashpw(password, BCrypt.gensalt(12)); 
		User user = userDao.findByUsername(username); 
		user.setPassword(passwordEncrypt);
		userDao.save(user); 
		return true;
	}
	@Override
	public Page<User> getAllUser(Pageable page){
		return userDao.findAll(page);
	}
	
	@Override
	public List<User> findUser(String key){
		List<User> list=userDao.findByUsernameOrPhoneAllIgnoreCase(key, key);
		return list; 
	}
	
	@Override
	public Page<User> findPage(int pageNo, int pageSize){
		Pageable pageable = PageRequest.of(pageNo -1, pageSize);
		return userDao.findAll(pageable);
	}
	
	@Override
	public Page<User> findPaginated(int pageNo, int pageSize, String sortField, String sortDirection){
		Sort sort = sortDirection.equalsIgnoreCase(Sort.Direction.ASC.name()) 
				? Sort.by(sortField).ascending() : Sort.by(sortField).descending() ;
		
		Pageable pageable = PageRequest.of(pageNo -1, pageSize,sort);
		return userDao.findAll(pageable);
	}

}
