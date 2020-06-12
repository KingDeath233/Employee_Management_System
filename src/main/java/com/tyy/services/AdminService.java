package com.tyy.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tyy.dao.AdminDAO;

@Service
public class AdminService {
	@Autowired
	private AdminDAO repo;
	
	public List<String> findAllUsername(){
		return repo.findAllUsername();
	}
}
