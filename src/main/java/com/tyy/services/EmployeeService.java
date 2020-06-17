package com.tyy.services;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.tyy.dao.EmployeeDAO;
import com.tyy.entities.Employee;

@Service
public class EmployeeService extends MainService{
	
	@Autowired
	private EmployeeDAO repo;
	@Autowired
	private SystemUserDetailsService userservice;
	
	public Page<Employee> findAllEmployeeAndManagerPage(Pageable pageable, String key){
		List<Employee> employees = findAllEmployeeAndManager();
		List<Employee> key_search = new ArrayList<Employee>();
		if(key.equals("")) {
			key_search = employees;
		}
		else {
			for(Employee e:employees) {
				if(e.search().toLowerCase().contains(key.toLowerCase())) {
					key_search.add(e);
				}
			}
		}
		List<Employee> list = searchUsingKey(key_search, pageable);
		Page<Employee> employeePage = new PageImpl<Employee>(list, PageRequest.of(pageable.getPageNumber(), pageable.getPageSize()),key_search.size());
		return employeePage;
	}
	
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
	
	public List<Employee> findAllEmployeeAndManager(){
		List<Employee> both = findAllManager();
		both.addAll(findAllEmployee());
		return both;
	}
	
	public Employee findEmployee(int id) {
		return repo.getOne(id);
	}
	
	public void deleteById(int id) {
		repo.deleteById(id);
	}
}
