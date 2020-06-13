package com.tyy.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tyy.dao.ResourceDAO;
import com.tyy.entities.Resource;

@Service
public class ResourceService {
	@Autowired
	ResourceDAO repo;
	
	public List<Resource> findAll(){
		return repo.findAll();
	}
}
