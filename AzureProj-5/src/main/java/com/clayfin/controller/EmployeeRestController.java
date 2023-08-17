package com.clayfin.controller;

import java.time.Instant;
import java.time.LocalTime;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.azure.core.annotation.PathParam;
import com.clayfin.model.AttendanceDetails;
import com.clayfin.model.MyUserDetails;
import com.clayfin.model.Task;
import com.clayfin.model.User;
import com.clayfin.repository.AttendenceDetailsRepo;
import com.clayfin.repository.LeaveRepo;
import com.clayfin.repository.RegistrationRepo;
import com.clayfin.repository.TaskRepo;
import com.clayfin.repository.UserRepo;
import com.clayfin.service.AttendenceDetailsService;
import com.clayfin.service.UserService;
import com.google.gson.Gson;  
@RestController
@RequestMapping("/employee")
@CrossOrigin(origins = "*",maxAge = 3600)
public class EmployeeRestController {
	
	
	@Autowired
	UserRepo userRepo;

	@Autowired
	RegistrationRepo registrationRepo;

	@Autowired
	UserService userService;
	
	@Autowired
	LeaveRepo leaveRepo;
	
	@Autowired
	AttendenceDetailsRepo attendenceDetailsRepo;
	
	@Autowired
	AttendenceDetailsService attendenceDetailsService;
	
	@Autowired
	TaskRepo taskrepo;
	
	
	
	@GetMapping("/hello")
	//@CrossOrigin(origins = "http://localhost:3000")
	public String hey() {
		System.out.println("method calling");
		return "Hey There ";
	}
	
	@PostMapping("/hello")
	@CrossOrigin(origins = "http://localhost:3000")
	public String hey1() {
		System.out.println("method calling");
		return "Hey There ";
	}
	
	@GetMapping("/get")
	@CrossOrigin(origins = "http://localhost:3000")
	public String retur(@AuthenticationPrincipal MyUserDetails user,@AuthenticationPrincipal OAuth2User user2) {
		
		String name1;
		if(user2!=null) {
			String name = user2.getAttribute("name");
			String[] words = name.split(" ");
			 name1 = words[0];
			 System.out.println(name1+"-------");
		}else {
		
		
		Authentication auth1 = SecurityContextHolder.getContext().getAuthentication();
		
	/*	String name = user.getAttribute("name");
		String[] words = name.split(" ");
		String name1 = words[0];
		
		*/
		
	 name1 = user.getUsername();
	 System.out.println(name1+"++++++++++++++");
		}
		User employee1 =  userRepo.findByUsername(name1);
		System.out.println(employee1);
//		return  employee1;
		return new Gson().toJson(employee1);
	}
	
	@CrossOrigin("**")
	@PostMapping("/check/{msg}")
	public List<Task> dist(@AuthenticationPrincipal OAuth2User user,@PathVariable String msg) {
		
	
		Authentication auth1 = SecurityContextHolder.getContext().getAuthentication();
		System.out.println(auth1.getPrincipal());
		System.out.println(auth1.getCredentials());
		String name = user.getAttribute("name");
		String[] words = name.split(" ");
		String name1 = words[0];
		 
		User employee1 =  userRepo.findByUsername(name1);
		System.out.println(employee1.getUserId());
		if (msg.equals("CHECKIN")) {
			System.out.println("...............");
			AttendanceDetails attendanceDetails = new AttendanceDetails();
			System.out.println("check in");
			attendanceDetails.setLoginTimestamp(LocalTime.now()+"");
			attendanceDetails.setDate(Date.from(Instant.now()));
			attendanceDetails.setUser(employee1);
			attendenceDetailsRepo.save(attendanceDetails);
			
		} else if(msg.equals("CHECKOUT")){
			List<AttendanceDetails> attendanceDetailsList = attendenceDetailsService.findByUserId(employee1.getUserId());
			System.out.println("ckeck out ");
			
			AttendanceDetails attendanceDetails = attendanceDetailsList.get(0);
			System.out.println(attendanceDetails);
			attendanceDetails.setLogoutTime(LocalTime.now()+"");
			attendanceDetails.setDate(Date.from(Instant.now()));
		//	attendanceDetails.setEmployee(employee1);
			attendenceDetailsRepo.save(attendanceDetails);
		}
		Integer id = employee1.getUserId();
		List<Task> tasks = taskrepo.findAllByUser_Id(id);
		
		
		return tasks;
	}
	
	
	@PostMapping("/employees/{id}/checkin")
	public String checkin(@PathParam("id") Integer id) {
		System.out.println("checkin    "+id);
		return "checkin";
	}
	

}
