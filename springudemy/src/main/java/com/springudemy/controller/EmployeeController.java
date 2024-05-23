package com.springudemy.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.springudemy.model.Employee;
import com.springudemy.service.EmployeeService;

@RestController
@RequestMapping("/employees")
public class EmployeeController {
	
	@Qualifier("employeeServiceImpl")
	@Autowired
	private EmployeeService employeeService;
	
	/*
	 *This method is used to create new new employee 
	 */
	
	@PostMapping("/create")
	public Employee savEmployee(@RequestBody Employee employee) {
		
		return employeeService.saveEmployee(employee);
		
	}
	/*
	 * This method is used to get list of employees
	 */
	@GetMapping(value = "/getallemployees")
	public List<Employee> getAllEmployees(){

		return employeeService.getAllEmployees();
	}

	@GetMapping(value = "/getemployeebyid/{id}")
	public Employee getEmployeeById(@PathVariable String id) {
		
		return employeeService.getEmployeeById(id);
		
	}
	
	@DeleteMapping("/deleteemployeebyid/{id}")
	public String deleteEmployeeById(@PathVariable String id) {
		return employeeService.deleteEmployeeById(id);
		
	}
}
