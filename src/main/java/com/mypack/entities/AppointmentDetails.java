package com.mypack.entities;

import jakarta.persistence.Entity;

import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;

@Entity
public class AppointmentDetails {
	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private int id;
	
	@NotBlank(message="PatientName cannot be blank")
	@Size(min=3,max=20,message="patient name should be between 3 to 20")
	private String patientName;
	
	@NotBlank(message="ScheduleTime cannot be blank")
	@Pattern(regexp = "^(2[0-3]|[01]?[0-9]):([0-5]?[0-9])$", message = "Please provide a valid time in HH:mm format")
	private String scheduleTime;
	
	
	private String consultation;
	
	@NotBlank(message="email cannot be blank")
	@Email(regexp="^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,}$",message="Inavlid email!!")
	private String email;
	
	private String prescriptionStatus;
	
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getPatientName() {
		return patientName;
	}

	public void setPatientName(String patientName) {
		this.patientName = patientName;
	}

	public String getScheduleTime() {
		return scheduleTime;
	}

	public void setScheduleTime(String scheduleTime) {
		this.scheduleTime = scheduleTime;
	}

	public String getConsultation() {
		return consultation;
	}

	public void setConsultation(String consultation) {
		this.consultation = consultation;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPrescriptionStatus() {
		return prescriptionStatus;
	}

	public void setPrescriptionStatus(String prescriptionStatus) {
		this.prescriptionStatus = prescriptionStatus;
	}
	
	
	
	

}
