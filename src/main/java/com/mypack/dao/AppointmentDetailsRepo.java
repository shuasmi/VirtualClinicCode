package com.mypack.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.mypack.entities.AppointmentDetails;

public interface AppointmentDetailsRepo extends JpaRepository<AppointmentDetails, Integer>{
	
	 AppointmentDetails findByPatientName(String patientName);

}
