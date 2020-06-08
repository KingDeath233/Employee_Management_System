package com.tyy.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.tyy.entities.Employee;

public interface EmployeeDAO extends JpaRepository<Employee, Integer>{
	public Employee findByUsername(String username);
}
