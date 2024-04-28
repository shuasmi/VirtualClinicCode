package com.mypack.dao;

import java.util.List;
import java.util.Random;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import com.mypack.entities.BlogPost;
import com.mypack.entities.MyOrder;
import com.mypack.entities.PatientDetails;

@Service
public class MyServiceBro {
	
	@Autowired
	private  BlogPostRepo blogPostRepo;
	
	@Autowired
	private PatientDetailsRepo patientDetailsRepo;
	
	@Autowired
	private JavaMailSender javaMailSender;

    public Page<BlogPost> getPaginatedData(int pageNumber, int pageSize) {
        PageRequest pageRequest = PageRequest.of(pageNumber, pageSize);
        return blogPostRepo.findAll(pageRequest);
    }

    
    /*
    public void sendVerificationCode(String email) {
        // Generate a unique verification code (e.g., UUID)
        String verificationCode = UUID.randomUUID().toString();

        // Save the verification code in the database along with the user's email
        patientDetailsRepo.saveVerificationCode(email, verificationCode);

        // Send the verification code via email
        sendEmail(email, "Verification Code", "Your verification code is: " + verificationCode);
    }
   */
    
    public void sendVerificationCode(String email) {
        // Generate a 4-digit verification code
        String verificationCode = generateVerificationCode();

        // Save the verification code in the database along with the user's email
        patientDetailsRepo.saveVerificationCode(email, verificationCode);

        // Send the verification code via email
        sendEmail(email, "Verification Code", "Your verification code is: " + verificationCode);
    }

    private String generateVerificationCode() {
        Random random = new Random();
        int code = random.nextInt(9000) + 1000; // Generates a random number between 1000 and 9999
        return String.valueOf(code);
    }

      
    private void sendEmail(String to, String subject, String body) {
        SimpleMailMessage message = new SimpleMailMessage();
        message.setTo(to);
        message.setSubject(subject);
        message.setText(body);
        javaMailSender.send(message);
    }
    
    public boolean verifyCode(String email, String enteredCode) {
        // Retrieve the user by email
    	try
    	{
    		PatientDetails user = patientDetailsRepo.findByEmail(email);
    		if (user == null) {
                return false; // User with given email not found
            }

            // Get the verification code stored in the database for the user
            String storedCode = user.getVerificationCode();
            return enteredCode.equals(storedCode);
    	}
    	catch(Exception e)
    	{
    		e.printStackTrace();
    		return false;
    	}
        

        // Compare the entered code with the stored code
        
    }
    
    public List<PatientDetails> getAllPatientsWithOrders() {
        // Fetch all patients along with their orders
        List<PatientDetails> patients = patientDetailsRepo.findAll();
        // Iterate over patients and check/order status
        for (PatientDetails patient : patients) {
            MyOrder order = patient.getOrder(); // Assuming getOrder() method exists in PatientDetails
            if (order != null) {
                String status = order.getStatus();
                // Do something with status if needed
            }
        }
        return patients;
    }

}
