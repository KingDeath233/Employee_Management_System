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
			.dataSource(springDataSource);
	}

	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http.authorizeRequests()
			.antMatchers("/login","/register","/process-register").permitAll()
			.antMatchers("/admin/**").hasAnyRole("ADMIN")
			.antMatchers("/manager/**").hasAnyRole("ADMIN","MANAGER")
			.antMatchers("/employee/**").hasAnyRole("ADMIN","MANAGER","EMPLOYEE")
			//.anyRequest().authenticated()
			.and()
		.formLogin()
			.loginPage("/login")
            .usernameParameter("username")
            .passwordParameter("password")
			.loginProcessingUrl("/authenticateTheUser")
			.and()
		.logout()
			.logoutUrl("/logout")
			.logoutSuccessUrl("/login?logout")
			.and()
		.exceptionHandling()
			.accessDeniedPage("/access-denied")
			.and()
        .httpBasic()
        	.disable();
	}
}
