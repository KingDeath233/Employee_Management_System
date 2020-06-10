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
import org.springframework.web.bind.annotation.RequestParam;

import com.tyy.entities.ManagerEmployeeRelation;
import com.tyy.services.EmployeeService;
import com.tyy.services.ManagerEmployeeRelationService;

@RequestMapping("admin")
@Controller
public class AdminController {
	
	@Autowired
	ManagerEmployeeRelationService MERService;
	@Autowired
	EmployeeService employeeService;
	
	@GetMapping("/show_relation")
	public String showRelation(Model theModel) {
		theModel.addAttribute("relations",MERService.findAllFromManagerEmployeeRelation());
		return "/admin/show_relation";
	}

	@GetMapping("/delete_relation")
	public String deleteRelation(@RequestParam("id") int id) {
		MERService.deleteById(id);
		return "redirect:/admin/show_relation";
	}
	
	@GetMapping("/add_relation")
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
}
