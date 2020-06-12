package com.tyy.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tyy.dao.EmployeeDAO;
import com.tyy.entities.Employee;

@Service
public class EmployeeService {
	
	@Autowired
	private EmployeeDAO repo;
	@Autowired
	private SystemUserDetailsService userservice;
	
	public Employee findByUsername(String username) {
		return repo.findByUsername(username);
	}
	
	public void save(Employee employee) {
		repo.save(employee);
	}
	
	public int getCurrentEmployeeID() {
		String username = userservice.getUsername();
		return findByUsername(username).getId();
	}
	
	public List<Employee> findAllEmployee(){
		return repo.findAllByIsmanagerOrderByLastnameAscFirstnameAsc(0);
	}
	
	public List<Employee> findAllManager(){
		return repo.findAllByIsmanagerOrderByLastnameAscFirstnameAsc(1);
	}
	
	public Employee findEmployee(int id) {
		return repo.getOne(id);
	}
	
	public void deleteById(int id) {
		repo.deleteById(id);
	}
}
