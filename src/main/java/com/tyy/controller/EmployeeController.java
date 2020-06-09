package com.tyy.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import com.tyy.entities.Employee;
import com.tyy.services.EmployeeService;
import com.tyy.services.SystemUserDetailsService;

@Controller
public class EmployeeController{
	
	@Autowired 
	SystemUserDetailsService userService;
	@Autowired
	EmployeeService employeeservice;
	
	@PostMapping("/employee/profile")
	public String eProfile(Model theModel) {
		String username = userService.getUsername();
		Employee e = employeeservice.findByUsername(username);
		theModel.addAttribute("employee",e);
		return "/employee/profile";
	}
	
	@PostMapping("/employee/profile/save")
	public String saveProfile(@Valid @ModelAttribute("employee") Employee employee,BindingResult result,Model theModel) {
		if(result.hasErrors()){
			return "/employee/profile";
		}
		else {
			employeeservice.save(employee);
			return "redirect:/";
		}
	}
}
