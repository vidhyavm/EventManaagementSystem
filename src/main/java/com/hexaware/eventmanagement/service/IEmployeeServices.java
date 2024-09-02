package com.hexaware.eventmanagement.service;

import java.util.List;


import com.hexaware.eventmanagement.dto.EmployeeDTO;
import com.hexaware.eventmanagement.entity.Employee;

public interface IEmployeeServices {
	
	public Employee addEmployee(EmployeeDTO employeeDTO);
	public Employee updateEmployee(EmployeeDTO employeeDTO);
	public void deleteByEmployeeId(long employeeId);
	public List<Employee> getAllEmployees();
	public EmployeeDTO getEmployeeByEmail(String email);
	public List<Employee> getEmployeeByPosition(String position);
	public Employee getEmployeeByMobNo(String mobNo);
	public List<Employee> getEmployeeByName(String employeeName);
	public Employee getEmployeeByEmployeeId(long employeeId);
	

}
