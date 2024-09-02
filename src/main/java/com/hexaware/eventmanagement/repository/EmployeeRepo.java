package com.hexaware.eventmanagement.repository;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.hexaware.eventmanagement.entity.Employee;


@Repository
public interface EmployeeRepo extends JpaRepository<Employee,Long> {
	
	public List<Employee> findByEmployeeName(String name);
	public Employee findByMobNo(String mobNo);
	public List<Employee> findByPosition(String position);
	public Employee findByEmail(String email);
	public Employee findByEmployeeId(long employeeId);
	
	

}
