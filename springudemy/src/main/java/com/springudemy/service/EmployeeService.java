package com.springudemy.service;

import java.util.List;

import com.springudemy.model.Employee;

public interface EmployeeService {
	
	Employee saveEmployee(Employee employee);

	List<Employee> getAllEmployees();

	Employee getEmployeeById(String id);

	String deleteEmployeeById(String id);

}
