package com.tyy.security;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.provisioning.JdbcUserDetailsManager;


@Configuration
@EnableWebSecurity
public class SecurityConfiguration extends WebSecurityConfigurerAdapter {
	
	@Autowired
	@Qualifier("springDataSource")
	private DataSource springDataSource;
	
	@Autowired
	private UserDetailsService userService;
	
	@Bean
	public JdbcUserDetailsManager jdbcUserDetailsManager() throws Exception{
		JdbcUserDetailsManager jdbcUserDetailsManager = new JdbcUserDetailsManager(springDataSource);
		return jdbcUserDetailsManager;
	}
	
	
	@Bean
	public BCryptPasswordEncoder passCodeEncoder() {
		return new BCryptPasswordEncoder();
	}
	
	
	
	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		auth
			.userDetailsService(userService)
			.passwordEncoder(this.passCodeEncoder())
			.and()
			.jdbcAuthentication()
			.dataSource(springDataSource)
			.passwordEncoder(this.passCodeEncoder());
	}


	@Override
	protected void configure(HttpSecurity http) throws Exception {
		// TODO Auto-generated method stub
		http.authorizeRequests()
		.antMatchers("/system/**").hasAnyRole("ADMIN","MANAGER","EMPLOYEE")
		.and()
		.formLogin()
			.loginPage("/login")
			.loginProcessingUrl("/authenticateTheUser")
			.permitAll()
		.and()
		.logout().permitAll()
		.and()
		.exceptionHandling().accessDeniedPage("/access-denied");
	}
}
