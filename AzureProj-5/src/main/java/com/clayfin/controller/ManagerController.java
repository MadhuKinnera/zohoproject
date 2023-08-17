package com.clayfin.controller;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.clayfin.DateStatus;
import com.clayfin.model.AttendanceDetails;
import com.clayfin.model.Leavetable;
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

@Controller
@RequestMapping("manager")
public class ManagerController {

	@Autowired
	UserRepo userRepo;

	@Autowired
	RegistrationRepo registrationRepo;

	@Autowired
	UserService userService;

	@Autowired
	LeaveRepo leaveRepo;

	@Autowired
	TaskRepo taskRepo;

	@Autowired
	AttendenceDetailsRepo attendenceDetailsRepo;

	@Autowired
	AttendenceDetailsService attendenceDetailsService;

	@GetMapping("/dashboard")
	public String home(@AuthenticationPrincipal MyUserDetails manager, Model model) {

		String managerName = manager.getUsername();
		User managerDetails = userRepo.findByUsername(managerName);
		List<User> employees = userRepo.getAllEmployeesByManager(managerDetails.getUserId());

		model.addAttribute("emps", employees);
		return "dashboard";
	}

	@PostMapping("asignTask")
	public String asignTask(@AuthenticationPrincipal MyUserDetails manager, @RequestParam("id") String id,
			@RequestParam("task") String task, Model model) {
		Task task2 = new Task();
		task2.setAssignedTime(LocalDateTime.now());
		System.out.println("id is " + id);
		Integer ids = Integer.parseInt(id.substring(0, id.length() - 1));
		User employee = userRepo.findById(ids).get();
		task2.setUser(employee);
		task2.setTaskName(task);
		if (task != null && task != "" && task != " ") {
			taskRepo.save(task2);
		}

		String managerName = manager.getUsername();
		User managerDetails = userRepo.findByUsername(managerName);
		List<User> employees = userRepo.getAllEmployeesByManager(managerDetails.getUserId());

		model.addAttribute("emps", employees);
		return "dashboard";
	}

	@CrossOrigin
	@PostMapping("/dashboard")
	public String dist(@RequestBody DateStatus dateTime, HttpSession session,
			@AuthenticationPrincipal MyUserDetails employee, Model model) {
		System.out.println(dateTime.getDate());
		String status = dateTime.getStatus();
		User manager = userRepo.findByUsername(employee.getUsername());
		System.out.println(status);

		if (status.equals("CHECKIN")) {
			System.out.println("...............");
			AttendanceDetails attendanceDetails = new AttendanceDetails();
			System.out.println("check in");
			attendanceDetails.setLoginTimestamp(LocalTime.now() + "");
			attendanceDetails.setDate(Date.from(Instant.now()));
			attendanceDetails.setUser(manager);
			attendenceDetailsRepo.save(attendanceDetails);

		} else if (status.equals("CHECKOUT")) {
			List<AttendanceDetails> attendanceDetailsList = attendenceDetailsService.findByUserId(manager.getUserId());
			System.out.println("ckeck out ");

			AttendanceDetails attendanceDetails = attendanceDetailsList.get(0);
			System.out.println(attendanceDetails);
			attendanceDetails.setLogoutTime(LocalTime.now() + "");
			attendanceDetails.setDate(Date.from(Instant.now()));
			attendanceDetails.setUser(manager);
			attendenceDetailsRepo.save(attendanceDetails);
		}

		String managerName = employee.getUsername();
		User managerDetails = userRepo.findByUsername(managerName);
		List<User> employees = userRepo.getAllEmployeesByManager(managerDetails.getUserId());

		model.addAttribute("emps", employees);
		return "dashboard";
	}

	@GetMapping("/leaveRequests")
	public String leaveRequests(Model model) {
		List<Leavetable> leaves = leaveRepo.findAllByStatus("pending");
		System.out.println(leaves);
		model.addAttribute("leavereq", leaves);
		List<Leavetable> emgLeaves = leaveRepo.findAllByStatus("emergency");
		model.addAttribute("emgReq", emgLeaves);
		return "leaveRequests";
	}

	@GetMapping("leaveApprove/{id}")
	public String leaveApprove(Model model, @PathVariable("id") Integer id) {

		Leavetable leavetable = leaveRepo.findById(id).get();
		leavetable.setStatus("Approved");
		leaveRepo.save(leavetable);
		List<Leavetable> leaves = leaveRepo.findAllByStatus("pending");
		System.out.println(leaves);
		model.addAttribute("leavereq", leaves);
		List<Leavetable> emgLeaves = leaveRepo.findAllByStatus("emergency");
		model.addAttribute("emgReq", emgLeaves);
		return "leaveRequests";
	}

	@GetMapping("leaveReject/{id}")
	public String leaveReject(Model model, @PathVariable("id") Integer id) {

		Leavetable leavetable = leaveRepo.findById(id).get();
		leavetable.setStatus("Rejected");
		leaveRepo.save(leavetable);
		List<Leavetable> leaves = leaveRepo.findAllByStatus("pending");
		System.out.println(leaves);
		model.addAttribute("leavereq", leaves);
		List<Leavetable> emgLeaves = leaveRepo.findAllByStatus("emergency");
		model.addAttribute("emgReq", emgLeaves);
		return "leaveRequests";
	}

	@GetMapping("/")
	public String employeeHome(@AuthenticationPrincipal MyUserDetails manager, Model model) {
		String managerName = manager.getUsername();
		User managerDetails = userRepo.findByUsername(managerName);
		List<User> employees = userRepo.getAllEmployeesByManager(managerDetails.getUserId());

		model.addAttribute("emps", employees);

		return "dashboard";

	}

	@GetMapping("/addEmp")
	public String addEmp() {
		return "addEmp";
	}

	@PostMapping("/addingEmp")
	public String adding(@AuthenticationPrincipal MyUserDetails manager, Model model, @RequestParam("name") String name,
			@RequestParam("email") String email, @RequestParam("password") String password) {

		String managerName = manager.getUsername();
		User managerDetails = userRepo.findByUsername(managerName);
		User employee = new User();
		employee.setUsername(name);
		employee.setMail(email);
		employee.setPassword(password);
		employee.setManagerId(managerDetails.getUserId());
		employee.setRole("ROLE_EMP");
		userRepo.save(employee);

		List<User> employees = userRepo.getAllEmployeesByManager(managerDetails.getUserId());

		model.addAttribute("emps", employees);

		return "dashboard";
	}

}
