package com.mypack.entities;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.validation.constraints.Size;

@Entity
public class PrescriptionDetails {

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private int id;
	
	@Size(min=4,max=20,message="Patient name must be between 4 to 20 characters!!")
	private String patientName;
	
	@Column(length=1000)
	//@NotBlank(message="This feild cannot be balnk")
	@Size(min=4,message="Medicenes should be atleast of 4 characters")
	private String Medicines;

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

	public String getMedicines() {
		return Medicines;
	}

	public void setMedicines(String medicines) {
		Medicines = medicines;
	}
	
	
}
