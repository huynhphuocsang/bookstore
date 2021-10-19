package com.ptit.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.ptit.model.User;

public interface UserDao extends JpaRepository<User, Long>{
	@Query("SELECT COUNT(u) FROM User u")
    public Long countUsers();
	
	public List<User> findByUsernameOrPhoneAllIgnoreCase(String username, String phone); 
	public List<User> findByUsernameAllIgnoreCase(String username); 
	public List<User> findByPhone(String phone); 
	public User findByUsername(String username); 
}
