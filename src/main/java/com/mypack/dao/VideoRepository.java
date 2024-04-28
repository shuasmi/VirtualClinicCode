package com.mypack.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.mypack.entities.Video;

public interface VideoRepository extends JpaRepository<Video, Integer>{
	
	

}
