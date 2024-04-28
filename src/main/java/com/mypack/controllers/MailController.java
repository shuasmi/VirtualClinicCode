package com.mypack.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.mypack.dao.MyServiceBro;
import com.mypack.dao.PatientDetailsRepo;
import com.mypack.entities.PatientDetails;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/email")
public class MailController {
	
	@Autowired
	private MyServiceBro myServiceBro;
	

	@Autowired
	private PatientDetailsRepo patientDetailsRepo;
	
	/*
	 @PostMapping("/verify")
	 public ResponseEntity<String> verifyEmail(@RequestParam String email,@Valid @ModelAttribute("PatientDetails")PatientDetails pd,BindingResult res,Model m) {
		 
		 if(res.hasErrors())
			{
				System.out.println(res);
				
				String AyurConsultUrl = ServletUriComponentsBuilder.fromCurrentContextPath()
		                .path("/Patient/AyurvedicConsultation2")
		                .toUriString();
		        
		        // Return a redirect response to enterOtpUrl with status FOUND (302)
		        return ResponseEntity.status(HttpStatus.FOUND)
		                .header("Location", AyurConsultUrl)
		                .build();
				
			}
			
		 

			m.addAttribute("title","Make Payment-Sai Clinic");
			pd.setTreatment("Ayurvedic");
			System.out.println(pd);
			PatientDetails result= this.patientDetailsRepo.save(pd);
			m.addAttribute("PatientDetails",result);   
		 myServiceBro.sendVerificationCode(email);
	     // HTML content for the response
	        
	        //String htmlResponse = "<!DOCTYPE html>"
	               // + "<html>"
	               // + "<head>"
	               // + "<title>Verification</title>"
	                //+ "</head>"
					//+ "<body>"
	                //+ "<h1>Verification Code Sent</h1>"
	                //+ "<p>A verification code has been sent to your email address.</p>"
	                //+"<a href=\"/enterotp.html\">Enter otp</a>"
	                //+ "</body>"
	                //+ "</html>";

	        // Return HTML content as a ResponseEntity with status OK
	        return ResponseEntity.ok(htmlResponse);
	        
	        // Build the URL for the enterotp endpoint
	        String enterOtpUrl = ServletUriComponentsBuilder.fromCurrentContextPath()
	                .path("/saiclinic/enterotp")
	                .toUriString();
	        
	        // Return a redirect response to enterOtpUrl with status FOUND (302)
	        return ResponseEntity.status(HttpStatus.FOUND)
	                .header("Location", enterOtpUrl)
	                .build();
	    }
	 */
	
	@PostMapping("/verify")
	public ResponseEntity<String> verifyEmail(@RequestParam String email, @Valid @ModelAttribute("PatientDetails") PatientDetails pd, BindingResult res, Model m) {
	    if (res.hasErrors()) {
	        System.out.println(res);

	        // Redirect to AyurvedicConsultation2 page with status FOUND (302)
	        String AyurConsultUrl = ServletUriComponentsBuilder.fromCurrentContextPath()
	                .path("/Patient/AyurvedicConsultation2")
	                .toUriString();
	        return ResponseEntity.status(HttpStatus.FOUND)
	                .header("Location", AyurConsultUrl)
	                .build();
	    }

	    m.addAttribute("title", "Make Payment-Sai Clinic");
	    pd.setTreatment("Ayurvedic");
	    System.out.println(pd);
	    PatientDetails result = this.patientDetailsRepo.save(pd);
	    m.addAttribute("PatientDetails", result);
	    myServiceBro.sendVerificationCode(email);

	    // Build the URL for the enterotp endpoint with error parameter
	    String enterOtpUrl = ServletUriComponentsBuilder.fromCurrentContextPath()
	            .path("/saiclinic/enterotp2")
	            .queryParam("error", "invalid")
	            .toUriString();

	    // Return a redirect response to enterOtpUrl with status FOUND (302)
	    return ResponseEntity.status(HttpStatus.FOUND)
	            .header("Location", enterOtpUrl)
	            .build();
	}

