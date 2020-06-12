package com.tyy.dto;

import java.util.ArrayList;
import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor

public class PersonalScheduleDTO {

	private String day;
	private int[] time; 
	private int[] index;
	private List<String> des = new ArrayList<String>();
	private List<String> loc = new ArrayList<String>();
	
	public PersonalScheduleDTO(String day){
		this.day = day;
	}
}
