package com.mypack.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import com.mypack.dao.PatientLoginDetailsRepo;
import com.mypack.entities.PatientLoginDetails;

public class UserDetailServiceImpl implements UserDetailsService{

	@Autowired
	private PatientLoginDetailsRepo detailsRepo;
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		// fetching patient from database
		PatientLoginDetails patient= detailsRepo.getPatientBtUserName(username);
		if(patient==null)
		{
			throw new UsernameNotFoundException("Could not found patient !!");
		}
		CustomUserDetail customUserDetail=new CustomUserDetail(patient);
		return customUserDetail;
	}
	

}
