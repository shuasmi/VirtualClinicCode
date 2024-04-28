package com.mypack.entities;

import jakarta.persistence.CascadeType;


import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToOne;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;

@Entity
public class PatientDetails {
	
	@Id
	@Column(unique=true, nullable=false) 
	@GeneratedValue(strategy=GenerationType.AUTO)
	private int srno;
	
	//@NotBlank(message="Patient Name cannot be blank!!")
	@Size(min=4,max=20,message="Patient name must be between 4 to 20 characters!!")
	private String patientName;
	
	//@NotBlank(message="conatct number cnnot be blank")
	@Pattern(regexp="^(\\+\\d{1,2}\\s?)?\\(?\\d{3}\\)?[\\s.-]?\\d{3}[\\s.-]?\\d{4}$",message="Inavlid contact number")
	private String contactNumber;
	
	//@NotBlank(message="email cannot be blank")
	@Email(regexp="^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,}$",message="Inavlid email!!")
	private String email;
	
	@Column(length=1000)
	//@NotBlank(message="This feild cannot be balnk")
	@Size(min=4,message="Medical problem should be atleast of 4 characters")
	private String medicalProblem;
	
	@Column(length=1000)
	@Size(min=4,message="Medical history should be atleast of 4 characters")
	private String medicalHistory;
	
	private String treatment;
	
	private String verificationCode;

	/*
	@ManyToOne
	private PatientLoginDetails pld;
	*/
	
	private String appointmentStatus;
	
	
	@ManyToOne
	private MyOrder order;
	
	
	public PatientDetails() {
		super();
		// TODO Auto-generated constructor stub
	}



	public String getPatientName() {
		return patientName;
	}



	public void setPatientName(String patientName) {
		this.patientName = patientName;
	}



	public String getContactNumber() {
		return contactNumber;
	}



	public void setContactNumber(String contactNumber) {
		this.contactNumber = contactNumber;
	}



	public String getEmail() {
		return email;
	}



	public void setEmail(String email) {
		this.email = email;
	}



	public String getMedicalProblem() {
		return medicalProblem;
	}



	public void setMedicalProblem(String medicalProblem) {
		this.medicalProblem = medicalProblem;
	}



	public String getMedicalHistory() {
		return medicalHistory;
	}



	public void setMedicalHistory(String medicalHistory) {
		this.medicalHistory = medicalHistory;
	}
	
	
	
	

	

	public MyOrder getOrder() {
		return order;
	}



	public void setOrder(MyOrder order) {
		this.order = order;
	}



	public String getTreatment() {
		return treatment;
	}



	public void setTreatment(String treatment) {
		this.treatment = treatment;
	}

	
	

	public String getVerificationCode() {
		return verificationCode;
	}



	public void setVerificationCode(String verificationCode) {
		this.verificationCode = verificationCode;
	}

	

	public String getAppointmentStatus() {
		return appointmentStatus;
	}



	public void setAppointmentStatus(String appointmentStatus) {
		this.appointmentStatus = appointmentStatus;
	}

	

	public int getSrno() {
		return srno;
	}



	public void setSrno(int srno) {
		this.srno = srno;
	}



	@Override
	public String toString() {
		return "PatientDetails [srno=" + srno + ", patientName=" + patientName + ", contactNumber=" + contactNumber
				+ ", email=" + email + ", medicalProblem=" + medicalProblem + ", medicalHistory=" + medicalHistory
				+ ", treatment=" + treatment + ", verificationCode=" + verificationCode + ", appointmentStatus="
				+ appointmentStatus + ", order=" + order + "]";
	}



	

	
	



	
	
	

}
