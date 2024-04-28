package com.mypack.entities;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;

@Entity
public class Appointment {
	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private int AppointmentId;
	
	private String scheduleTime;
	/*
	@ManyToOne
	private Appointment pid;
	*/
	
	private DoctorDetails dd;
	
	public Appointment() {
		super();
		// TODO Auto-generated constructor stub
	}

	public String getScheduleTime() {
		return scheduleTime;
	}

	public void setScheduleTime(String scheduleTime) {
		this.scheduleTime = scheduleTime;
	}

	public int getAppointmentId() {
		return AppointmentId;
	}

	public void setAppointmentId(int appointmentId) {
		AppointmentId = appointmentId;
	}

	

	public DoctorDetails getDd() {
		return dd;
	}

	public void setDd(DoctorDetails dd) {
		this.dd = dd;
	}
	
	
	

}
