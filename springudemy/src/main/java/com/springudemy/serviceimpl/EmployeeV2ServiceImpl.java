package com.springudemy.serviceimpl;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.springudemy.enity.EmployeeEntity;
import com.springudemy.exception.EmployeeNotFoundException;
import com.springudemy.model.Employee;
import com.springudemy.repository.EmployeeRepository;
import com.springudemy.service.EmployeeService;

@Service
public class EmployeeV2ServiceImpl implements EmployeeService {

	@Autowired
	private EmployeeRepository employeeRepository;

	@Override
	public Employee saveEmployee(Employee employee) {
		if (employee.getEmployeeId() == null || employee.getEmployeeId().isEmpty()) {
			employee.setEmployeeId(UUID.randomUUID().toString());

		}

		EmployeeEntity employeeEntity = new EmployeeEntity();
		BeanUtils.copyProperties(employee, employeeEntity);
		employeeRepository.save(employeeEntity);// as emp repo consist employee entity not employee
		return employee;
	}

	@Override
	public List<Employee> getAllEmployees() {
		
		List<EmployeeEntity> employeeEntitiesList = employeeRepository.findAll();
//		here getting employ enitity list we need to convert it onto employee list
		List<Employee> employees = employeeEntitiesList.stream().map(employeeEntity->{
			Employee employee = new Employee();
			BeanUtils.copyProperties(employeeEntity, employee);
			return employee;
		}).collect(Collectors.toList());
		return employees;
	}

	@Override
	public Employee getEmployeeById(String id) {
		EmployeeEntity employeeEntity = employeeRepository.findById(id).orElseThrow(()-> new EmployeeNotFoundException("Employee not found with id: "+id));
		Employee employee = new Employee();
		BeanUtils.copyProperties(employeeEntity, employee);
		
		return employee;
	}

	@Override
	public String deleteEmployeeById(String id) {
	employeeRepository.deleteById(id);
		return "Employee deleted with id: "+id;
	}

}
