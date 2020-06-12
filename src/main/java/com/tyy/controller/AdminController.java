package com.tyy.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.tyy.entities.Employee;
import com.tyy.entities.ManagerEmployeeRelation;
import com.tyy.services.AdminService;
import com.tyy.services.EmployeeService;
import com.tyy.services.ManagerEmployeeRelationService;

@Controller
public class AdminController {
	
	@Autowired
	ManagerEmployeeRelationService MERService;
	@Autowired
	EmployeeService employeeService;
	@Autowired
	AdminService adminService;
	
	@GetMapping("/admin/show_relation")
	public String showRelation(Model theModel) {
		theModel.addAttribute("relations",MERService.findAllFromManagerEmployeeRelation());
		return "/admin/show_relation";
	}

	@GetMapping("/admin/delete_relation")
	public String deleteRelation(@RequestParam("id") int id) {
		MERService.deleteById(id);
		return "redirect:/admin/show_relation";
	}
	
	@GetMapping("/admin/add_relation")
	public String addRelation(Model theModel){
		ManagerEmployeeRelation relation = new ManagerEmployeeRelation();
		theModel.addAttribute("relation",relation);	
		theModel.addAttribute("employees",employeeService.findAllEmployee());
		theModel.addAttribute("managers",employeeService.findAllManager());
		return "/admin/add_relation";
	}
	
	@PostMapping("/admin/process-add_relation")
	public String processAddRelation(@Valid @ModelAttribute("relation") ManagerEmployeeRelation relation, BindingResult result,Model model) {
		if(result.hasErrors()) {
			return "/admin/add_relation";
		}
		else {
			MERService.save(relation);
			return "redirect:/admin/show_relation";
		}
	}
	
	@GetMapping("/manager/show_employee_list")
	public String showEmployeeList(Model theModel) {
		List<Employee> m = employeeService.findAllManager();
		List<Employee> e = employeeService.findAllEmployee();
		m.addAll(e);
		theModel.addAttribute("stuff", m);
		return "/manager/show_employee_list";
	}
	
	@GetMapping("/admin/add_employee")
	public String addEmployee(Model theModel) {
		Employee e = new Employee();
		List<String> usernames = adminService.findAllUsername();
		theModel.addAttribute("newEmployee", e);
		theModel.addAttribute("avaiable_list",usernames);
		return "/admin/add_employee";
	}
	
	@PostMapping("/admin/process-add_employee")
	public String processAddEmployee(@Valid @ModelAttribute("newEmployee") Employee e,BindingResult result,Model theModel) {
		if(result.hasErrors()) {
			return "/admin/add_employee";
		}
		else {
			employeeService.save(e);
			return "redirect:/manager/show_employee_list";
		}
	}
	
	@GetMapping("/admin/update_employee")
	public String updateEmployee(@RequestParam("id") int id, Model theModel) {
		Employee e = employeeService.findEmployee(id);
		theModel.addAttribute("newEmployee", e);
		List<String> usernames = adminService.findAllUsername();
		usernames.add(e.getUsername());
		theModel.addAttribute("avaiable_list",usernames);
		return "admin/add_employee";
	}
	
	@GetMapping("/test")
	public String test() {
		return "table-example";
	}
	
	@GetMapping("/admin/delete_employee")
	public String deleteEmployee(@RequestParam("id") int id) {
		List<ManagerEmployeeRelation> relations = MERService.findAllByEmployeeid(id);
		for(ManagerEmployeeRelation r:relations) {
			MERService.deleteById(r.getId());
		}
		employeeService.deleteById(id);
		return "redirect:/manager/show_employee_list";
	}
}
