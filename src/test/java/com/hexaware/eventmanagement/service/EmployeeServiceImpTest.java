package com.hexaware.eventmanagement.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.Arrays;
import java.util.List;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;

import com.hexaware.eventmanagement.dto.EmployeeDTO;
import com.hexaware.eventmanagement.entity.Employee;
import com.hexaware.eventmanagement.repository.EmployeeRepo;

@SpringBootTest
class EmployeeServiceImpTest {
	
	
	@BeforeAll
	static void setUpBeforeClass() throws Exception {
	}
	
	@Mock
	private EmployeeRepo employeeRepo;
	
	@InjectMocks
	private EmployeeServiceImp  employeeService;
	
	
	 @BeforeEach
	    void setUp() {
	        MockitoAnnotations.openMocks(this);
	 }

	   @Test
	    void testAddEmployee_Success() {
	        
	        EmployeeDTO employeeDTO = new EmployeeDTO();
	        employeeDTO.setEmployeeName("vidhya malathi");
	        employeeDTO.setEmail("vidhya@gmail.com");
	        employeeDTO.setPosition("Developer");
	        employeeDTO.setDepartment("IT");
	        employeeDTO.setMobNo("1234567890");

	        Employee employee = new Employee();
	        employee.setEmployeeName(employeeDTO.getEmployeeName());
	        employee.setEmail(employeeDTO.getEmail());
	        employee.setPosition(employeeDTO.getPosition());
	        employee.setDepartment(employeeDTO.getDepartment());
	        employee.setMobNo(employeeDTO.getMobNo());

	        when(employeeRepo.findByEmail(employeeDTO.getEmail())).thenReturn(null);
	        when(employeeRepo.findByMobNo(employeeDTO.getMobNo())).thenReturn(null);
	        when(employeeRepo.save(any(Employee.class))).thenReturn(employee);

	        Employee createdEmployee = employeeService.addEmployee(employeeDTO);

	        assertNotNull(createdEmployee);
	        assertEquals(employeeDTO.getEmail(), createdEmployee.getEmail());
	        verify(employeeRepo, times(1)).save(any(Employee.class));
	    }


	    @Test
	    void testUpdateEmployee_Success() {
	       
	        EmployeeDTO employeeDTO = new EmployeeDTO();
	        employeeDTO.setEmployeeId(1L);
	        employeeDTO.setEmployeeName("Vidhya");
	        employeeDTO.setEmail("vidhyamalathi5d@example.com");

	        Employee existingEmployee = new Employee();
	        existingEmployee.setEmployeeId(1L);

	        when(employeeRepo.existsById(employeeDTO.getEmployeeId())).thenReturn(true);
	        when(employeeRepo.save(any(Employee.class))).thenReturn(existingEmployee);

	        Employee updatedEmployee = employeeService.updateEmployee(employeeDTO);
	        assertNotNull(updatedEmployee);
	        assertEquals(employeeDTO.getEmployeeId(), updatedEmployee.getEmployeeId());
	        verify(employeeRepo, times(1)).save(any(Employee.class));
	    }


	    @Test
	    void testDeleteByEmployeeId_Success() {
	 
	        long employeeId = 1L;
	        when(employeeRepo.existsById(employeeId)).thenReturn(true);

	        employeeService.deleteByEmployeeId(employeeId);

	        verify(employeeRepo, times(1)).deleteById(employeeId);
	    }

	   
	    @Test
	    void testGetAllEmployees() {
	     
	        Employee employee1 = new Employee();
	        Employee employee2 = new Employee();

	        when(employeeRepo.findAll()).thenReturn(Arrays.asList(employee1, employee2));

	        List<Employee> employees = employeeService.getAllEmployees();

	        assertNotNull(employees);
	        assertEquals(2, employees.size());
	        verify(employeeRepo, times(1)).findAll();
	    }

	    @Test
	    void testGetEmployeeByEmail_Success() {

	        String email = "vidhyamalathi5@gmail.com";
	        Employee employee = new Employee();
	        employee.setEmail(email);

	        when(employeeRepo.findByEmail(email)).thenReturn(employee);

	        EmployeeDTO employeeDTO = employeeService.getEmployeeByEmail(email);

	        assertNotNull(employeeDTO);
	        assertEquals(email, employeeDTO.getEmail());
	        verify(employeeRepo, times(1)).findByEmail(email);
	    }

	    @Test
	    void testGetEmployeeByPosition() {
	       
	        String position = "Developer";
	        Employee employee1 = new Employee();
	        Employee employee2 = new Employee();

	        when(employeeRepo.findByPosition(position)).thenReturn(Arrays.asList(employee1, employee2));

	        List<Employee> employees = employeeService.getEmployeeByPosition(position);
	        assertNotNull(employees);
	        assertEquals(2, employees.size());
	        verify(employeeRepo, times(1)).findByPosition(position);
	    }

	    @Test
	    void testGetEmployeeByMobNo_Success() {
	        String mobNo = "1234567890";
	        Employee employee = new Employee();
	        employee.setMobNo(mobNo);

	        when(employeeRepo.findByMobNo(mobNo)).thenReturn(employee);

	        Employee foundEmployee = employeeService.getEmployeeByMobNo(mobNo);

	        assertNotNull(foundEmployee);
	        assertEquals(mobNo, foundEmployee.getMobNo());
	        verify(employeeRepo, times(1)).findByMobNo(mobNo);
	    }

	

	    @Test
	    void testGetEmployeeByName() {
	        String name = "vidhya";
	        Employee employee1 = new Employee();
	        Employee employee2 = new Employee();

	        when(employeeRepo.findByEmployeeName(name)).thenReturn(Arrays.asList(employee1, employee2));

	        List<Employee> employees = employeeService.getEmployeeByName(name);

	        assertNotNull(employees);
	        assertEquals(2, employees.size());
	        verify(employeeRepo, times(1)).findByEmployeeName(name);
	    }

	    @Test
	    void testGetEmployeeByEmployeeId_Success() {

	        long employeeId = 1L;
	        Employee employee = new Employee();
	        employee.setEmployeeId(employeeId);

	        when(employeeRepo.findByEmployeeId(employeeId)).thenReturn(employee);
	        Employee foundEmployee = employeeService.getEmployeeByEmployeeId(employeeId);

	        assertNotNull(foundEmployee);
	        assertEquals(employeeId, foundEmployee.getEmployeeId());
	        verify(employeeRepo, times(1)).findByEmployeeId(employeeId);
	    }

	   

}
