package com.tyy.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@AllArgsConstructor
@NoArgsConstructor

public class UserDTOforAdmin {
	private String username;
	private int enabled;
	private String auth;
	
	public String search() {
		String tmp = "enabled";
		if(enabled==0) {
			tmp = "disabled";
		}
		return username+" "+auth+" "+tmp;
	}
}
