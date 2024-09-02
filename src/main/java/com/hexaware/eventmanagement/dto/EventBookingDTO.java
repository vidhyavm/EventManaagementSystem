package com.hexaware.eventmanagement.dto;


import com.hexaware.eventmanagement.entity.Employee;
import com.hexaware.eventmanagement.entity.Event;

public class EventBookingDTO {
	
	private long regId;
	private String regName;
	private String comments;
	private Event event;
	private Employee employee;
	
	
	public EventBookingDTO(long regId, String regName, String comments, Event event,
			Employee employee) {
		super();
		this.regId = regId;
		this.regName = regName;
		this.comments = comments;
		this.event = event;
		this.employee = employee;
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
	
	public long getRegId() {
		return regId;
	}
	public void setRegId(long regId) {
		this.regId = regId;
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
	
	@Override
	public String toString() {
		return "EventBookingDTO [regId=" + regId + ", regName=" + regName + ", regTime=" +  ", comments="
				+ comments + ", event=" + event + ", employee=" + employee + "]";
	}
	public EventBookingDTO() {
		super();
		// TODO Auto-generated constructor stub
	}
	public void setEmployeeId(long l) {
		// TODO Auto-generated method stub
		
	}
	public void setEventId(long l) {
		// TODO Auto-generated method stub
		
	}
	
	
		
	
	
	
	

}
