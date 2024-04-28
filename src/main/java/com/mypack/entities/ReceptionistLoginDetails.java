package com.mypack.entities;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class ReceptionistLoginDetails {
	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private int receptionistId;
	
	private String receptionistUsername;
	
	private String receptionistPassword;

	public ReceptionistLoginDetails() {
		super();
		// TODO Auto-generated constructor stub
	}

	public int getReceptionistId() {
		return receptionistId;
	}

	public void setReceptionistId(int receptionistId) {
		this.receptionistId = receptionistId;
	}

	public String getReceptionistUsername() {
		return receptionistUsername;
	}

	public void setReceptionistUsername(String receptionistUsername) {
		this.receptionistUsername = receptionistUsername;
	}

	public String getReceptionistPassword() {
		return receptionistPassword;
	}

	public void setReceptionistPassword(String receptionistPassword) {
		this.receptionistPassword = receptionistPassword;
	}
	
	

}
