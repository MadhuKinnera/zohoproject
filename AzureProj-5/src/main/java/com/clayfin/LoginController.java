package com.clayfin;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.clayfin.model.LoginDTO;
import com.clayfin.model.User;
import com.clayfin.repository.UserRepo;
import com.google.gson.Gson;

@RestController
public class LoginController {

	@Autowired
	UserRepo userRepo;

	@CrossOrigin("*")
	@PostMapping(path = "/employeeee", consumes = "application/json", produces = "application/json")
	public String getAllEmployees(@RequestBody LoginDTO myUserDetails) {

		User user = null;
		if (!myUserDetails.getUsernameOrEmail().contains(".com"))
			user = userRepo.findByUsername(myUserDetails.getUsernameOrEmail());
		else
			user = userRepo.findByMail(myUserDetails.getUsernameOrEmail());
		String result = "";
		if (user == null) {
			result = "Not Found";
		} else if (user.getPassword().equals(myUserDetails.getPassword())) {
			result = "Success";
		} else {
			result = "Error";
		}

		return new Gson().toJson(result);

	}

//	
//	@PostMapping()
//	public ResponseEntity<LoginResponseDTO> performLogin(@Valid @RequestBody LoginRequestDTO loginRequestDTO){
//		return ResponseEntity.ok(new LoginResponseDTO("Success !"));
//	}

//	
//	@GetMapping("/employeeee")
//    public String /* List<User> */ getEmp() {
//		
//		 
//		User employee1 =  userRepo.findByUsername("karun");
//    //	System.out.println(employee1.getUsername());
//	//	System.out.println(userRepo.findAll());
//        return new Gson().toJson(employee1);
//		//return userRepo.findAll();
//    }  
}
