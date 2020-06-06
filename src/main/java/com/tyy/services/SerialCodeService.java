package com.tyy.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tyy.dao.SerialCodeDAO;

@Service
public class SerialCodeService {
	@Autowired
	private SerialCodeDAO repo;
	
	public String ifCodeExist(String code) {
		return repo.findByCode(code).getType();
	}
}
