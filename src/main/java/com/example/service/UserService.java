package com.example.service;

import java.util.List;

import com.example.domain.UserMaster;

public interface UserService {

	public boolean registerUser(UserMaster user);
	public UserMaster getUserByUserName(String username);
	public UserMaster getUserByPhoneNumber(String phone);
	public UserMaster getUserByEmail(String email);
	public List<UserMaster> getUsers();
	public boolean createSpamUser(String phone);
}
