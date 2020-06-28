package com.tyy.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.tyy.dto.PasswordDTO;
import com.tyy.entities.Employee;
import com.tyy.services.EmployeeService;
import com.tyy.services.ScheduleService;
import com.tyy.services.SystemUserDetailsService;

@Controller
public class EmployeeController extends MainController{
	
	@Autowired 
	SystemUserDetailsService userService;
	@Autowired
	EmployeeService employeeService;
	@Autowired
	ScheduleService scheduleService;
	
	@RequestMapping("")
	public String index() {
		return "redirect:/system/main";
	}
	
	@RequestMapping("system/main")
	public String system() {
		return "system/main";
	}
	
	@GetMapping("employee/profile")
	public String eProfile(Model theModel) {
		String username = userService.getUsername();
		Employee e = employeeService.findByUsername(username);
		theModel.addAttribute("employee",e);
		return "employee/profile";
	}
	
	@PostMapping("employee/profile/save")
	public String saveProfile(@Valid @ModelAttribute("employee") Employee employee,BindingResult result,Model theModel) {
		if(result.hasErrors()){
			return "employee/profile";
		}
		else {
			employeeService.save(employee);
			return "redirect:/";
		}
	}
	
	@GetMapping("employee/change_password")
	public String changePassword(Model theModel) {
		PasswordDTO pdto = new PasswordDTO();
		theModel.addAttribute("psdto", pdto);
		return "employee/change_password";
	}
	
	@PostMapping("employee/process-password")
	public String processPassword(@Valid @ModelAttribute("psdto") PasswordDTO psdto,BindingResult result, Model theModel) {
		if(result.hasErrors()){
			return "employee/change_password";
		}
		userService.changePassword(psdto.getNewpass());
		return "redirect:/";
	}
	
	@GetMapping("employee/my_schedule")
	public String viewMySchedule(Model theModel) {
		return "employee/my_schedule";
	}
}
