package com.code.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

@EnableWebSecurity
@Configuration
public class SecurityConfiguration extends WebSecurityConfigurerAdapter {

	
	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {

		//Created user with role for authentication of requests
		auth.inMemoryAuthentication().withUser("john").password("{noop}john").roles("user");

	}

	@Override
	protected void configure(HttpSecurity http) throws Exception {

		//Only authenticated user are authorised to access all the request
		//formLogin() provide default login form 
		http.httpBasic().and().authorizeRequests().anyRequest().authenticated().and().formLogin();

	}

}
