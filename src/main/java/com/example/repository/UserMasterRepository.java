package com.example.repository;

import java.io.Serializable;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.entity.UserMasterEntity;

public interface UserMasterRepository extends JpaRepository<UserMasterEntity, Serializable> {

	public UserMasterEntity findByPhoneNumber(String phoneNo);
	
	public UserMasterEntity findByusername(String username);
	
	public UserMasterEntity findByEmail(String email);
}
