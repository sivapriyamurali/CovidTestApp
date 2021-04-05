package com.example.codejava;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
public class AppController {

	@Autowired
	private UserRepository userRepo;

	@Autowired
	private TransactionAppRepository apptrepo;

	@Autowired
	private TestCenterRepository tcrepo;

	@Autowired
	private AppointmentRepository appointmentRepository;


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
		List<TestCenter> tstcntr = tcrepo.findAll();
		model.addAttribute("tstcntr", tstcntr);

//		List<String> vacdate = appointmentRepository.findDistinctvaccineDate();
//		model.addAttribute("vacdate", vacdate);
		return "PVaccine.html";
	}

	@GetMapping("/Test")
	public String Test(Model model)
	{
		model.addAttribute("appt", new TransactionApp());
		List<TestCenter> tests = tcrepo.findAll();
		model.addAttribute("tests", tests);

		return "PTest.html";

	}

	@RequestMapping(value = "/Test/Date", method = RequestMethod.GET)
	public @ResponseBody
	List<String> findAllDates(@RequestParam(value = "TC", required = true) String TestCenter)
	{
		List<String> Datesset = appointmentRepository.findDistinctDate(TestCenter);
		System.out.println(Datesset);
		return Datesset;
	}

	@RequestMapping(value = "/Test/Time", method = RequestMethod.GET)
	public @ResponseBody
	List<String> findAllTimes(@RequestParam(value = "TC", required = true) String TestCenter, @RequestParam(value = "TD", required = true)  String TestDate)
	{
		System.out.println("TC" + TestCenter);
		System.out.println("TD" +  TestDate);

		List<String> Timeset = appointmentRepository.findDistinctTime(TestCenter, TestDate);
		System.out.println(Timeset);
		return Timeset;
	}


	@RequestMapping(value = "/vaccine/Date", method = RequestMethod.GET)
	public @ResponseBody
	List<String> findAllvacDates(@RequestParam(value = "TC", required = true) String TestCenter)
	{
		List<String> vacDatesset = appointmentRepository.findDistinctVacDate(TestCenter);
		System.out.println(vacDatesset);
		return vacDatesset;
	}


	@RequestMapping(value = "/vaccine/Time", method = RequestMethod.GET)
	public @ResponseBody
	List<String> findAllVacTimes(@RequestParam(value = "TC", required = true) String TestCenter, @RequestParam(value = "TD", required = true)  String TestDate)
	{
		System.out.println("TCV" + TestCenter);
		System.out.println("VD" +  TestDate);

		List<String> vacTimeset = appointmentRepository.findDistinctvacTime(TestCenter, TestDate);
		System.out.println(vacTimeset);
		return vacTimeset;
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
