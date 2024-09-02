package com.hexaware.eventmanagement.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hexaware.eventmanagement.dto.EmployeeDTO;
import com.hexaware.eventmanagement.entity.Employee;
import com.hexaware.eventmanagement.exception.DuplicateEmployeeException;
import com.hexaware.eventmanagement.exception.EmployeeNotFoundException;
import com.hexaware.eventmanagement.repository.AddressRepo;
import com.hexaware.eventmanagement.repository.EmployeeRepo;

@Service
public class EmployeeServiceImp implements IEmployeeServices {

	
	@Autowired
	EmployeeRepo employeeRepo;
	
	@Autowired
	AddressRepo addressRepo;
	
	
	@Override
	public Employee addEmployee(EmployeeDTO employeeDTO) {
		
		
		try {
			Employee existingEmail=employeeRepo.findByEmail(employeeDTO.getEmail());
			
			if(existingEmail!=null) {
				throw new DuplicateEmployeeException("Employee with email " +employeeDTO.getEmail());
			}
			
			Employee existingMobNo=employeeRepo.findByMobNo(employeeDTO.getMobNo());
			
			if(existingMobNo!=null) {
				throw new DuplicateEmployeeException("Employee with email " +employeeDTO.getEmail());
			}
			
		
		Employee employee=new Employee();
		employee.setEmployeeName(employeeDTO.getEmployeeName());
		employee.setEmail(employeeDTO.getEmail());
		employee.setPosition(employeeDTO.getPosition());
		employee.setDepartment(employeeDTO.getDepartment());
		employee.setMobNo(employeeDTO.getMobNo());
		employee.setDoj(employeeDTO.getDoj());
		
		if(employeeDTO.getAddress()!=null)
		{
			addressRepo.save(employeeDTO.getAddress());
		}
		employee.setAddress(employeeDTO.getAddress());
		
		Employee addEmployee=employeeRepo.save(employee);
		
		return addEmployee;
		}
		catch(Exception e) {
			throw new RuntimeException("Error creating employee", e);
		}
	}

	@Override
	public Employee updateEmployee(EmployeeDTO employeeDTO) {
		try {
			if(!employeeRepo.existsById(employeeDTO.getEmployeeId())) {
				throw new EmployeeNotFoundException("Employee not found with ID: "+ employeeDTO.getEmployeeId());
			}
			Employee employee=new Employee();
			employee.setEmployeeId(employeeDTO.getEmployeeId());
			employee.setEmployeeName(employeeDTO.getEmployeeName());
			employee.setEmail(employeeDTO.getEmail());
			employee.setPosition(employeeDTO.getPosition());
			employee.setDepartment(employeeDTO.getDepartment());
			employee.setMobNo(employeeDTO.getMobNo());
			employee.setDoj(employeeDTO.getDoj());
			
			if(employeeDTO.getAddress()!=null)
			{
				addressRepo.save(employeeDTO.getAddress());
			}
			employee.setAddress(employeeDTO.getAddress());
			
			Employee updateEmployee=employeeRepo.save(employee);
			
			return updateEmployee;
			
		}
		catch(Exception e) {
			
			throw new RuntimeException("Error Updating Employee",e);
	
		}
		
	}

	@Override
	public void deleteByEmployeeId(long employeeId) {
		try {
			if(!employeeRepo.existsById(employeeId)) {
				throw new EmployeeNotFoundException("Employee not found with ID: "+ employeeId);
			}
			employeeRepo.deleteById(employeeId);
		}
		catch(Exception e) {
			throw new RuntimeException("Error Deleting Employee",e);
		}
		
	}


	@Override
	public List<Employee> getAllEmployees() {
		try {
			List<Employee> employees=employeeRepo.findAll();
			return employees;
		}
		catch(Exception e) {
			throw new RuntimeException("Error getting all Employee Details",e);
		}
	}

	@Override
	public EmployeeDTO getEmployeeByEmail(String email) {
		try {
			Employee employee=employeeRepo.findByEmail(email);
			if(employee==null) {
				throw new EmployeeNotFoundException("Employee not found with email: "+email);
			}
			
			EmployeeDTO employeeDTO=new EmployeeDTO();
			employeeDTO.setEmployeeId(employee.getEmployeeId());
			employeeDTO.setEmployeeName(employee.getEmployeeName());
			employeeDTO.setEmail(employee.getEmail());
			employeeDTO.setPosition(employee.getPosition());
			employeeDTO.setDepartment(employee.getDepartment());
			employeeDTO.setDoj(employee.getDoj());
			employeeDTO.setMobNo(employee.getMobNo());
			
			if(employee.getAddress()!=null) {
				employeeDTO.setAddress(employee.getAddress());
			}
			
			return employeeDTO;
			
		}
		
		
		catch(Exception e) {
			throw new RuntimeException("Error getting employee by email",e);
		}
	}

	@Override
	public List<Employee> getEmployeeByPosition(String position) {
		try {
			
			List<Employee> employees= employeeRepo.findByPosition(position);
			return employees;
		}
		catch(Exception e){
			throw new RuntimeException("Error getting employees by employee position",e);
		}
	}

	@Override
	public Employee getEmployeeByMobNo(String mobNo) {
		try {
			Employee employee=employeeRepo.findByMobNo(mobNo);
			if(employee==null) {
				throw new EmployeeNotFoundException("Employee not Found with mobile number:"+mobNo);
			}
			return employee;
		}
		catch(Exception e) {
			throw new RuntimeException("Error getting employee by mobile number", e);
		}
	}

	@Override
	public List<Employee> getEmployeeByName(String employeeName) {
		try {
			List<Employee> employees=employeeRepo.findByEmployeeName(employeeName);
			return employees;
		}
		catch(Exception e) {
			throw new RuntimeException("Error getting employee by name ", e);
		}
	}


	@Override
	public Employee getEmployeeByEmployeeId(long employeeId) {
		try {
			Employee employee=employeeRepo.findByEmployeeId(employeeId);
			if(employee==null) {
				throw new EmployeeNotFoundException("Employee not found with a Id:"+employeeId);
				
			}
			return employee;
		}
	 catch(Exception e) {
				throw new RuntimeException("Error getting Employee by Id",e);
			}
		}
	

}
