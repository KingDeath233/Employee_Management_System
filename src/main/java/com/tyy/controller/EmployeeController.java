package com.tyy.controller;

import java.util.ArrayList;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import com.tyy.dto.PasswordDTO;
import com.tyy.dto.PersonalScheduleDTO;
import com.tyy.dto.UserDTO;
import com.tyy.entities.Employee;
import com.tyy.entities.Schedule;
import com.tyy.services.EmployeeService;
import com.tyy.services.ScheduleService;
import com.tyy.services.SystemUserDetailsService;

@Controller
public class EmployeeController{
	
	@Autowired 
	SystemUserDetailsService userService;
	@Autowired
	EmployeeService employeeService;
	@Autowired
	ScheduleService scheduleService;
	
	@GetMapping("/employee/profile")
	public String eProfile(Model theModel) {
		String username = userService.getUsername();
		Employee e = employeeService.findByUsername(username);
		theModel.addAttribute("employee",e);
		return "/employee/profile";
	}
	
	@PostMapping("/employee/profile/save")
	public String saveProfile(@Valid @ModelAttribute("employee") Employee employee,BindingResult result,Model theModel) {
		if(result.hasErrors()){
			return "/employee/profile";
		}
		else {
			employeeService.save(employee);
			return "redirect:/";
		}
	}
	
	@GetMapping("/employee/change_password")
	public String changePassword(Model theModel) {
		PasswordDTO pdto = new PasswordDTO();
		theModel.addAttribute("psdto", pdto);
		return "/employee/change_password";
	}
	
	@PostMapping("employee/process-password")
	public String processPassword(@Valid @ModelAttribute("psdto") PasswordDTO psdto,BindingResult result, Model theModel) {
		if(result.hasErrors()){
			return "/employee/change_password";
		}
		userService.changePassword(psdto.getNewpass());
		return "redirect:/";
	}
	
	@GetMapping("/employee/my_schedule")
	public String viewMySchedule(Model theModel) {
		int eid = employeeService.getCurrentEmployeeID();
		List<Schedule> personalSchedules = scheduleService.findAllByEmployeeId(eid);
		List<PersonalScheduleDTO> psdto = new ArrayList<PersonalScheduleDTO>(); 
		System.out.println(psdto);
		/*String[] day = {"Monday","Tuesday","Wednesday","Thursday","Friday","Saturday","Sunday"};
		for(String d:day) {
			psdto.add(new PersonalScheduleDTO(d));
		}
		for(Schedule ps:personalSchedules) {
			
		}*/
		theModel.addAttribute("psdto",psdto);
		return "/employee/my_schedule";
	}
}
