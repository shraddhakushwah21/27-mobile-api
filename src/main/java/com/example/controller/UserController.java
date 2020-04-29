package com.example.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.domain.UserMaster;
import com.example.exception.UserNotFoundException;
import com.example.service.UserService;

import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.Authorization;

@RestController
public class UserController {

	@Autowired
	private UserService userService;
	
	@ApiOperation(authorizations = {@Authorization(value="jwtToken")}, value = "Create USer Profile")
	@PostMapping(value = "/register",consumes = {"application/json","application/xml"})
	public ResponseEntity<String> registerUser(@RequestBody UserMaster user){
		boolean registerUser = userService.registerUser(user);
		if(registerUser) {
			return new ResponseEntity<String>("User Registered Successfully.",HttpStatus.CREATED);
		}else {
			return new ResponseEntity<String>("User NOT Registered !",HttpStatus.BAD_REQUEST);
		}
	}
	
	@ApiOperation(authorizations = {@Authorization(value="jwtToken")}, value = "Get User Details by Passing Name")
	@GetMapping(value = "/getUser/username/{username}",produces = {"application/json","application/xml"})
	public ResponseEntity<UserMaster> getUserByName(@PathVariable("username") String username){
		UserMaster user = userService.getUserByUserName(username);
			return new ResponseEntity<UserMaster>(user,HttpStatus.OK);  
	}
	
	
	@ApiOperation(authorizations = {@Authorization(value="jwtToken")}, value = "Get User Details by Passing Phone Number")
	@GetMapping(value = "/getUser/phone/{phone}",produces = {"application/json","application/xml"})
	public ResponseEntity<UserMaster> getUserByPhone(@PathVariable("phone") String phone){
		UserMaster user = userService.getUserByPhoneNumber(phone);
			return new ResponseEntity<UserMaster>(user,HttpStatus.OK);  
	}
	
	@ApiOperation(authorizations = {@Authorization(value="jwtToken")}, value = "Get User Details by Passing Email")
	@GetMapping(value = "/getUser/email/{email}",produces = {"application/json","application/xml"})
	public ResponseEntity<UserMaster> getUserByEmail(@PathVariable("email") String email){
		UserMaster user = userService.getUserByEmail(email);
			return new ResponseEntity<UserMaster>(user,HttpStatus.OK);  
	}
	
	
	@ApiOperation(authorizations = {@Authorization(value="jwtToken")}, value = "Create Spam Number")
	@PostMapping(value = "/spam/phone/{phone}",consumes = {"application/json","application/xml"})
	public ResponseEntity<String> createSpamUser(@PathVariable("phone") String phone){
		boolean spamUser = userService.createSpamUser(phone);
		if(!spamUser) {
			throw new UserNotFoundException("User Not Found!");
		}else {
			return new ResponseEntity<String>("User created as SPAM Successfully.",HttpStatus.CREATED);
		}
	}
}
