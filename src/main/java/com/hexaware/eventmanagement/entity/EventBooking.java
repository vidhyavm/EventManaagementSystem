package com.hexaware.eventmanagement.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;

@Entity
public class EventBooking {
	
	
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "eventRegister_seq_generator")
	@SequenceGenerator(name = "eventRegister_seq_generator", sequenceName = "eventRegister_seq", allocationSize = 1, initialValue =110000)
	private long regId;
	
	
	@NotEmpty(message="register name cannnot be empty")
	@Size(max=50,message="Name cannot exceed 50 characters")
	private String regName;
	
	
	@NotEmpty(message="Comments cannot be empty")
	@Size(max=150,message="commenets cannot exceed 150 characters")
	private String comments;
	


	 @JsonBackReference("event-bookings")
	 @ManyToOne
	 @JoinColumn(name = "event_id", referencedColumnName = "eventId")
	 private Event event;


	 @JsonBackReference("employee-bookings")
	 @ManyToOne
	 @JoinColumn(name = "employee_id", referencedColumnName = "employeeId")
	 private Employee employee;
	
	public long getRegId() {
		return regId;
	}


	public void setRegId(long regId) {
		this.regId = regId;
	}


	public String getRegName() {
		return regName;
	}


	public void setRegName(String regName) {
		this.regName = regName;
	}


	

	


	public String getComments() {
		return comments;
	}


	public void setComments(String comments) {
		this.comments = comments;
	}


	public Event getEvent() {
		return event;
	}


	public void setEvent(Event event) {
		this.event = event;
	}


	public Employee getEmployee() {
		return employee;
	}


	public void setEmployee(Employee employee) {
		this.employee = employee;
	}


	public EventBooking(long regId, String regName, String comments, Event event,
			Employee employee) {
		super();
		this.regId = regId;
		this.regName = regName;
		this.comments = comments;
		this.event = event;
		this.employee = employee;
	}


	@Override
	public String toString() {
		return "EventBooking [regId=" + regId + ", regName=" + regName + ", comments="
				+ comments + ", event=" + event + ", employee=" + employee + "]";
	}


	public EventBooking() {
		super();
		// TODO Auto-generated constructor stub
	}

	
	
}
