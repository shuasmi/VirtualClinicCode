package com.mypack.controllers;




import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.mypack.dao.AppointmentRepo;
import com.mypack.dao.PatientDetailsRepo;
import com.mypack.dao.PatientLoginDetailsRepo;
import com.mypack.entities.Appointment;
import com.mypack.entities.DoctorDetails;
import com.mypack.entities.PatientDetails;
import com.mypack.entities.PatientLoginDetails;
import com.mypack.helper.Message;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;

@Controller
@RequestMapping("/saiclinic")
public class HomeController{
	
	@Autowired
	private BCryptPasswordEncoder bCryptPasswordEncoder;
	@Autowired
	private PatientLoginDetailsRepo loginDetailsRepo;
	
	@Autowired
	private PatientDetailsRepo patientDetailsRepo;
	/*
	@Autowired
	private PatientLoginDetailsRepo patientLoginDetails;
	
	@Autowired
	private AppointmentRepo appointmentRepo;
	
	
	@GetMapping("/test")
	@ResponseBody
	public String test()
	{
		PatientLoginDetails pd=new PatientLoginDetails();
		
		pd.setUsername("shubham123");
		pd.setPassword("yum");
		
		
		
		patientLoginDetails.save(pd);

		Appointment ap1=new Appointment();
		ap1.setScheduleTime("8:30");
		
		DoctorDetails doctorDetails=new DoctorDetails();
		
		doctorDetails.setDoctorName("Amol Patil");
		doctorDetails.setWrorkingHours("11:00AM to 1:00PM");
		
		ap1.setDd(doctorDetails);
		
		appointmentRepo.save(ap1);
		return "working..";
	}
	*/
	
	@RequestMapping("/home2")
	public String home(Model m)
	{
		/*
		try
		{
			if(result.hasErrors())
			{
				System.out.println(result);
				
			}
			
			if(patient.getUsername().length()<=4)
			{
				throw new Exception("Username must be greater than 4 letter");
			}
			else if(patient.getPassword().length()<=4)
			{
				throw new Exception("Password must be greater than 4 character");
			}
			
			m.addAttribute("title","Home-Sai Virtual Clinic");
			
			
			patient.setRole("ROLE_PATIENT");
			patient.setPassword(bCryptPasswordEncoder.encode(patient.getPassword()));
			System.out.println("Patient : "+patient);
			PatientLoginDetails res= this.loginDetailsRepo.save(patient);
			
			
			m.addAttribute("patient",new PatientLoginDetails());
			session.setAttribute("message", new Message("Successfully Registered !!", "alert-sucess"));
			return "home";
		}
		catch(Exception e)
		{
			e.printStackTrace();
			m.addAttribute("patient",patient);
			session.setAttribute("message", new Message("Something went wrong !!"+e.getMessage(), "alert-danger"));
			return "register";
		}
		*/
		
		m.addAttribute("title","Home-Sai Clinic");
		return "home2";
	}
	

	@RequestMapping("/home")
	public String home2(@Valid @ModelAttribute("Patient") PatientLoginDetails patient,BindingResult result,Model m,HttpSession session)
	{
		
		try
		{
			if(result.hasErrors())
			{
				System.out.println(result);
				
			}
			
			if(patient.getUsername().length()<=4)
			{
				throw new Exception("Username must be greater than 4 letter");
			}
			else if(patient.getPassword().length()<=4)
			{
				throw new Exception("Password must be greater than 4 character");
			}
			
			m.addAttribute("title","Home-Sai Virtual Clinic");
			
			
			patient.setRole("ROLE_PATIENT");
			patient.setPassword(bCryptPasswordEncoder.encode(patient.getPassword()));
			System.out.println("Patient : "+patient);
			PatientLoginDetails res= this.loginDetailsRepo.save(patient);
			
			
			m.addAttribute("patient",new PatientLoginDetails());
			session.setAttribute("message", new Message("Successfully Registered !!", "alert-sucess"));
			return "home";
		}
		catch(Exception e)
		{
			e.printStackTrace();
			m.addAttribute("patient",patient);
			session.setAttribute("message", new Message("Something went wrong !!"+e.getMessage(), "alert-danger"));
			return "Register";
		}
		
		
		
	}
	
