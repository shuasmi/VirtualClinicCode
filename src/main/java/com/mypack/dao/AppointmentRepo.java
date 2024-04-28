package com.mypack.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.mypack.entities.Appointment;

public interface AppointmentRepo extends JpaRepository<Appointment, Integer>{
	

}
