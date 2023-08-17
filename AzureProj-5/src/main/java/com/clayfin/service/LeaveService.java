package com.clayfin.service;

import java.sql.Timestamp;
import java.time.Instant;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.clayfin.model.Leavetable;
import com.clayfin.model.User;
import com.clayfin.repository.LeaveRepo;

@Service
public class LeaveService {
	
	@Autowired
	LeaveRepo leaveRepo;
	
	public Leavetable createLeave(User user,int days) {
		Leavetable leavetable = new Leavetable();
		leavetable.setUser(user);
		leavetable.setDays(days);
		leavetable.setTimestamp(Timestamp.from(Instant.now()));
		leaveRepo.save(leavetable);
		return leavetable;
	}
}
