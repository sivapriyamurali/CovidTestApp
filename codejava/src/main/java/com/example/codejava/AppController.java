package com.example.codejava;



import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import javax.mail.MessagingException;
import javax.servlet.http.HttpServletRequest;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
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

	@Autowired
	private CustomUserDetailsService service;


	@GetMapping("/login")
	public String login()
	{
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		if (authentication == null || authentication instanceof AnonymousAuthenticationToken)
		{
			return "PLogin.html";
		}

		else
		{
			return "Patient_HomePage";
		}

	}


	@GetMapping("/Appointments")
	public String Appointments(Model model, @AuthenticationPrincipal CustomUserDetails userna)
	{
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		if (authentication == null || authentication instanceof AnonymousAuthenticationToken)
		{
			return "PLogin.html";
		}

		else
		{
			List<TransactionApp> listAppsPast = apptrepo.findByNamePastAppointments(userna.getUsername());
			List<TransactionApp> listAppsFuture = apptrepo.findByNameFutureAppointments(userna.getUsername());

			model.addAttribute("listAppsPast", listAppsPast);
			model.addAttribute("listAppsFuture", listAppsFuture);
			model.addAttribute("apptselec", new TransactionApp());

			return "Appointments";
		}


	}



	@GetMapping("/Vaccine")
	public String Vaccine(Model model,  @AuthenticationPrincipal CustomUserDetails userna)
	{
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		if (authentication == null || authentication instanceof AnonymousAuthenticationToken)
		{
			return "PLogin.html";
		}

		else
		{
			TransactionApp checkVaccineappt = apptrepo.findByVaccineFutureAppointments(userna.getUsername());
			boolean isexist;

			if(checkVaccineappt == null)
			{
				isexist = false;
			}
			else
			{
				isexist = true;
			}
			model.addAttribute("isexist", isexist);

			List<TestCenter> tstcntr = tcrepo.findAll();

			model.addAttribute("tstcntr", tstcntr);

			model.addAttribute("appt", new TransactionApp());

			return "PVaccine.html";
		}


	}

	@GetMapping("/Test")
	public String Test(Model model, @AuthenticationPrincipal CustomUserDetails userna)
	{
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		if (authentication == null || authentication instanceof AnonymousAuthenticationToken)
		{
			return "PLogin.html";
		}

		else
		{
			TransactionApp checkApptExist = apptrepo.findByTestFutureAppointments(userna.getUsername());
			boolean isexist;


			/* If there is an no future Appointment */
			if( checkApptExist == null)
			{
				isexist = false;
			}
			/* If there is an future Appointment */
			else
			{
				isexist = true;
			}

			model.addAttribute("isexist", isexist);

			List<TestCenter> tests = tcrepo.findAll();

			model.addAttribute("tests", tests);

			model.addAttribute("appt", new TransactionApp());
			return "PTest.html";

		}


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
		List<String> Timesetfinal= new ArrayList<>();
		int numberofslots;



		List<String> time = appointmentRepository.findDistinctTestTime(TestCenter, TestDate);
		System.out.println(time);
		System.out.println(time.get(0));

		long sl[] = new long[time.size()];

		for (int i = 0; i < time.size(); i++)
		{
			sl[i] = appointmentRepository.findDistinctTestSlot(TestCenter, TestDate,time.get(i));
			numberofslots = apptrepo.CountSlots(TestCenter, TestDate,time.get(i));

			if(numberofslots < sl[i])
			{
				Timesetfinal.add(time.get(i));
			}
			else
			{

			}

		}
		return Timesetfinal;
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
		List<String> Timesetfinal= new ArrayList<>();
		int numberofslots;

		List<String> time = appointmentRepository.findDistinctvacTime(TestCenter, TestDate);
		System.out.println(time);
		System.out.println(time.get(0));

		long sl[] = new long[time.size()];

		for (int i = 0; i < time.size(); i++)
		{
			sl[i] = appointmentRepository.findDistinctVaccineSlot(TestCenter, TestDate,time.get(i));
			numberofslots = apptrepo.CountSlotsVac(TestCenter, TestDate,time.get(i));

			if(numberofslots < sl[i])
			{
				Timesetfinal.add(time.get(i));
			}
			else
			{

			}

		}
		return Timesetfinal;
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
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		if (authentication == null || authentication instanceof AnonymousAuthenticationToken)
		{
			return "PLogin.html";
		}
		else
		{
			model.addAttribute("user", new User());

			User AUser = userRepo.findByEmail(userna.getUsername());

			model.addAttribute("AUser", AUser);

			return "PHome";
		}

	}

	@GetMapping("/Patient_HomePage")
	public String PatientHomePage()
	{
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		if (authentication == null || authentication instanceof AnonymousAuthenticationToken)
		{
			return "PLogin.html";
		}

		else
		{
			return "Patient_HomePage";
		}

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

	@GetMapping("/delete_appointment")
	public String deleteAppointment(TransactionApp trans, Model model, @AuthenticationPrincipal CustomUserDetails userna)
	{

		System.out.println(trans.getId());
		System.out.println(trans.getReport());
		apptrepo.deleteBytransactionId(trans.getId());


		List<TransactionApp> listAppsPast = apptrepo.findByNamePastAppointments(userna.getUsername());
		List<TransactionApp> listAppsFuture = apptrepo.findByNameFutureAppointments(userna.getUsername());

		model.addAttribute("listAppsPast", listAppsPast);
		model.addAttribute("listAppsFuture", listAppsFuture);
		model.addAttribute("apptselec", new TransactionApp());
		return "Appointments";
	}


}
