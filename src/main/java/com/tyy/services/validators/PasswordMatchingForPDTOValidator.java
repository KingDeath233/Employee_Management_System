package com.tyy.services.validators;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import com.tyy.dto.PasswordDTO;

public class PasswordMatchingForPDTOValidator implements ConstraintValidator<PasswordMatchingForPDTO, PasswordDTO> {

	@Override
	public void initialize(PasswordMatchingForPDTO pm) {
		pm.message();
	}
	
	@Override
	public boolean isValid(PasswordDTO instance, ConstraintValidatorContext context) {	
		return instance.getNewpass().equals(instance.getNewpassr());
	}

}
