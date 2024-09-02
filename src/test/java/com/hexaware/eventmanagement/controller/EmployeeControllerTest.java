package com.hexaware.eventmanagement.controller;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.hexaware.eventmanagement.dto.EmployeeDTO;
import com.hexaware.eventmanagement.entity.Employee;
import com.hexaware.eventmanagement.service.IEmployeeServices;

@WebMvcTest(EmployeeController.class)
class EmployeeControllerTest {

	@Autowired
    private MockMvc mockMvc;

    @MockBean
    private IEmployeeServices employeeServices;

    @Autowired
    private ObjectMapper objectMapper;

    private EmployeeDTO employeeDTO;
    private Employee employee;

    @BeforeEach
    void setUp() {
        employeeDTO = new EmployeeDTO();
        employeeDTO.setEmployeeId(1L);
        employeeDTO.setEmployeeName("vidhya");
        employeeDTO.setEmail("vidhya@example.com");
        employeeDTO.setPosition("Developer");
        employeeDTO.setDepartment("IT");
        employeeDTO.setDoj(LocalDate.of(2020, 1, 1));
        employeeDTO.setMobNo("9876543210");

        employee = new Employee();
        employee.setEmployeeId(1L);
        employee.setEmployeeName("vidhya");
        employee.setEmail("vidhya@example.com");
        employee.setPosition("Developer");
        employee.setDepartment("IT");
        employee.setDoj(LocalDate.of(2020, 1, 1));
        employee.setMobNo("9876543210");
    }

    @Test
    void testAddEmployee() throws Exception {
        Mockito.when(employeeServices.addEmployee(Mockito.any(EmployeeDTO.class))).thenReturn(employee);

        mockMvc.perform(post("/api/v1/employees/add")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(employeeDTO)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.employeeId").value(1L))
                .andExpect(jsonPath("$.employeeName").value("vidhya"))
                .andExpect(jsonPath("$.email").value("vidhya@example.com"))
                .andExpect(jsonPath("$.position").value("Developer"))
                .andExpect(jsonPath("$.department").value("IT"))
                .andExpect(jsonPath("$.doj").value("2020-01-01"))
                .andExpect(jsonPath("$.mobNo").value("9876543210"));
    }

    @Test
    void testUpdateEmployee() throws Exception {
        Mockito.when(employeeServices.updateEmployee(Mockito.any(EmployeeDTO.class))).thenReturn(employee);

        mockMvc.perform(put("/api/v1/employees/update")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(employeeDTO)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.employeeId").value(1L))
                .andExpect(jsonPath("$.employeeName").value("vidhya"))
                .andExpect(jsonPath("$.email").value("vidhya@example.com"))
                .andExpect(jsonPath("$.position").value("Developer"))
                .andExpect(jsonPath("$.department").value("IT"))
                .andExpect(jsonPath("$.doj").value("2020-01-01"))
                .andExpect(jsonPath("$.mobNo").value("9876543210"));
    }

    @Test
    void testDeleteEmployee() throws Exception {
        Mockito.doNothing().when(employeeServices).deleteByEmployeeId(1L);

        mockMvc.perform(delete("/api/v1/employees/delete/1"))
                .andExpect(status().isOk());
    }

    @Test
    void testGetEmployeeByEmail() throws Exception {
        Mockito.when(employeeServices.getEmployeeByEmail("vidhya@example.com")).thenReturn(employeeDTO);

        mockMvc.perform(get("/api/v1/employees/get/email/vidhya@example.com")
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.employeeId").value(1L))
                .andExpect(jsonPath("$.employeeName").value("vidhya"))
                .andExpect(jsonPath("$.email").value("vidhya@example.com"))
                .andExpect(jsonPath("$.position").value("Developer"))
                .andExpect(jsonPath("$.department").value("IT"))
                .andExpect(jsonPath("$.doj").value("2020-01-01"))
                .andExpect(jsonPath("$.mobNo").value("9876543210"));
    }

    @Test
    void testGetEmployeeByPosition() throws Exception {
        Mockito.when(employeeServices.getEmployeeByPosition("Developer")).thenReturn(Arrays.asList(employee));

        mockMvc.perform(get("/api/v1/employees/get/position/Developer")
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].employeeId").value(1L))
                .andExpect(jsonPath("$[0].employeeName").value("vidhya"))
                .andExpect(jsonPath("$[0].email").value("vidhya@example.com"))
                .andExpect(jsonPath("$[0].position").value("Developer"))
                .andExpect(jsonPath("$[0].department").value("IT"))
                .andExpect(jsonPath("$[0].doj").value("2020-01-01"))
                .andExpect(jsonPath("$[0].mobNo").value("9876543210"));
    }

    @Test
    void testGetEmployeeByMobNo() throws Exception {
        Mockito.when(employeeServices.getEmployeeByMobNo("9876543210")).thenReturn(employee);

        mockMvc.perform(get("/api/v1/employees/get/mobile/9876543210")
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.employeeId").value(1L))
                .andExpect(jsonPath("$.employeeName").value("vidhya"))
                .andExpect(jsonPath("$.email").value("vidhya@example.com"))
                .andExpect(jsonPath("$.position").value("Developer"))
                .andExpect(jsonPath("$.department").value("IT"))
                .andExpect(jsonPath("$.doj").value("2020-01-01"))
                .andExpect(jsonPath("$.mobNo").value("9876543210"));
    }

    @Test
    void testGetEmployeeByName() throws Exception {
        Mockito.when(employeeServices.getEmployeeByName("vidhya")).thenReturn(Arrays.asList(employee));

        mockMvc.perform(get("/api/v1/employees/get/name/vidhya")
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].employeeId").value(1L))
                .andExpect(jsonPath("$[0].employeeName").value("vidhya"))
                .andExpect(jsonPath("$[0].email").value("vidhya@example.com"))
                .andExpect(jsonPath("$[0].position").value("Developer"))
                .andExpect(jsonPath("$[0].department").value("IT"))
                .andExpect(jsonPath("$[0].doj").value("2020-01-01"))
                .andExpect(jsonPath("$[0].mobNo").value("9876543210"));
    }

    @Test
    void testGetEmployeeById() throws Exception {
        Mockito.when(employeeServices.getEmployeeByEmployeeId(1L)).thenReturn(employee);

        mockMvc.perform(get("/api/v1/employees/get/id/1")
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.employeeId").value(1L))
                .andExpect(jsonPath("$.employeeName").value("vidhya"))
                .andExpect(jsonPath("$.email").value("vidhya@example.com"))
                .andExpect(jsonPath("$.position").value("Developer"))
                .andExpect(jsonPath("$.department").value("IT"))
                .andExpect(jsonPath("$.doj").value("2020-01-01"))
                .andExpect(jsonPath("$.mobNo").value("9876543210"));
    }

    @Test
    void testGetAllEmployees() throws Exception {
        List<Employee> employees = Arrays.asList(employee);

        Mockito.when(employeeServices.getAllEmployees()).thenReturn(employees);

        mockMvc.perform(get("/api/v1/employees/getall")
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].employeeId").value(1L))
                .andExpect(jsonPath("$[0].employeeName").value("vidhya"))
                .andExpect(jsonPath("$[0].email").value("vidhya@example.com"))
                .andExpect(jsonPath("$[0].position").value("Developer"))
                .andExpect(jsonPath("$[0].department").value("IT"))
                .andExpect(jsonPath("$[0].doj").value("2020-01-01"))
                .andExpect(jsonPath("$[0].mobNo").value("9876543210"));
    }
    

}
