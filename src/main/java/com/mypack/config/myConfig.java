package com.mypack.config;

import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.web.server.ServerHttpSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.DefaultSecurityFilterChain;
import org.springframework.security.web.SecurityFilterChain;


@Configuration
@EnableWebSecurity
//@EnableMethodSecurity
public class myConfig{
	
	@Bean
	public BCryptPasswordEncoder passwordEncoder()
	{
		return new BCryptPasswordEncoder();
	}
	@Bean
	public UserDetailsService userDetailsService()
	{
		/*
		UserDetails patient=User
				.withUsername("shubham")
				.password(passwordEncoder().encode("pass"))
				.roles("PATIENT")
				.build();
		
		UserDetails receptionist=User
				.withUsername("shakal")
				.password(passwordEncoder().encode("shakal1234"))
				.roles("RECEPTIONIST")
				.build();
		
		InMemoryUserDetailsManager inMemoryUserDetailsManager=new InMemoryUserDetailsManager(patient,receptionist);
		return inMemoryUserDetailsManager;
		*/
		
		return new UserDetailServiceImpl();
	}
	
	@Bean
	public DaoAuthenticationProvider daoAuthenticationProvider()
	{
		DaoAuthenticationProvider provider=new DaoAuthenticationProvider();
		provider.setUserDetailsService(this.userDetailsService());
		provider.setPasswordEncoder(passwordEncoder());
		return provider;
	}
	
	@Bean
	public AuthenticationManager authenticationManager(AuthenticationConfiguration configuration)throws Exception
	{
		return configuration.getAuthenticationManager();
	}
	/*
	@Bean
	public FilterRegistrationBean<ContentTypeOptionsFilter> contentTypeOptionsFilter()
	{
		 FilterRegistrationBean<ContentTypeOptionsFilter> registrationBean = new FilterRegistrationBean<>();
		 registrationBean.setFilter(new ContentTypeOptionsFilter());
		 registrationBean.addUrlPatterns("/*");
		 return registrationBean;
	}
	*/
	
	@Bean
	public SecurityFilterChain securityFilterChain(HttpSecurity http)throws Exception
	{
		
		http.csrf().disable()
			.cors().disable()
			.authorizeHttpRequests()
			.requestMatchers("/css/**", "/img/**", "/js/**").permitAll()
			.requestMatchers("/Receptionist/**")
			.hasRole("RECEPTIONIST")
			.requestMatchers("/Doctors/**")
			.hasRole("DOCTOR")
			.requestMatchers("/Patient/**")
			.hasRole("PATIENT")
			.requestMatchers("/saiclinic/**","/email/**")
			.permitAll()
			.anyRequest()
			.authenticated()
			.and()
			.formLogin().loginPage("/saiclinic/login").defaultSuccessUrl("/saiclinic/home2");
		
		return http.build();
		
		/*
		return http .csrf(csrf -> csrf.disable())
				.authorizeRequests(auth -> { auth.requestMatchers("/saiclinic/**").permitAll(); auth.requestMatchers("/Patient/**").hasRole("PATIENT"); auth.requestMatchers("/saiclinic/Receptionist").hasRole("RECEPTIONIST")
				.anyRequest()
				.authenticated()
				.and()
				.formLogin().loginPage("/saiclinic/login"); })
				.build(); }
				*/
}
	
}
	/*
	@Bean
	public UserDetailsService getUserDetailsService()
	{
		return new UserDetailServiceImpl();
	}
	
	@Bean
	public BCryptPasswordEncoder passwordEncoder()
	{
		return new BCryptPasswordEncoder();
	}
	
	@Bean
	public DaoAuthenticationProvider daoAuthenticationProvider()
	{
		DaoAuthenticationProvider provider=new DaoAuthenticationProvider();
		provider.setUserDetailsService(this.getUserDetailsService());
		provider.setPasswordEncoder(passwordEncoder());
		return provider;
	}
	
	//configure method....
	
	@Bean
	public AuthenticationManager authenticationManager(AuthenticationConfiguration configuration)throws Exception
	{
		return configuration.getAuthenticationManager();
	}
	*/

