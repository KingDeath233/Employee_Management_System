package com.tyy.dto;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import javax.persistence.Id;
import javax.validation.constraints.NotEmpty;

import org.hibernate.validator.constraints.Length;
import org.springframework.security.core.GrantedAuthority;
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
	@Id
	@NotEmpty(message="Username cannot be empty!")
	private String username;
	@Length(min=5, message="Password should be more than 5 digits!")
	private String password;
	private String matchingPassword;
	@Length(min=8,max=8,message="Serial code should be exactly 8 digits!")
	private String code;
	
	private List<GrantedAuthority> authorities = new ArrayList<>();
	
	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
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
