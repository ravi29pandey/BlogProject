package com.ravi.blog.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import com.ravi.blog.entities.User;
import com.ravi.blog.exceptions.ResourceNotFoundException;
import com.ravi.blog.repositories.UserRepo;

public class CustomUserDetailService implements UserDetailsService{

	
	@Autowired
	private UserRepo userRepo;
	
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
          //loading user from database
		
         User userName=this.userRepo.findByEmail(username).orElseThrow(()-> new ResourceNotFoundException("username", "email :", 0))	;
	
	
	
		return userName;
	}

}
