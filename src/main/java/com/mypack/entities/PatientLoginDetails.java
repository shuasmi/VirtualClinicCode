package com.mypack.entities;

import java.util.ArrayList;

import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

@Entity
public class PatientLoginDetails {
	
	

	@Id
	@Column(unique=true, nullable=false) 
	@GeneratedValue(strategy=GenerationType.AUTO)
	private int patientId;
	
	@NotBlank(message="Username cannot be empty!!")
	@Size(min=5,max=20,message="Username must be between 5-20 characters")
	private String username;
	
	@NotBlank(message="Password cannot be empty!!")
	@Size(min=5,message="Password must be between 5-20 characters")
	private String password;
	
	
	@OneToMany(cascade=CascadeType.ALL,fetch=FetchType.EAGER,mappedBy="patient")
	private List<MyOrder> pd=new ArrayList<>();
	
	
	private String role;
	
	
	public int getPatientId() {
		return patientId;
	}

	public void setPatientId(int patientId) {
		this.patientId = patientId;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}
	
	
	public String getRole() {
		return role;
	}

	public void setRole(String role) {
		this.role = role;
	}

	
	
	
	

	public List<MyOrder> getPd() {
		return pd;
	}

	public void setPd(List<MyOrder> pd) {
		this.pd = pd;
	}

	@Override
	public String toString() {
		return "PatientLoginDetails [patientId=" + patientId + ", username=" + username + ", password=" + password
				+"]";
	}
	

	

}
