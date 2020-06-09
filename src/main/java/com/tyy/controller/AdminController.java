package com.tyy.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.tyy.entities.ManagerEmployeeRelation;
import com.tyy.services.EmployeeService;
import com.tyy.services.ManagerEmployeeRelationService;

@Controller
public class AdminController {
	
	@Autowired
	ManagerEmployeeRelationService MERService;
	@Autowired
	EmployeeService employeeService;
	
	@GetMapping("/admin/show_relation")
	public String updateRelation(Model theModel) {
		theModel.addAttribute("relations",MERService.findAllFromManagerEmployeeRelation());
		return "/admin/show_relation";
	}

	@GetMapping("/admin/delete_relation")
	public String deleteRelation(@RequestParam("id") int id) {
		MERService.deleteById(id);
		return "redirect:/admin/show_relation";
	}
	
	@GetMapping("/admin/update_relation")
	public String updateRelation(@RequestParam("id") int id, Model theModel){
		ManagerEmployeeRelation relation = MERService.findById(id);
		theModel.addAttribute("relation",relation);	
		theModel.addAttribute("employees",employeeService.findAllEmployee());
		theModel.addAttribute("managers",employeeService.findAllManager());
		return "/admin/update_relation";
	}
	
	@PostMapping("/admin/process-update_relation")
	public String processUpdateRelation(@Valid @ModelAttribute("relation") ManagerEmployeeRelation relation, BindingResult result,Model model) {
		MERService.save(relation);
		return "redirect:/admin/show_relation";
	}
}
