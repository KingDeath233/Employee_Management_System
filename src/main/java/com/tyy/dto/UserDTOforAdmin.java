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
}
