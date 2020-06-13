package com.tyy.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name="authorities")
@Data
@AllArgsConstructor
@NoArgsConstructor

public class Auth {
	@Id
	private String username;
	
	@Column(name="authority")
	private String authority;
}
