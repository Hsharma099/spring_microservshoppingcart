package com.springudemy.enity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "tbl_employee")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class EmployeeEntity {
	@Id
//	@GeneratedValue(strategy = GenerationType.IDENTITY)---takje id as long/id
	private String employeeId;
	private String firstName;
	private String lastName;
	@Column(unique = true)
	private String emailId;
	private String department;

}
