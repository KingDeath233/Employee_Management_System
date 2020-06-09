package com.tyy.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.tyy.entities.Employee;

public interface EmployeeDAO extends JpaRepository<Employee, Integer>{
	public Employee findByUsername(String username);
	public List<Employee> findAllByIsmanagerOrderByLastnameAscFirstnameAsc(int number);
}
