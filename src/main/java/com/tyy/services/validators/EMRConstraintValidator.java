package com.tyy.services.validators;

import java.util.List;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import org.springframework.beans.factory.annotation.Autowired;

import com.tyy.entities.ManagerEmployeeRelation;
import com.tyy.services.ManagerEmployeeRelationService;

public class EMRConstraintValidator implements ConstraintValidator<EMRConstraint, ManagerEmployeeRelation>{

	@Autowired
	ManagerEmployeeRelationService MERService;
	
	@Override
	public void initialize(EMRConstraint pm) {
		pm.message();
	}
	
	@Override
	public boolean isValid(ManagerEmployeeRelation instance, ConstraintValidatorContext context) {	
		List<ManagerEmployeeRelation> relations = MERService.findAll();
		for(ManagerEmployeeRelation r : relations) {
			if(instance.getEmployeeid()==r.getEmployeeid()) {
				return false;
			}
		}
		return true;
	}

}
