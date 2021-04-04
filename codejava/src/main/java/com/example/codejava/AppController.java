package com.example.codejava;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
public class AppController {

	@Autowired
	private UserRepository userRepo;

	@Autowired
	private TransactionAppRepository apptrepo;
	
	@GetMapping("/login")
	public String login()
	{
		return "PLogin.html";
	}


	@GetMapping("/Appointments")
	public String Appointments(Model model, @AuthenticationPrincipal CustomUserDetails userna)
	{

		List<TransactionApp> listAppsPast = apptrepo.findByNamePastAppointments(userna.getUsername());
		List<TransactionApp> listAppsFuture = apptrepo.findByNameFutureAppointments(userna.getUsername());

		model.addAttribute("listAppsPast", listAppsPast);
		model.addAttribute("listAppsFuture", listAppsFuture);

		return "Appointments";
	}



	@GetMapping("/Vaccine")
	public String Vaccine(Model model)
	{
		model.addAttribute("appt", new TransactionApp());
		return "PVaccine.html";
	}

	@GetMapping("/Test")
	public String Test(Model model)
	{
		model.addAttribute("appt", new TransactionApp());
		return "PTest.html";

	}
	
	@GetMapping("/register")
	public String showRegistrationForm(Model model)
	{
		model.addAttribute("user", new User());
		
		return "Registration.html";
	}
	
	@PostMapping("/process_register")
	public String processRegister(User user)
	{
		BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
		String encodedPassword = passwordEncoder.encode(user.getPassword());
		user.setPassword(encodedPassword);
		
		userRepo.save(user);
		
		return "register_success";
	}
	
	@GetMapping("/users")
	public String listUsers(Model model)
	{
		List<User> listUsers = userRepo.findAll();
		model.addAttribute("listUsers", listUsers);

		return "users";
	}
	@PostMapping("/Test_confirm")
	public String testAppointment(TransactionApp appt , @AuthenticationPrincipal CustomUserDetails userna)
	{
		appt.setPatient_id(userna.getUsername());
		appt.setType("Test");

		apptrepo.save(appt);

		return "TestApptSuccess.html";
	}


	@PostMapping("/Vac_confirm")
	public String VacAppointment(TransactionApp appt , @AuthenticationPrincipal CustomUserDetails userna)
	{
		appt.setPatient_id(userna.getUsername());
		appt.setType("Vaccination");

		apptrepo.save(appt);

		return "VaccApptSuccess.html";
	}


	@GetMapping("/PHome")
	public String PatientHome(Model model)
	{
		return "PHome";
	}

}
