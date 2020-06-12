package com.tyy.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.tyy.entities.Employee;

public interface AdminDAO extends JpaRepository<Employee, Integer>{

	@Query(value = "select username from users where username not in(select username from employee)", nativeQuery=true)
	public List<String> findAllUsername();
	
}
