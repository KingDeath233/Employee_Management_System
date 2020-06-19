package com.tyy.services;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.tyy.dao.ManagerEmployeeRelationDAO;
import com.tyy.dto.ManagerEmployeeRelationDTO;
import com.tyy.entities.ManagerEmployeeRelation;


@Service
public class ManagerEmployeeRelationService extends MainService{
	
	@Autowired ManagerEmployeeRelationDAO repo;
	
	public Page<ManagerEmployeeRelationDTO> findAllFromManagerEmployeeRelation(Pageable pageable, String key){
		List<ManagerEmployeeRelationDTO> relations = repo.findAllFromManagerEmployeeRelation();
		List<ManagerEmployeeRelationDTO> key_search = new ArrayList<ManagerEmployeeRelationDTO>();
		if(key.equals("")) {
			key_search = relations;
		}
		else {
			for(ManagerEmployeeRelationDTO r:relations) {
				if(r.search().toLowerCase().contains(key.toLowerCase())) {
					key_search.add(r);
				}
			}
		}
		List<ManagerEmployeeRelationDTO> list = searchUsingKey(key_search, pageable);
		Page<ManagerEmployeeRelationDTO> MERPage = new PageImpl<ManagerEmployeeRelationDTO>(list, PageRequest.of(pageable.getPageNumber(), pageable.getPageSize()),key_search.size());
		return MERPage;
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
