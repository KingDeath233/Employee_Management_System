package com.tyy.bean;

import org.springframework.stereotype.Component;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Component
public class ResourceBean {
	private int id;
	private String url;
	private String roles;
	
	public String[] getRolesArray() {
        String[] authorities = roles.split(",");
        return authorities;
	}
}
