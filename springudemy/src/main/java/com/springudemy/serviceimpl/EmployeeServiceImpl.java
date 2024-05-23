package com.springudemy.serviceimpl;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.springudemy.exception.EmployeeNotFoundException;
import com.springudemy.model.Employee;
import com.springudemy.service.EmployeeService;

@Service
public class EmployeeServiceImpl implements EmployeeService {
	
	List<Employee> employees = new ArrayList<>();

	@Override
	public Employee saveEmployee(Employee employee) {
		if (employee.getEmployeeId()==null || employee.getEmployeeId().isEmpty()) {
			employee.setEmployeeId(UUID.randomUUID().toString());
			
		}
		employees.add(employee);
		return employee;
	}

	@Override
	public List<Employee> getAllEmployees() {
		// TODO Auto-generated method stub
		return employees;
	}

	@Override
	public Employee getEmployeeById(String id) {
		// TODO Auto-generated method stub
		return employees.stream().filter(emp->emp.getEmployeeId()
				.equalsIgnoreCase(id))
				.findFirst()
				.orElseThrow(()-> new EmployeeNotFoundException("Employee not found with id: "+id));
	}

	@Override
	public String deleteEmployeeById(String id) {
		Employee employee = employees.stream().filter(e->e.getEmployeeId().equalsIgnoreCase(id)).findFirst().get();
		employees.remove(employee);
		return "Employee deleted with id: "+id;
	}

}
