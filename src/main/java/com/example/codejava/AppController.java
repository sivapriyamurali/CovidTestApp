package com.example.codejava;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class AppController {

	@Autowired
	private UserRepository userRepo;
	
	@GetMapping("/login")
	public String login() {
		return "PLogin.html";
	}
	
	@GetMapping("/register")
	public String showRegistrationForm(Model model) {
		model.addAttribute("user", new User());
		
		return "Registration.html";
	}
	
	@PostMapping("/process_register")
	public String processRegister(User user) {
		BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
		String encodedPassword = passwordEncoder.encode(user.getPassword());
		user.setPassword(encodedPassword);
		
		userRepo.save(user);

		return "register_success";
	}
	
	@GetMapping("/users")
	public String listUsers(Model model) {
		List<User> listUsers = userRepo.findAll();
		model.addAttribute("listUsers", listUsers);
		
		return "users";
	}

	@GetMapping("/PHome")
	public String PatientHome(Model model) {
//		List<User> listUsers = userRepo.findAll();
//		model.addAttribute("listUsers", listUsers);

		return "PHome.html";
	}


	@GetMapping("/hcAppt")
	public String hcAppt(Model model) {
//		List<User> listUsers = userRepo.findAll();
//		model.addAttribute("listUsers", listUsers);

		return "HC_ViewAppointment.html";
	}
}
