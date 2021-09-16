package com.ptit.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.ptit.model.User;

public interface UserDao extends JpaRepository<User, Long>{
	@Query("SELECT COUNT(u) FROM User u")
    public Long countUsers();
}
