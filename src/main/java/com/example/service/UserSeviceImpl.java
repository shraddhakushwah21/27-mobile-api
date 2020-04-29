package com.example.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.domain.UserMaster;
import com.example.entity.UserMasterEntity;
import com.example.exception.UserNotFoundException;
import com.example.repository.UserMasterRepository;

@Service
public class UserSeviceImpl  implements UserService{

	@Autowired
	private UserMasterRepository userRepo;
	
	
	@Override
	public boolean registerUser(UserMaster user) {
		user.setSpam(false);
		UserMasterEntity userEntity=new UserMasterEntity();
		BeanUtils.copyProperties(user, userEntity);
		userEntity.setCreatedBy("Shraddha");
		UserMasterEntity isSaved = userRepo.save(userEntity);
		return isSaved.getUserId()!=null;
	}

	@Override
	public UserMaster getUserByUserName(String username) {
		UserMasterEntity userMasterEntity = userRepo.findByusername(username);
		if(userMasterEntity==null) {
			throw new UserNotFoundException("User Not Found!");
		}
		UserMaster user=new UserMaster();
		BeanUtils.copyProperties(userMasterEntity, user);
		return user;
	}

	@Override
	public UserMaster getUserByPhoneNumber(String phone) {
		UserMasterEntity userMasterEntity = userRepo.findByPhoneNumber(phone);
		if(userMasterEntity==null) {
			throw new UserNotFoundException("User Not Found!");
		}
		UserMaster user=new UserMaster();
		BeanUtils.copyProperties(userMasterEntity, user);
		return user;
	}

	@Override
	public UserMaster getUserByEmail(String email) {
		if(email.equals(null)||email.equals(" ")) {
			throw new UserNotFoundException("Email is not Available for this user");
		}else {
		UserMasterEntity userMasterEntity = userRepo.findByEmail(email);
		if(userMasterEntity==null) {
			throw new UserNotFoundException("User Not Found!");
		}
		UserMaster user=new UserMaster();
		BeanUtils.copyProperties(userMasterEntity, user);
		return user;
		}
	}

	@Override
	public List<UserMaster> getUsers() {
		 List<UserMasterEntity> findAll = userRepo.findAll();
		 List<UserMaster> users=new ArrayList<UserMaster>();
		 findAll.forEach(entity->{
			 UserMaster user=new UserMaster();
			 BeanUtils.copyProperties(entity, user);
			 users.add(user);
		 });
		 return users;
	}

	@Override
	public boolean createSpamUser(String phone) {
		UserMasterEntity entity = userRepo.findByPhoneNumber(phone);
		if(entity!=null) {
			entity.setSpam(true);
			return userRepo.save(entity) != null;
			
		}
		return false;
	}

}
