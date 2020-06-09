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

import com.tyy.dto.UserDTO;
import com.tyy.services.SerialCodeService;
import com.tyy.services.SystemUserDetailsService;


@Controller

public class UserController{
	@Autowired 
	SystemUserDetailsService userService;
	@Autowired
	SerialCodeService serialCodeService;
	
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
		String type = serialCodeService.ifCodeExist(newUser.getCode());
		if(type.equals("")) {
			return "redirect:/register?codeNotExists";
		}
		else if(!userService.registerUser(newUser,type)) {
			return "redirect:/register?userExists";
		}
		return "redirect:/login?signedup";
	}
	
	@RequestMapping("/access-denied")
	public String accessDenied() {
		return "access_denied";
	}
}
