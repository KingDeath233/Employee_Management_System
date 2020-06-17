package com.tyy.controller;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import org.springframework.ui.Model;

public class MainController {

	public Model setPageAndKey(Model theModel,int totalPages,int page,String key) {
		 if(totalPages>0) {
			 List<Integer> pageNumbers = IntStream.rangeClosed(1, totalPages).boxed().collect(Collectors.toList());
			 theModel.addAttribute("pageNumbers",pageNumbers);
			 theModel.addAttribute("currentpage",page);
			 theModel.addAttribute("key",key);
		 }
		return theModel;
	}
}
