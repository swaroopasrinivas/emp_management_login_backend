package com.example.demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.demo.entity.User;

public interface RegistrationRepository extends JpaRepository<User, Integer>{
	
	public User findByEmailId(String emailId);
	
	public User findByEmailIdAndPassword(String emailId, String password);

}
