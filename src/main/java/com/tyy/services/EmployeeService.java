package com.tyy.services;

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
public class EmployeeService {
	
	@Autowired
	private EmployeeDAO repo;
	@Autowired
	private SystemUserDetailsService userservice;
	
	public Page<Employee> findPaginated(Pageable pageable){
		List<Employee> employees = findAllEmployeeAndManager();

		int pageSize = pageable.getPageSize();
		int currentPage = pageable.getPageNumber();
		int startItem = currentPage * pageSize;
		
		System.out.println("pageSize: "+pageSize+"\ncurrentPage "+currentPage+"\nStartItem: "+startItem);
		
		List<Employee> list;
		if(employees.size()<startItem) {
			list= Collections.emptyList();
		}else {
			int toIndex = Math.min(startItem + pageSize,  employees.size());
			list=employees.subList(startItem, toIndex);
		}
		Page<Employee> employeePage = new PageImpl<Employee>(list, PageRequest.of(currentPage, pageSize),employees.size());
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
