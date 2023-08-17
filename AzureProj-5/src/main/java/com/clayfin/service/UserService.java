package com.clayfin.service;

import java.sql.Date;

import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.clayfin.model.Registration;
import com.clayfin.model.User;
import com.clayfin.repository.RegistrationRepo;
import com.clayfin.repository.UserRepo;

@Service
public class UserService {
	
	@Autowired
	private UserRepo userRepo;
	
	
	@Autowired
	 private RegistrationRepo registrationRepo;
	
	
	private static final org.slf4j.Logger logger = LoggerFactory.getLogger(UserService.class);
	
	
	public User createUser(User user) {
        
        userRepo.save(user);
        Registration register = new Registration();
        register.setUser(user);
        register.setStatus("pending");
        register.setRegisterTime(new Date(System.currentTimeMillis()));
        registrationRepo.save(register);
        return user;

    }
    
    public User getUserByUsername(String username) {
        return userRepo.findByUsername(username);
    }
    
	public User getUserByMail(String mail) {
		return userRepo.findByMail(mail);
	}
    
   
}
