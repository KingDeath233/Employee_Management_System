package com.tyy.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.tyy.entities.SerialCode;

public interface SerialCodeDAO extends JpaRepository<SerialCode, Integer>{
	
	public SerialCode findTop1ByCode(String code);

}
