package com.tyy.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.Email;
import javax.validation.constraints.Pattern;

import org.hibernate.validator.constraints.Length;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name="employee")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Employee {
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int id;
	
	@Pattern(regexp = "\\D+", message="Not a valid name!")
	@Column(name="first_name")
	private String first_name;
	
	@Pattern(regexp = "\\D+", message="Not a valid name!")
	@Column(name="last_name")
	private String last_name;
	
	@Email(message="Not an valid email!")
	@Column(name="email")
	private String email;
	
	@Length(min=10,max=11,message="Not a valid phone number!")
	@Column(name="phone")
	private String phone;
	
	@Column(name="username")
	private String username;
}
