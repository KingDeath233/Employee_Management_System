package com.tyy.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.tyy.dto.UserDTOforAdmin;
import com.tyy.entities.User;

public interface UserDAO extends JpaRepository<User, String>{
	
	@Query("select new com.tyy.dto.UserDTOforAdmin(u.username,u.enabled,a.authority) from User as u,Auth as a where u.username=a.username order by case when (u.username='admin') then 0 else 1 end")
	public List<UserDTOforAdmin> findAllUserWithAuth();
	
	@Query("select new com.tyy.dto.UserDTOforAdmin(u.username,u.enabled,a.authority) from User as u,Auth as a where u.username=a.username and u.username=?1")
	public UserDTOforAdmin findUserWithAuth(String username);
	
	public List<User> findAllByUsername(String username);
	

}
