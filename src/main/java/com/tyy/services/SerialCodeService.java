package com.tyy.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tyy.dao.SerialCodeDAO;
import com.tyy.entities.SerialCode;

@Service
public class SerialCodeService {
	@Autowired
	private SerialCodeDAO repo;
	
	public String ifCodeExist(String code) {
		return repo.findTop1ByCode(code).getType();
	}
	
	public SerialCode findByCode(String code) {
		return repo.findTop1ByCode(code);
	}
	
	public void save(SerialCode code) {
		repo.save(code);
	}
	
	public void delete(SerialCode code) {
		repo.delete(code);
	}
}
