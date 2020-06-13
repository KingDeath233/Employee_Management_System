package com.tyy.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.tyy.dto.ManagerEmployeeRelationDTO;
import com.tyy.entities.ManagerEmployeeRelation;

public interface ManagerEmployeeRelationDAO extends JpaRepository<ManagerEmployeeRelation, Integer>{

	@Query("select new com.tyy.dto.ManagerEmployeeRelationDTO(r.id,m.id,m.lastname,m.firstname,e.id,e.lastname,e.firstname) \r\n" + 
			"from Employee e, Employee m, ManagerEmployeeRelation r \r\n" + 
			"where e.id = r.employeeid and m.id = r.managerid \r\n" + 
			"order by m.id,e.id")
	public List<ManagerEmployeeRelationDTO> findAllFromManagerEmployeeRelation();

	public List<ManagerEmployeeRelation> findAll();
	
	public List<ManagerEmployeeRelation> findAllByEmployeeid(int id);
	
	public List<ManagerEmployeeRelation> findAllByManagerid(int id);
}
