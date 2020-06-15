package com.tyy.controller;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

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
public class ManagerController {
	
	@Autowired
	EmployeeService employeeService;
	
	@Autowired
	SerialCodeService serialService;
	
	@GetMapping("/manager/show_employee_list")
	public String showEmployeeList(Model theModel, @RequestParam("page")Optional<Integer> page, @RequestParam("size")Optional<Integer> size){
		 int currentPage = page.orElse(1);
		 int pageSize = size.orElse(5);
		 Page<Employee> employeePage = employeeService.findPaginated(PageRequest.of(currentPage-1, pageSize));
		 theModel.addAttribute("employeePage",employeePage);
		 int totalPages = employeePage.getTotalPages();
		 if(totalPages>0) {
			 List<Integer> pageNumbers = IntStream.rangeClosed(1, totalPages).boxed().collect(Collectors.toList());
			 theModel.addAttribute("pageNumbers",pageNumbers);
		 }
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
}
