package com.example.codejava;


import org.hibernate.query.criteria.internal.expression.function.CurrentTimestampFunction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import net.bytebuddy.utility.RandomString;

import javax.mail.MessagingException;
import javax.servlet.http.HttpServletRequest;
import java.io.UnsupportedEncodingException;
import java.util.List;
import java.util.Date;


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

	@Autowired
	private CustomUserDetailsService service;


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
		model.addAttribute("apptselec", new TransactionApp());

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
	public String processRegister(User user, HttpServletRequest request) throws UnsupportedEncodingException, MessagingException {

		service.registerCustomer(user, getSiteURL(request));

		return "register_success";
	}

	private String getSiteURL(HttpServletRequest request) {
		String siteURL = request.getRequestURL().toString();
		return siteURL.replace(request.getServletPath(), "");
	}


	@GetMapping("/verify")
	public String verifyUser(@Param("code") String code) {
		if (service.verify(code))
		{
			return "verify_success";
		}
		else
		{
			return "verify_fail";
		}
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

	@PostMapping("/patientindscrn")
	public String patientindscrn(Model model, TransactionApp apptselec)
	{

		TransactionApp listappdetail = apptrepo.findBytransactionId(apptselec.getId());
		System.out.println(apptselec.getId());

		model.addAttribute("listappdetail", listappdetail);
		return "Patient_IndividualAppointments.html";
	}


	@GetMapping("/PHome")
	public String PatientHome(Model model, @AuthenticationPrincipal CustomUserDetails userna)
	{
		model.addAttribute("user", new User());


		User AUser = userRepo.findByEmail(userna.getUsername());

		model.addAttribute("AUser", AUser);


		return "PHome";
	}

	@PostMapping("/update_register")
	public String updateRegister(User user, Model model, @AuthenticationPrincipal CustomUserDetails userna)
	{

		User AUser = userRepo.findByEmail(userna.getUsername());

		String nm = user.getName();
		String em = user.getEmail();
		String ph = user.getPhone();
		String ad = user.getAddress();
		Long id = user.getId();

		AUser.setName(nm);
		AUser.setEmail(em);
		AUser.setPhone(ph);
		AUser.setAddress(ad);

		System.out.println(user.getName());
		userRepo.updateUser(nm,em,ph,ad,id);

		model.addAttribute("AUser", AUser);
		model.addAttribute("user", new User());


		return "PHome";
	}


}
