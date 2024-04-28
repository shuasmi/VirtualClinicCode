package com.mypack.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.mypack.dao.AppointmentDetailsRepo;
import com.mypack.dao.PatientDetailsRepo;
import com.mypack.dao.PrescriptionDetailsRepo;
import com.mypack.entities.AppointmentDetails;
import com.mypack.entities.PatientDetails;
import com.mypack.entities.PrescriptionDetails;

import jakarta.mail.internet.MimeMessage;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;

import java.util.List;
@Controller
@RequestMapping("/Doctors")
public class DoctorsController {
	
	@Autowired
	private AppointmentDetailsRepo appointmentDetailsRepo;
	
	@Autowired
	private PrescriptionDetailsRepo prescriptionDetailsRepo;
	
	@Autowired
	private PatientDetailsRepo patientDetailsRepo;
	
	 @Autowired
	 private JavaMailSender emailSender;
	
	@RequestMapping("/DoctorsPage")
	public String doctorPage(Model m)
	{
		m.addAttribute("title","Doctors Page - Sai Clinic");
		List<AppointmentDetails> ad=this.appointmentDetailsRepo.findAll();
		
		m.addAttribute("AppointmentDetails",ad);
		return "DoctorsPage";
	}
	/*
	@GetMapping("/lobby")
	public String videoLobby(@RequestParam(name = "patientName", required = false) String patientName, Model model)
	{
		model.addAttribute("patientName", patientName);
		
		return "lobby";
	}
	*/
	@GetMapping("/Prescription")
	public String prescription(Model m)
	{
		m.addAttribute("title","Prescription -Sai Clinic");
		m.addAttribute("PrescriptionDetails",new PrescriptionDetails());
		return "Prescription";
	}
	
	@PostMapping("/PrescriptionDetails")
	public String prescriptionDetails(@Valid @ModelAttribute("PrescriptionDetails")PrescriptionDetails pd,BindingResult res,Model m,HttpServletRequest request)
	{
		if(res.hasErrors())
		{
			System.out.println(res);
			return "Prescription";
		}
		
		m.addAttribute("title","Prescription Details-Sai Clinic");
		
		PrescriptionDetails result=this.prescriptionDetailsRepo.save(pd);
		m.addAttribute("PrescriptionDetails",result);
		
		String name=request.getParameter("patientName");
		String medicine=request.getParameter("Medicines");
		
		AppointmentDetails appointD=appointmentDetailsRepo.findByPatientName(name);
		
		if(appointD!=null)
		{
			appointD.setPrescriptionStatus("send");
			appointmentDetailsRepo.save(appointD);
		}
		
		sendEmail(name, medicine);
		
		return "redirect:/Doctors/DoctorsPage";
	}
	
	private void sendEmail(String patientName,String medicine)
	{
		 MimeMessage message = emailSender.createMimeMessage();
	     MimeMessageHelper helper = new MimeMessageHelper(message);
	     
	     try
	     {
	    	 PatientDetails pd=this.patientDetailsRepo.findByPatientName(patientName);
	    	 
	    	 if(pd!=null)
	    	 {
	    		 String email=pd.getEmail();
	    		 helper.setTo(email); // Replace with the customer's email address
		            helper.setSubject("Doctors Prescription");
		            
		            String emailBody = "Dear " + patientName + ",\n\n";
		            emailBody += "Below is your prescription from the doctor:\n\n";
		            emailBody += "Medicines: " + medicine + "\n\n";
		            emailBody += "Please follow the prescription as advised.\n\n";
		            emailBody += "Thank you.\n";
		            
		            helper.setText(emailBody);

		            emailSender.send(message);
	    	 }
	     }
	     catch(Exception e)
	     {
	    	 e.printStackTrace();
	     }
	}
	
	@PostMapping("/deleteAppointment")
	public String deleteAppointment(HttpServletRequest request)
	{
		String patientName=request.getParameter("patientName");
		AppointmentDetails ad=appointmentDetailsRepo.findByPatientName(patientName);
		
		if(ad!=null)
		{
			appointmentDetailsRepo.delete(ad);
			System.out.println("Successfully Deleted ....");
		}
		else
		{
			System.out.println("Not deleted ....");
		}
		
		
		return "redirect:/Doctors/DoctorsPage";
	}
	
}
