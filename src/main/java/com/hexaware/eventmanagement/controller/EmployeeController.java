package com.hexaware.eventmanagement.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.hexaware.eventmanagement.dto.EmployeeDTO;
import com.hexaware.eventmanagement.entity.Employee;
import com.hexaware.eventmanagement.service.IEmployeeServices;

@RestController
@RequestMapping("/api/v1/employees")
public class EmployeeController {
	
	@Autowired
	IEmployeeServices service;
	
	@PostMapping(value="/add")
	public Employee addEmployee(@RequestBody EmployeeDTO employeeDTO) {
		
		return service.addEmployee(employeeDTO);
		
	}
	
	@PutMapping(value="/update")
	public Employee updateEmployee(@RequestBody EmployeeDTO employeeDTO) {
		return service.updateEmployee(employeeDTO);
	}
	
	@DeleteMapping(value="/delete/{employeeId}",produces = "application/json")
	public void deleteByEmployeeId(@PathVariable("employeeId") long employeeId) {
		service.deleteByEmployeeId(employeeId);
	}
	
	@GetMapping(value="/getall",produces = "application/json")
	public List<Employee> getAllEmployees(){
		return service.getAllEmployees();
		
		
	}
	
	@GetMapping(value="/get/email/{email}",produces = "application/json")
	public EmployeeDTO getEmployeeByEmail(@PathVariable("email") String email) {
		return service.getEmployeeByEmail(email);
	}
	
	@GetMapping(value="/get/position/{position}",produces = "application/json")
	public List<Employee> getEmployeeByEmployeePosition(@PathVariable("position") String position){
		return service.getEmployeeByPosition(position);
		
	}
	
	@GetMapping(value="/get/mobile/{mobNo}",produces = "application/json")
	public Employee getEmployeeByMobNo(@PathVariable("mobNo") String mobNo) {
		return service.getEmployeeByMobNo(mobNo);
		
	}
	
	@GetMapping(value="/get/name/{employeeName}",produces = "application/json")
	public List<Employee> getEmployeeByName(@PathVariable("employeeName") String employeeName){
		return service.getEmployeeByName(employeeName);
	}
	
	
	@GetMapping(value="/get/id/{employeeId}",produces = "application/json")
	public Employee getEmployeeByEmployeeId(@PathVariable("employeeId") Long employeeId) {
		return service.getEmployeeByEmployeeId(employeeId);
	}

}
