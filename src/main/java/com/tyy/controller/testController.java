package com.tyy.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/test")
public class testController {
	@RequestMapping("/t1")
	public String t1() {
		return "/test/t1";
	}
}
