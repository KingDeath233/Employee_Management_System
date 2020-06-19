package com.tyy.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.tyy.dto.ManagerEmployeeRelationDTO;
import com.tyy.dto.UserDTOforAdmin;
import com.tyy.entities.Employee;
import com.tyy.entities.ManagerEmployeeRelation;
import com.tyy.services.AdminService;
import com.tyy.services.EmployeeService;
import com.tyy.services.ManagerEmployeeRelationService;
import com.tyy.services.SystemUserDetailsService;

@Controller
public class AdminController extends MainController{
	
	@Autowired
	ManagerEmployeeRelationService MERService;
	@Autowired
	EmployeeService employeeService;
	@Autowired
	AdminService adminService;
	@Autowired
	SystemUserDetailsService userService;
	
	@GetMapping("/admin/show_relation")
	public String showRelation(Model theModel,@RequestParam(value = "page",defaultValue = "1")int page, @RequestParam(value = "size",defaultValue = "5")int size,
			@RequestParam(value = "key",defaultValue = "")String key) {
		Page<ManagerEmployeeRelationDTO> MERPage = MERService.findAllFromManagerEmployeeRelation(PageRequest.of(page-1, size),key);
		theModel.addAttribute("MERPage",MERPage);
		theModel = setPageAndKey(theModel,MERPage.getTotalPages(),page,key);
		return "/admin/show_relation";
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
	
	@GetMapping("/admin/delete_relation")
	public String deleteRelation(@RequestParam("id") int id) {
		MERService.deleteById(id);
		return "redirect:/admin/show_relation";
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
			userService.checkIsManager(e);
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
	
	@GetMapping("/admin/delete_employee")
    public String deleteEmployee(@RequestParam("id") int id) {
        Employee tmp = employeeService.findEmployee(id);
        if(tmp.getIsmanager()==0) {
            List<ManagerEmployeeRelation> relations = MERService.findAllByEmployeeid(id);
            for(ManagerEmployeeRelation r:relations) {
                MERService.deleteById(r.getId());
            }
        }
        else {
            List<ManagerEmployeeRelation> relations = MERService.findAllByManagerid(id);
            for(ManagerEmployeeRelation r:relations) {
                MERService.deleteById(r.getId());
            }
        }
        employeeService.deleteById(id);
        return "redirect:/manager/show_employee_list";
    }
	
	@GetMapping("/admin/show_users")
	public String showUsers(Model theModel,@RequestParam(value = "page",defaultValue = "1")int page, @RequestParam(value = "size",defaultValue = "5")int size
			,@RequestParam(value = "key",defaultValue = "")String key) {
		Page<UserDTOforAdmin> userPage = userService.findAllUserWithAuth(PageRequest.of(page-1, size),key);
		theModel.addAttribute("userPage",userPage);
		theModel = setPageAndKey(theModel,userPage.getTotalPages(),page,key);
		return "/admin/show_users";
	}
	
	@GetMapping("/admin/process-enable")
	public String processEnable(@RequestParam("username") String username) {
		userService.changeEnable(username);
		return "redirect:/admin/show_users";
	}
	
	@GetMapping("/admin/process-position")
	public String processPosition(@RequestParam("username") String username) {
		userService.changePosition(username);
		return "redirect:/admin/show_users";
	}
	
	@GetMapping("/admin/reset-password")
	public String resetPassword(@RequestParam("username") String username) {
		userService.resetPassword(username);
		return "redirect:/admin/show_users";
	}
	
	@GetMapping("/test")
	public String test() {
		return "work-on/table-example";
	}
	
	@GetMapping("/test1")
	public String test1() {
		return "/work-on/work_on_template";
	}
	
	@GetMapping("/test2")
	public String test2() {
		return "/work-on/page";
	}
	
	@GetMapping("/error")
	public String error() {
		return "error";
	}
	
}