	@RequestMapping("/about")
	public String about(Model m)
	{
		m.addAttribute("title","About-Sai Clinic");
		return "about";
		
	}
	
	//@PreAuthorize("hasRole('PATIENT')")
	@RequestMapping("/Patients")
	public String patients(Model m)
	{
		m.addAttribute("title","Patients-Sai Clinic");
		return "Patients";
		
	}
	
	
	
	
	@RequestMapping("/Register")
	public String register(Model m)
	{
		m.addAttribute("title","Register- Sai Virtual Clinic");
		m.addAttribute("Patient",new PatientLoginDetails());
		return "Register";
		
	}
	
	@RequestMapping(value="/PatientDetails",method=RequestMethod.POST)
	public String patientDetails(@Valid @ModelAttribute("Patient") PatientLoginDetails patient,BindingResult result,Model m,HttpSession session)
	{
		
		return "PatientDetails";
		
	}
	
	
	@GetMapping("/login")
	public String customLogin(Model m)
	{
		m.addAttribute("title","Login Page-Sai Clinic");
		
		
		return "login";
	}
	
	@GetMapping("/forgotpassword")
	public String forgotPassword(Model m)
	{
		m.addAttribute("title","Forgot Password-Sai Clinic");
		return "forgotpassword";
	}
	
	@GetMapping("/resetpassword/{id}")
	public String resetpassword(@PathVariable int id,Model m)
	{
		m.addAttribute("title","Reset Password-Sai Clinic");
		m.addAttribute("id",id);
		return "resetpassword";
	}
	
	@GetMapping("/InvalidUsername")
	public String invalidUsername(Model m)
	{
		m.addAttribute("title","Invalid Username-Sai Clinic");
		return "invalidusername";
	}
	
	@GetMapping("/passwordchangesuccess")
	public String passwordChangeSuccess(Model m)
	{
		m.addAttribute("title","Password Change Successfully-Sai Clinic");
		return "passwordchangesuccess";
	}
	
	@PostMapping("/fpassword")
	public String fPassword(@RequestParam String username,HttpSession session)
	{
		PatientLoginDetails user= loginDetailsRepo.findByusername(username);
		
		if(user!=null)
		{
			return "redirect:/saiclinic/resetpassword/"+user.getPatientId();
		}
		else
		{
			session.setAttribute("message","Invalid Username");
			return "invalidusername";
		}
	}
	
	@PostMapping("/changepassword")
	public String changepassword(@RequestParam String nPassword,@RequestParam Integer id,HttpSession session)
	{
		PatientLoginDetails pt= loginDetailsRepo.findById(id).get();
		String encryptPassword=bCryptPasswordEncoder.encode(nPassword);
		
		pt.setPassword(encryptPassword);
		
		PatientLoginDetails updatePatient=loginDetailsRepo.save(pt);
		
		if(updatePatient!=null)
		{
			session.setAttribute("message","PasswordChangeSuccessfully");
		}
		
		return "redirect:/saiclinic/passwordchangesuccess";
	}
	
	
	@GetMapping("/enterotp")
	public String enterOtp()
	{
		return "enterotp";
	}
	
	@GetMapping("/enterotp2")
	public String enterOtp2()
	{
		return "enterotp2";
	}
	
	@GetMapping("/lobby")
	public String videoLobby(@RequestParam(name = "patientName", required = false) String patientName,@RequestParam(name = "meetingId", required = false) String meetingId, Model model)
	{
		try {
			model.addAttribute("patientName", patientName);
			model.addAttribute("meetingId", meetingId);
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		
		return "lobby";
	}
	
	@GetMapping("/video")
	public String videoPage()
	{
		return "vid";
	}
	
	
}
