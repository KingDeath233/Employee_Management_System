package com.tyy.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.tyy.entities.Schedule;

public interface ScheduleDAO extends JpaRepository<Schedule, Integer>{

	public List<Schedule> findAllByEmployeeIdOrderByStartTime(int id);
	
}
