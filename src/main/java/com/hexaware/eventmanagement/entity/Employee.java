package com.hexaware.eventmanagement.entity;

import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

import com.fasterxml.jackson.annotation.JsonManagedReference;

import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Past;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;


@Entity
public class Employee {
	
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE,generator = "employee_seq_generator")
	@SequenceGenerator(name = "employee_seq_generator", sequenceName = "employee_seq", allocationSize = 1,initialValue =120000)
	private long employeeId;
	
	@NotEmpty(message="employee name cannot be empty")
	@Size(max = 50, message = "First name cannot exceed 50 characters")
    @Pattern(regexp = "^[a-zA-Z\\s]+$", message = "First name can only contain letters and spaces")
	private String employeeName;


	@NotEmpty(message="Email cannot be empty")
	@Email(message="Invalid Email format")
	private String email;
	
	@NotEmpty(message="position cannot be empty")
	@Size(max=50,message="Position cannnot exceed 50 characters")
	private String position;


	@NotEmpty(message="Department cannot be empty")
	@Size(max=50,message="Department cannnot exceed 50 characters")
	private String department;
	

	@Past(message="Date of Join must Be past date")
	private LocalDate doj;
	
	@NotEmpty(message="Mobile number cannot be Empty")
	@Pattern(regexp = "^[6789]\\d{9}$", message = "Invalid Indian mobile number")
	private String mobNo;
	
	
	
	@OneToOne(cascade=CascadeType.ALL)
	@JoinColumn(name="addressId",referencedColumnName="addressId")
	@JsonManagedReference
	private Address address;
	

	 @JsonManagedReference("employee-organizer")
	 @OneToMany(mappedBy = "organizer", cascade = CascadeType.ALL)
	 private Set<Event> organizedEvents = new HashSet<>();
	
	 @JsonManagedReference("employee-bookings")
	 @OneToMany(mappedBy = "employee", cascade = CascadeType.ALL)
     private Set<EventBooking> eventBooking = new HashSet<>();

	public Address getAddress() {
		return address;
	}
	public void setAddress(Address address) {
		this.address = address;
	}
	public Set<Event> getOrganizedEvents() {
		return organizedEvents;
	}
	public void setOrganizedEvents(Set<Event> organizedEvents) {
		this.organizedEvents = organizedEvents;
	}
	public Set<EventBooking> getEventBooking() {
		return eventBooking;
	}
	public void setEventBooking(Set<EventBooking> eventBooking) {
		this.eventBooking = eventBooking;
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
		return "Employee [employeeId=" + employeeId + ", employeeName=" + employeeName + ", email=" + email
				+ ", position=" + position + ", department=" + department + ", doj=" + doj + ", mobNo=" + mobNo + "]";
	}
	public Employee(long employeeId, String employeeName, String email, String position, String department,
			LocalDate doj, String mobNo) {
		super();
		this.employeeId = employeeId;
		this.employeeName = employeeName;
		this.email = email;
		this.position = position;
		this.department = department;
		this.doj = doj;
		this.mobNo = mobNo;
	}
	public Employee() {
		super();
		// TODO Auto-generated constructor stub
	}
	

}
