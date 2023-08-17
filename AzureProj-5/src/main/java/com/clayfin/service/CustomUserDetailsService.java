package com.clayfin.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.clayfin.model.MyUserDetails;
import com.clayfin.model.User;
import com.clayfin.repository.UserRepo;

@Service
public class CustomUserDetailsService implements UserDetailsService {

	MyUserDetails myUserDetails;

	@Autowired
	UserRepo userRepo;

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		
		
		System.out.println("user name is "+username);
		User user = userRepo.findByUsername(username);
		// Employee employee = employeeRepo.findByUsername(username);
         if(user!=null)
		System.out.println(user.getUsername());
		return new MyUserDetails(user);
	}
}
