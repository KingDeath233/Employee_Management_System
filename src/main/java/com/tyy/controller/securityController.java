package com.tyy.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/security")
public class securityController {
	@RequestMapping("/login")
	public String login() {
		return "/security/login";
	}
}
