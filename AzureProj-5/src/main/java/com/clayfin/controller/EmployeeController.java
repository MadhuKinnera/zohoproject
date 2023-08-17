package com.clayfin.controller;

import java.sql.Timestamp;
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
@RequestMapping("employee")
public class EmployeeController {

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

	@GetMapping("hi")
	public String hi() {
		return "hi";
	}

	@CrossOrigin
	@GetMapping("existedEmp")
	public String displayHello(@AuthenticationPrincipal MyUserDetails employee, Model model) {
		LocalDateTime date = LocalDateTime.now();
		User employee1 = userRepo.findByUsername(employee.getUsername());
		System.out.println("manager " + employee1.getUsername());
		Integer id = employee1.getUserId();
		List<Task> tasks = taskrepo.findAllByUser_Id(id);
		model.addAttribute("tasks", tasks);
		return "existedEmp";
	}

	@CrossOrigin
	@PostMapping("existedEmp")
	public String dist(@RequestBody DateStatus dateTime, HttpSession session,
			@AuthenticationPrincipal MyUserDetails user, Model model) {
		System.out.println(dateTime.getDate());
		String status = dateTime.getStatus();
		User user1 = userRepo.findByUsername(user.getUsername());
		System.out.println(status);

		if (status.equals("CHECKIN")) {
			System.out.println("...............");
			AttendanceDetails attendanceDetails = new AttendanceDetails();
			System.out.println("check in");
			attendanceDetails.setLoginTimestamp(LocalTime.now() + "");
			attendanceDetails.setDate(Date.from(Instant.now()));
			attendanceDetails.setUser(user1);
			attendenceDetailsRepo.save(attendanceDetails);

		} else if (status.equals("CHECKOUT")) {
			List<AttendanceDetails> attendanceDetailsList = attendenceDetailsService.findByUserId(user1.getUserId());
			System.out.println("ckeck out ");

			AttendanceDetails attendanceDetails = attendanceDetailsList.get(0);
			System.out.println(attendanceDetails);
			attendanceDetails.setLogoutTime(LocalTime.now() + "");
			attendanceDetails.setDate(Date.from(Instant.now()));
			// attendanceDetails.setEmployee(employee1);
			attendenceDetailsRepo.save(attendanceDetails);
		}

		Integer id = user1.getUserId();
		List<Task> tasks = taskrepo.findAllByUser_Id(id);
		model.addAttribute("tasks", tasks);

		return "existedEmp";
	}

	@GetMapping("/leave")
	public String leave() {
		return "leave";
	}

	@GetMapping("/emgLeave")
	public String emgLeave() {
		return "emgLeave";
	}

	@PostMapping("/emgLeave")
	public String emgLeaveApply(Model model, @RequestParam("days") Integer days, @RequestParam("reason") String reason,
			@AuthenticationPrincipal MyUserDetails employee) {
		Leavetable leavetable = new Leavetable();
		leavetable.setDays(days);
		System.out.println(employee.getUsername());
		User employee1 = userRepo.findByUsername(employee.getUsername());
		leavetable.setUser(employee1);
		leavetable.setStatus("emergency");
		leavetable.setReason(reason);
		leavetable.setTimestamp(Timestamp.from(Instant.now()));
		leaveRepo.save(leavetable);

		System.out.println("emergency leave registred successfuly");

		Integer id = employee1.getUserId();
		List<Task> tasks = taskrepo.findAllByUser_Id(id);
		model.addAttribute("tasks", tasks);

		return "existedEmp";
	}

	@PostMapping("/applyLeave")
	public String leaveApproval(Model model, @RequestParam("days") Integer days, @RequestParam("reason") String reason,
			@AuthenticationPrincipal MyUserDetails employee) {
		Leavetable leavetable = new Leavetable();
		leavetable.setDays(days);
		System.out.println(employee.getUsername());
		User employee1 = userRepo.findByUsername(employee.getUsername());
		leavetable.setUser(employee1);
		leavetable.setStatus("pending");
		leavetable.setReason(reason);
		leavetable.setTimestamp(Timestamp.from(Instant.now()));
		leaveRepo.save(leavetable);
		System.out.println("leave registred successfuly");

		Integer id = employee1.getUserId();
		List<Task> tasks = taskrepo.findAllByUser_Id(id);
		model.addAttribute("tasks", tasks);
		return "existedEmp";
	}

	@GetMapping("/")
	public String employeeHome(Model model, @AuthenticationPrincipal MyUserDetails employee) {
		User employee1 = userRepo.findByUsername(employee.getUsername());
		LocalDateTime date = LocalDateTime.now();
		Integer id = employee1.getUserId();
		List<Task> tasks = taskrepo.findAllByUser_Id(id);
		model.addAttribute("tasks", tasks);
		return "existedEmp";

	}

}
