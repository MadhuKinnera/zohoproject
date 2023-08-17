
package com.clayfin;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.clayfin.model.Registration;
import com.clayfin.model.User;
import com.clayfin.repository.AttendenceDetailsRepo;
import com.clayfin.repository.LeaveRepo;
import com.clayfin.repository.RegistrationRepo;
import com.clayfin.service.AttendenceDetailsService;
import com.clayfin.service.UserService;

@Controller
public class ControllerMain {

	

	@Autowired
	RegistrationRepo registrationRepo;

	@Autowired
	UserService userService;

	@Autowired
	LeaveRepo leaveRepo;

	@GetMapping("/home")
	public String dis() {
		return "home";

	}

	@GetMapping("/")
	public String home() {
		return "home";
	}

	// @Autowired //LeaveService leaveService;
/*
	@GetMapping("/elogin")
	public String user(HttpSession session, @AuthenticationPrincipal OAuth2User user) {
		Authentication auth1 = SecurityContextHolder.getContext().getAuthentication();
		System.out.println(auth1.getPrincipal());
		System.out.println(auth1.getCredentials());
		String name = user.getAttribute("name");
		String email = user.getAttribute("preferred_username");

		Employee emp = employeeService.getEmployeeByEmail(email);
		if (emp == null) {
			Employee employee = new Employee();
			employee.setUsername(name);
			employee.setEmail(email);
			return "employee";
		} else {

			session.setAttribute("empId", emp.getId());
			return "existedEmp";
		}

	}
*/
	@Autowired
	AttendenceDetailsService attendenceDetailsService;

	@Autowired
	AttendenceDetailsRepo attendenceDetailsRepo;

	@CrossOrigin

	@GetMapping("/apply")
	public String app(Model model) {
		List<Registration> registrations = registrationRepo.findAllByAutho();
		model.addAttribute("employees", registrations);
		return "emp";
	}

	@CrossOrigin

	@RequestMapping("/signup")
	public String display3(@ModelAttribute User user) {
		ModelAndView mv = new ModelAndView();
		mv.addObject("user", user);
		return "signup";
	}

	@CrossOrigin
  
  @PostMapping("/storeData") public String
  display4(@javax.validation.Valid @ModelAttribute User user) {
  System.out.println(user); 
  userService.createUser(user);
  
  // Employee emp = employeeRepo.findByEmail(user.getMail());
  // if (emp ==  null) { // return "loginPending"; // } else { // return "existedEmp"; // }
  return "existedEmp";
   }

//	@CrossOrigin
/*
	@PostMapping("/authorizeEmp")
	public String Autherize(@RequestParam("id") Integer id, @RequestParam("role") String role, Model model) {
		System.out.println("in emp ser0");
		Registration register = registrationRepo.findById(id).get();
		employeeService.createEmployee(register, role);
		List<Registration> registrations = registrationRepo.findAllByAutho();
		model.addAttribute("employees", registrations);
		return "emp";

	}
*/
}
