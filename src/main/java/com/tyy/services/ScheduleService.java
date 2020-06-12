package com.tyy.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tyy.dao.ScheduleDAO;
import com.tyy.entities.Schedule;

@Service
public class ScheduleService {

	@Autowired
	ScheduleDAO repo;
	
	public List<Schedule> findAllByEmployeeId(int id){
		return repo.findAllByEmployeeIdOrderByStartTime(id);
	}
	
}
