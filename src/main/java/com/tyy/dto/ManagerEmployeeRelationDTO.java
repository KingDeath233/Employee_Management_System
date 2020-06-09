package com.tyy.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ManagerEmployeeRelationDTO {

	private int id;
	private int mid;
	private String manager_last_name;
	private String manager_first_name;
	private int eid;
	private String employee_last_name;
	private String employee_first_name;

}
