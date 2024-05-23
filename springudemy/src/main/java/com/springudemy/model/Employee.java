package com.springudemy.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import jakarta.persistence.Column;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
//@JsonIgnoreProperties({"department","lastName"})
//@JsonIgnoreProperties({"department","lastName"})--we will not not get these values in output
public class Employee {
	private String employeeId;
	private String firstName;
	private String lastName;
	private String emailId;
//	@JsonIgnore
//	By using @jsonIgnore--we will not get this parameter in output
	private String department;
	

}
