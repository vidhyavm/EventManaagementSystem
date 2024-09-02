package com.hexaware.eventmanagement.dto;

import java.time.LocalDate;

import com.hexaware.eventmanagement.entity.Address;




public class EmployeeDTO {
	
	private long employeeId;
	private String employeeName;
	private String email;
	private String position;
	private String department;
	private LocalDate doj;
	private String mobNo;
	
	private Address address;
	
	public EmployeeDTO(long employeeId, String employeeName, String email, String position, String department,
			LocalDate doj, String mobNo, Address address) {
		super();
		this.employeeId = employeeId;
		this.employeeName = employeeName;
		this.email = email;
		this.position = position;
		this.department = department;
		this.doj = doj;
		this.mobNo = mobNo;
		this.address = address;
	}
	public Address getAddress() {
		return address;
	}
	public void setAddress(Address address) {
		this.address = address;
	}
	public long getEmployeeId() {
		return employeeId;
	}
	public void setEmployeeId(long employeeId) {
		this.employeeId = employeeId;
	}
	public String getEmployeeName() {
		return employeeName;
	}
	public void setEmployeeName(String employeeName) {
		this.employeeName = employeeName;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getPosition() {
		return position;
	}
	public void setPosition(String position) {
		this.position = position;
	}
	public String getDepartment() {
		return department;
	}
	public void setDepartment(String department) {
		this.department = department;
	}
	public LocalDate getDoj() {
		return doj;
	}
	public void setDoj(LocalDate doj) {
		this.doj = doj;
	}
	public String getMobNo() {
		return mobNo;
	}
	public void setMobNo(String mobNo) {
		this.mobNo = mobNo;
	}

	
	

	@Override
	public String toString() {
		return "EmployeeDTO [employeeId=" + employeeId + ", employeeName=" + employeeName + ", email=" + email
				+ ", position=" + position + ", department=" + department + ", doj=" + doj + ", mobNo=" + mobNo
				+ ", address=" + address + "]";
	}
	public EmployeeDTO() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	
	

}
