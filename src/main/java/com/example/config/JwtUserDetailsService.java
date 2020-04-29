package com.example.config;

import java.util.ArrayList;

import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class JwtUserDetailsService implements UserDetailsService {

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		if("jwtToken".equals(username)) {
			return new User("jwtToken", "{noop}jwtToken", new ArrayList<>());
		}else {
			throw new UsernameNotFoundException("User not found with username: " + username);
		}
		//return null;
	}

}