	/*
	 @PostMapping("/verify2")
	 public ResponseEntity<String> verifyEmail2(@RequestParam String email,@Valid @ModelAttribute("PatientDetails")PatientDetails pd,BindingResult res,Model m) {
		 
		 if(res.hasErrors())
			{
				System.out.println(res);
				
				String AyurConsultUrl = ServletUriComponentsBuilder.fromCurrentContextPath()
		                .path("/Patient/AlopathyConsultation2")
		                .toUriString();
		        
		        // Return a redirect response to enterOtpUrl with status FOUND (302)
		        return ResponseEntity.status(HttpStatus.FOUND)
		                .header("Location", AyurConsultUrl)
		                .build();
				
			}
			
		 

			m.addAttribute("title","Make Payment-Sai Clinic");
			pd.setTreatment("Alopathy");
			System.out.println(pd);
			PatientDetails result= this.patientDetailsRepo.save(pd);
			m.addAttribute("PatientDetails",result);   
			myServiceBro.sendVerificationCode(email);
	     // HTML content for the response
	        
	        //String htmlResponse = "<!DOCTYPE html>"
	               // + "<html>"
	               // + "<head>"
	               // + "<title>Verification</title>"
	               // + "</head>"
	               // + "<body>"
	               // + "<h1>Verification Code Sent</h1>"
	               // + "<p>A verification code has been sent to your email address.</p>"
	               // +"<a href=\"/enterotp.html\">Enter otp</a>"
	               // + "</body>"
	                //+ "</html>";

	        // Return HTML content as a ResponseEntity with status OK
	       // return ResponseEntity.ok(htmlResponse);
	        
	        // Build the URL for the enterotp endpoint
	        String enterOtpUrl = ServletUriComponentsBuilder.fromCurrentContextPath()
	                .path("/saiclinic/enterotp")
	                .toUriString();
	        
	        // Return a redirect response to enterOtpUrl with status FOUND (302)
	        return ResponseEntity.status(HttpStatus.FOUND)
	                .header("Location", enterOtpUrl)
	                .build();
	    }
	 */
	
	@PostMapping("/verify2")
	public ResponseEntity<String> verifyEmail2(@RequestParam String email, @Valid @ModelAttribute("PatientDetails") PatientDetails pd, BindingResult res, Model m) {
	    if (res.hasErrors()) {
	        System.out.println(res);

	        // Redirect to AlopathyConsultation2 page with status FOUND (302)
	        String AyurConsultUrl = ServletUriComponentsBuilder.fromCurrentContextPath()
	                .path("/Patient/AlopathyConsultation2")
	                .toUriString();
	        return ResponseEntity.status(HttpStatus.FOUND)
	                .header("Location", AyurConsultUrl)
	                .build();
	    }

	    m.addAttribute("title", "Make Payment-Sai Clinic");
	    pd.setTreatment("Alopathy");
	    System.out.println(pd);
	    PatientDetails result = this.patientDetailsRepo.save(pd);
	    m.addAttribute("PatientDetails", result);
	    myServiceBro.sendVerificationCode(email);

	    // Build the URL for the enterotp2 endpoint with error parameter
	    String enterOtpUrl = ServletUriComponentsBuilder.fromCurrentContextPath()
	            .path("/saiclinic/enterotp2")
	            .queryParam("error", "invalid")
	            .toUriString();

	    // Return a redirect response to enterOtpUrl with status FOUND (302)
	    return ResponseEntity.status(HttpStatus.FOUND)
	            .header("Location", enterOtpUrl)
	            .build();
	}

	/*
	 @PostMapping("/confirm")
	 public ResponseEntity<String> confirmEmailVerification(@RequestParam String email, @RequestParam String code) {
	     boolean verificationSuccessful =myServiceBro.verifyCode(email, code);

	     if (verificationSuccessful) {
	    	 String MakePaymentUrl = ServletUriComponentsBuilder.fromCurrentContextPath()
		                .path("/Patient/MakePayment")
		                .toUriString();
		        
		        // Return a redirect response to enterOtpUrl with status FOUND (302)
		        return ResponseEntity.status(HttpStatus.FOUND)
		                .header("Location", MakePaymentUrl)
		                .build();
	         //return ResponseEntity.ok("Email verification successful");
	     } else {
	    	 String EnterOtpUrl = ServletUriComponentsBuilder.fromCurrentContextPath()
		                .path("/saiclinic/enterotp2")
		                .toUriString();
		        
		        // Return a redirect response to enterOtpUrl with status FOUND (302)
		        return ResponseEntity.status(HttpStatus.FOUND)
		                .header("Location", EnterOtpUrl)
		                .build();
	         //return ResponseEntity.badRequest().body("Invalid verification code");
	     }
	 }
	 */
	
	@PostMapping("/confirm")
	public ResponseEntity<String> confirmEmailVerification(@RequestParam String email, @RequestParam String code) {
	    boolean verificationSuccessful = myServiceBro.verifyCode(email, code);
	    String makePaymentUrl = "/Patient/MakePayment";
	    String enterOtpUrl = "/saiclinic/enterotp2";

	    if (verificationSuccessful) {
	        return ResponseEntity.status(HttpStatus.FOUND)
	                .header("Location", makePaymentUrl)
	                .build();
	    } else {
	        return ResponseEntity.status(HttpStatus.FOUND)
	                .header("Location", enterOtpUrl + "?error=invalid")
	                .build();
	    }
	}

	 

}
