package com.tyy.dto;

import org.hibernate.validator.constraints.Length;

import com.tyy.services.validators.PasswordMatchingForPDTO;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@PasswordMatchingForPDTO
public class PasswordDTO {

	private String oldpass;
	
	@Length(min=5, message="Password should be more than 5 digits!")
	private String newpass;
	
	private String newpassr;
}
