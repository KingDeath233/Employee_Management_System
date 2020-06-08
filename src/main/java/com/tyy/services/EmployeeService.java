package com.tyy.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tyy.dao.EmployeeDAO;
import com.tyy.entities.Employee;

@Service
public class EmployeeService {
	
	@Autowired
	private EmployeeDAO repo;
	
	public Employee findByUsername(String username) {
		return repo.findByUsername(username);
	}
	
	public void save(Employee employee) {
		repo.save(employee);
	}
}
