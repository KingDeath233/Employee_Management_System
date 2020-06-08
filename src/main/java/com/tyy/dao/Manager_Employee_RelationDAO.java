package com.tyy.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.tyy.entities.Manager_Employee_Relation;

public interface Manager_Employee_RelationDAO extends JpaRepository<Manager_Employee_Relation, Integer>{

	/*@Query("select new com.allan.thymeleaf_crud.dto.FirstDTO(e.id,m.Mid,e.firstName,e.lastName,e.email,m.M_First_Name,m.M_Last_Name) from Employee as e,Manager as m")
	public List<FirstDTO> findAllFromTwo();*/

}
