package com.mypack.dao;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import com.mypack.entities.Video;

@Service
public class VideoService {
	
	@Autowired
	private VideoRepository videoRepository;
	
	public List<Video> getAllVideos()
	{
		return videoRepository.findAll();
	}
	
	public Page<Video> getPagiData(int pageNumber, int pageSize)
	{
		PageRequest pageRequest=PageRequest.of(pageNumber, pageSize);
		return videoRepository.findAll(pageRequest);
	}
}
