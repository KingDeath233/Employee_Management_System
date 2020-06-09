package com.tyy.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class MainPageController{
	@RequestMapping("/")
	public String index() {
		return "redirect:/system/main";
	}
	
	@RequestMapping("/system/main")
	public String system() {
		return "/system/main";
	}
}
