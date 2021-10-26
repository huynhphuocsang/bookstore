package com.ptit.repository;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.ptit.model.User;

public interface UserDao extends JpaRepository<User, Long>{
	@Query("SELECT COUNT(u) FROM User u")
    public Long countUsers();
	
	public List<User> findByUsernameOrPhoneAllIgnoreCase(String username, String phone); 
	public List<User> findByUsernameAllIgnoreCase(String username); 
	public List<User> findByPhone(String phone); 
	public User findByUsername(String username);
	
	@Query(value = "select u.* from User u where u.email = :email and u.username NOT LIKE :username ", nativeQuery = true)
	public List<User> verifyDuplicateEmail(@Param("email") String email, @Param("username") String username); 
	
	@Query(value = "select u.* from User u where u.phone = :phone and u.username NOT LIKE :username ", nativeQuery = true)
	public List<User> verifyDuplicatePhone(@Param("phone") String phone, @Param("username") String username); 
	
}
