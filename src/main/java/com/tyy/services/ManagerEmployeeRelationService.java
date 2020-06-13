package com.tyy.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tyy.dao.ManagerEmployeeRelationDAO;
import com.tyy.dto.ManagerEmployeeRelationDTO;
import com.tyy.entities.ManagerEmployeeRelation;


@Service
public class ManagerEmployeeRelationService {
	
	@Autowired ManagerEmployeeRelationDAO repo;
	
	public List<ManagerEmployeeRelationDTO> findAllFromManagerEmployeeRelation(){
		return repo.findAllFromManagerEmployeeRelation();
	}
	
	public void save(ManagerEmployeeRelation relation) {
		repo.save(relation);
	}
	
	public void deleteById(int id) {
		repo.deleteById(id);
	}
	
	public ManagerEmployeeRelation findById(int id) {
		return repo.getOne(id);
	}
	
	public List<ManagerEmployeeRelation> findAll(){
		return repo.findAll();
	}
	
	public List<ManagerEmployeeRelation> findAllByEmployeeid(int id){
		return repo.findAllByEmployeeid(id);
	}
	
	public List<ManagerEmployeeRelation> findAllByManagerid(int id){
		return repo.findAllByManagerid(id);
	}
}
