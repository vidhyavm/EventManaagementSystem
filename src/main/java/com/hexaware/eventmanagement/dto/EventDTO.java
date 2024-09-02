package com.hexaware.eventmanagement.dto;

import com.hexaware.eventmanagement.entity.Employee;

public class EventDTO {
	
	private long eventId;
	private String title;
	private String description;
	private String location;
	private Employee organizer;
	
	
	
	
	public EventDTO(long eventId, String title, String description, String location, Employee organizer) {
		super();
		this.eventId = eventId;
		this.title = title;
		this.description = description;
		this.location = location;
		this.organizer = organizer;
	}




	@Override
	public String toString() {
		return "EventDTO [eventId=" + eventId + ", title=" + title + ", description=" + description + ", location="
				+ location + ", organizer=" + organizer + "]";
	}




	public long getEventId() {
		return eventId;
	}




	public void setEventId(long eventId) {
		this.eventId = eventId;
	}




	public String getTitle() {
		return title;
	}




	public void setTitle(String title) {
		this.title = title;
	}




	public String getDescription() {
		return description;
	}




	public void setDescription(String description) {
		this.description = description;
	}




	public String getLocation() {
		return location;
	}




	public void setLocation(String location) {
		this.location = location;
	}




	public Employee getOrganizer() {
		return organizer;
	}




	public void setOrganizer(Employee organizer) {
		this.organizer = organizer;
	}




	public EventDTO() {
		super();
		// TODO Auto-generated constructor stub
	}




	public void setOrganizerId(long l) {
		// TODO Auto-generated method stub
		
	}
	
	
	
	



	
	
	
	

}
