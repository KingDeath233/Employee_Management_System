package com.tyy.entities;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import javax.validation.constraints.NotEmpty;

import org.hibernate.validator.constraints.Length;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.tyy.services.validators.PasswordMatching;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@PasswordMatching
public class UserDTO implements UserDetails{
	
	private static final long serialVersionUID = 1L;
	@NotEmpty(message="Username cannot be empty!")
	private String username;
	@NotEmpty(message="Password cannot be empty!")
	@Length(min=5, message="Password should be more than 5 digits!")
	private String password;
	@NotEmpty(message="Matching password cannot be empty!")
	private String matchingPassword;
	
	private List<GrantedAuthority> authorities = new ArrayList<>();
	
	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		// TODO Auto-generated method stub
		authorities.add(new SimpleGrantedAuthority("ROLE_"+"EMPLOYEE"));
		return this.authorities;
	}

	public void setAuthorities(List<GrantedAuthority> authorities) {
		this.authorities = authorities;
	}


	@Override
	public boolean isAccountNonExpired() {
		return true;
	}

	@Override
	public boolean isAccountNonLocked() {
		return true;
	}

	@Override
	public boolean isCredentialsNonExpired() {
		return true;
	}

	@Override
	public boolean isEnabled() {
		return true;
	}

	
}
