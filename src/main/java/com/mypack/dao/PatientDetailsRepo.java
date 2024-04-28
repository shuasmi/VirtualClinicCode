package com.mypack.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.mypack.entities.PatientDetails;

import jakarta.transaction.Transactional;


public interface PatientDetailsRepo extends JpaRepository<PatientDetails, Integer>{
	
	public PatientDetails findByEmail(String email);
	
	 @Transactional
	 @Modifying
	 @Query("UPDATE PatientDetails u SET u.verificationCode = :verificationCode WHERE u.email = :email")
	 void saveVerificationCode(String email, String verificationCode);
	 
	 
	 public PatientDetails findByVerificationCode(String verificationCode);
	 
	 public PatientDetails findByPatientName(String patientName);
	 
}
