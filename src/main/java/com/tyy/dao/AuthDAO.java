package com.tyy.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.tyy.entities.Auth;

public interface AuthDAO extends JpaRepository<Auth, String>{

	List<Auth> findAllByUsername(String username);
}
