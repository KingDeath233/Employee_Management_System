package com.tyy.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.tyy.entities.Resource;

public interface ResourceDAO extends JpaRepository<Resource, Integer>{
	public List<Resource> findAll();
}
