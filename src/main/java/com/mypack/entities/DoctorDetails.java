package com.mypack.entities;

import jakarta.persistence.Embeddable;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Embeddable
public class DoctorDetails {
	
	
	
	private String doctorName;
	
	private String wrorkingHours;

	public DoctorDetails() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	

	public DoctorDetails(String doctorName, String wrorkingHours) {
		super();
		this.doctorName = doctorName;
		this.wrorkingHours = wrorkingHours;
	}



	public String getDoctorName() {
		return doctorName;
	}

	public void setDoctorName(String doctorName) {
		this.doctorName = doctorName;
	}

	public String getWrorkingHours() {
		return wrorkingHours;
	}

	public void setWrorkingHours(String wrorkingHours) {
		this.wrorkingHours = wrorkingHours;
	}
	
	

}
