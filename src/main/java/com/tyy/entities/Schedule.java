package com.tyy.entities;

import java.sql.Time;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotEmpty;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name="schedule")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Schedule {

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int id;
	
	@NotEmpty
	@Column(name="employeeId")
	private int employeeId;
	
	@NotEmpty
	@Column(name="startTime")
	private Date startTime;
	
	@NotEmpty
	@Column(name="endTime")
	private Date endTime;
	
	@NotEmpty
	@Column(name="jobDes")
	private String jobDes;
	
	@NotEmpty
	@Column(name="loc")
	private String loc;
	
	@NotEmpty
	@Column(name="date")
	private Date date;
	
	@NotEmpty
	@Column(name="time")
	private Time time;
}
