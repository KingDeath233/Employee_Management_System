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

import com.tyy.entities.UserDTO;
import com.tyy.services.SystemUserDetailsService;


@Controller

public class UserController {
	@Autowired 
	SystemUserDetailsService userService;
	
	@RequestMapping("/login")
	public String login() {
		return "/login";
	}
	
	@GetMapping("/register")
	public String register(Model model) {
		model.addAttribute("newUser", new UserDTO());
		return "/register";
	}
	
	@PostMapping("/process-register")
	public String registerUser(@Valid @ModelAttribute("newUser") UserDTO newUser, BindingResult result, Model model) {
		if(result.hasErrors()){
			return "/register";
		}
		else if(!userService.registerUser(newUser)) {
			return "redirect:/register?userExists";
		}
		return "redirect:/login?signedup";
	}
	
	@RequestMapping("/access-denied")
	public String accessDenied() {
		return "access_denied";
	}
}
