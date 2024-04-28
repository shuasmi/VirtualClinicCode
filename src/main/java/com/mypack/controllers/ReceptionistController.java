package com.mypack.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.mypack.dao.AppointmentDetailsRepo;
import com.mypack.dao.MyOrderRepo;
import com.mypack.dao.MyServiceBro;
import com.mypack.dao.PatientDetailsRepo;
import com.mypack.entities.AppointmentDetails;
import com.mypack.entities.MyOrder;
import com.mypack.entities.PatientDetails;

import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;

@Controller
@RequestMapping("/Receptionist")
public class ReceptionistController {
	
	@Autowired
	private PatientDetailsRepo patientDetailsRepo;
	
	@Autowired
	private AppointmentDetailsRepo appointmentDetailsRepo;
	
	@Autowired
	private MyOrderRepo myOrderRepo;
	
	 @Autowired
	 private JavaMailSender emailSender;
	 
	 private PatientDetails pd;
	 
	 @Autowired
	 private MyServiceBro myServiceBro;
	
	//@PreAuthorize("hasRole('RECEPTIONIST')")
		@RequestMapping("/ReceptionistPage")
		public String receptionist(Model m)
		{
			/*
			m.addAttribute("title","Receptionist-Sai Clinic");
			List<PatientDetails> pd=this.patientDetailsRepo.findAll();
			
			List<MyOrder> mo=this.myOrderRepo.findAll();
			
			
			
			m.addAttribute("MyOrder",mo);
			m.addAttribute("PatientDetails",pd);
			*/
			
			
			
			List<MyOrder> mo=myOrderRepo.findAll();
			//System.out.println(mo);
			
			
			List<PatientDetails> patientsWithOrders = myServiceBro.getAllPatientsWithOrders();
			System.out.println(patientsWithOrders);
			m.addAttribute("PatientDetails",patientsWithOrders);
			return "Receptionist";
			
		}
		
		@PostMapping("/DeleteRow")
		public String deleteRow(HttpServletRequest request)
		{
				

				
		            // Find the PatientDetails object by email
					String patientEmail =request.getParameter("patientEmail");
		            PatientDetails patientDetails = patientDetailsRepo.findByEmail(patientEmail);

		            if (patientDetails != null) {
		            	// Remove the association with MyOrder to prevent cascading deletion
		                patientDetails.getOrder().setPatientDetails(null);
		                // Delete the PatientDetails object from the database
		                patientDetailsRepo.delete(patientDetails);
		                System.out.println("successfully done");
		            } else {
		               System.out.println("Not done");
		            }
			
		            
		            
		         
		            return "redirect:/Receptionist/ReceptionistPage";
		        
		    
		       			
		}
		
		@RequestMapping("/ScheduleAppointment")
		public String scheduleAppointment(Model m)
		{
			m.addAttribute("title","Schedule Appointment-Sai Clinic");
			m.addAttribute("BookAppointment",new AppointmentDetails());
			
			return "ScheduleAppointment";
		}
		
		@PostMapping("/SuccessAppointment")
		public String successAppointment(@Valid @ModelAttribute("BookAppointment")AppointmentDetails ad,BindingResult res,Model m,HttpServletRequest request)
		{
			if(res.hasErrors())
			{
				System.out.println(res);
				return "ScheduleAppointment";
			}
			
			
			
						
			
			
			
			m.addAttribute("title","Success Appointment-Sai Clinic");
			
			AppointmentDetails result=this.appointmentDetailsRepo.save(ad);
			m.addAttribute("BookAppointment",result);
			
			
			
			 String patientName = request.getParameter("patientName");
		     String scheduleTime = request.getParameter("scheduleTime");
		     String consultation = request.getParameter("consultation");
		     String email=request.getParameter("email");
		     
		     sendEmail(patientName, scheduleTime, consultation,email);
			
		     PatientDetails patientDetails = patientDetailsRepo.findByEmail(email);
		     
		     if(patientDetails != null)
		     {
		    	 patientDetails.setAppointmentStatus("Done");
		    	 
		         patientDetailsRepo.save(patientDetails);
		     }
		     //m.addAttribute("formSubmitted", true);
		     
		     // Retrieve updated list of patients with orders
		     //List<PatientDetails> patientsWithOrders = myServiceBro.getAllPatientsWithOrders();
		     //m.addAttribute("PatientDetails", patientsWithOrders);
		     
		     
			return "SuccessAppointment";
		}
		
		
		 private void sendEmail(String patientName, String scheduleTime, String consultation,String email) {
		        MimeMessage message = emailSender.createMimeMessage();
		        MimeMessageHelper helper = new MimeMessageHelper(message);
		        
		        try {
		            helper.setTo(email); // Replace with the customer's email address
		            helper.setSubject("Appointment Confirmation");
		            helper.setText("Dear " + patientName + ",\n\nYour appointment is scheduled for " +
		                    scheduleTime + ".\n\nConsultation Type: " + consultation+",\n\nGo To Start Appointment in Home Page and Enter Following MeetingId:"+patientName);

		            emailSender.send(message);
		        } catch (MessagingException e) {
		            e.printStackTrace();
		            // Handle exception appropriately
		        }
		    }
		    

}
