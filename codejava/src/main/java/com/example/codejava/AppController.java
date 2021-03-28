package com.example.codejava;

import java.util.List;

import org.apache.tomcat.util.net.openssl.ciphers.Authentication;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
public class AppController {

	@Autowired
	private UserRepository userRepo;
	
	@GetMapping("/login")
	public String login() {
		return "PLogin.html";
	}

	@GetMapping("/Appointments")
	public String Appointments() {
		return "Appointments.html";
	}

	@GetMapping("/Vaccine")
	public String Vaccine() {
		return "PVaccine.html";
	}

	@GetMapping("/Test")
	public String Test() {
		return "PTest.html";
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
	public String PatientHome(Model model)
	{
//		List<User> listUsers = userRepo.findAll();
//		model.addAttribute("listUsers", listUsers);

		return "PHome";
	}

//	@GetMapping("/PHome")
//	public String PHome(Model model)
//	{
//		return "PHome";
//	}

//	@RequestMapping(value = "/PHome", method = RequestMethod.GET)
//	@ResponseBody
//	public String currentUserName(Authentication authentication) {
//		return authentication.name();
//	}

//	@RequestMapping(value = "/employees", method = RequestMethod.GET)
//	public String overviewEmployee(Model model) {
//		model.addAttribute("sections", sectionService.getAllSections());
//		model.addAttribute("personSectionService", personSectionService);
//		return "employee-list";
//	}
//
//	@RequestMapping(value = "/overviewEmployee", method = RequestMethod.GET)
//	public String overviewEmployee(Model model, @RequestParameter long id) {
//		model.addAttribute("currentPerson", personService.getById(id));
//		return "overviewEmployee";
//	}

}
