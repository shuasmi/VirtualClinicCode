package com.mypack.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.mypack.entities.PatientLoginDetails;

public interface PatientLoginDetailsRepo extends JpaRepository<PatientLoginDetails, Integer>{
	
	@Query("select u from PatientLoginDetails u where u.username = :username")
	public PatientLoginDetails getPatientBtUserName(@Param("username") String username);
	
	public PatientLoginDetails findByusername(String uname);
}
