package com.mypack.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.mypack.entities.PrescriptionDetails;

public interface PrescriptionDetailsRepo extends JpaRepository<PrescriptionDetails, Integer>{

}
