package com.tyy.services;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.provisioning.UserDetailsManager;
import org.springframework.stereotype.Service;

import com.tyy.entities.UserDTO;

@Service
public class SystemUserDetailsService implements UserDetailsService {

	@Autowired
	private UserDetailsManager jdbcUserDetailsManager;
	
	@Autowired
	private BCryptPasswordEncoder encoder;
	
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		UserDetails user = jdbcUserDetailsManager.loadUserByUsername(username);
		if(user==null) {
			throw new UsernameNotFoundException("No user found with username: "+username);
		}
		return user;
	}
	
	public boolean registerUser(UserDTO newUser, String type) {
		if(jdbcUserDetailsManager.userExists(newUser.getUsername())) {
			return false;
		}
		List<GrantedAuthority> auth = new ArrayList<GrantedAuthority>();
		auth.add(new SimpleGrantedAuthority("ROLE_"+type.toUpperCase()));
		newUser.setAuthorities(auth);
		String oldpass = newUser.getPassword();
		newUser.setPassword(encoder.encode(oldpass));
			jdbcUserDetailsManager.createUser(newUser);
		return true;
	}

}

