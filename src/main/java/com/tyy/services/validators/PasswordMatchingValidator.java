package com.tyy.services.validators;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import com.tyy.entities.UserDTO;

public class PasswordMatchingValidator implements ConstraintValidator<PasswordMatching, UserDTO> {

	@Override
	public void initialize(PasswordMatching pm) {
		pm.message();
	}
	
	@Override
	public boolean isValid(UserDTO instance, ConstraintValidatorContext context) {	
		return instance.getPassword().equals(instance.getMatchingPassword());
	}
}
