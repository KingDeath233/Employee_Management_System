package com.tyy.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.tyy.entities.Employee;
import com.tyy.entities.SerialCode;
import com.tyy.services.EmployeeService;
import com.tyy.services.SerialCodeService;

@Controller
public class ManagerController extends MainController{
	
	@Autowired
	EmployeeService employeeService;
	
	@Autowired
	SerialCodeService serialService;
	
	@GetMapping("/manager/show_employee_list")
	public String showEmployeeList(Model theModel, @RequestParam(value = "page",defaultValue = "1")int page, @RequestParam(value = "size",defaultValue = "5")int size
			,@RequestParam(value = "key",defaultValue = "")String key){
		 Page<Employee> employeePage = employeeService.findAllEmployeeAndManagerPage(PageRequest.of(page-1, size),key);
		 theModel.addAttribute("employeePage",employeePage);
		 theModel = setPageAndKey(theModel,employeePage.getTotalPages(),page,key);
		return "/manager/show_employee_list";
	}
	
	@GetMapping("/manager/generate_code")
	public String generateCode(Model theModel) {
		SerialCode code = new SerialCode();
		theModel.addAttribute("code",code);
		return "/manager/generate_code";
	}
	
	@PostMapping("/manager/process-code")
	public String processCode(@ModelAttribute("code") SerialCode c) {
		for(int i = 0;i<c.getNumber();i++) {
			SerialCode scode = new SerialCode();
			scode.setType(c.getType());
			String s = String.format("%08d",(int)(Math.random()*100000000));
			scode.setCode(s);
			serialService.save(scode);
		}
		return "redirect:/";
	}
	
	@GetMapping("/manager/all_schedule")
	public String allSchedule() {
		return "/manager/all_schedule";
	}
}
