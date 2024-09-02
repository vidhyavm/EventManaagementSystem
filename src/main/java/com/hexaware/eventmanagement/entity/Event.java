package com.hexaware.eventmanagement.entity;

import java.util.HashSet;
import java.util.Set;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;


@Entity
public class Event {

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "event_seq_generator")
	@SequenceGenerator(name = "event_seq_generator", sequenceName = "event_seq", allocationSize = 1,initialValue =100000)
	private long eventId;
	
	@NotEmpty(message = "Title cannot be empty")
	@Size(max = 50, message = "Title cannot exceed 50 characters")
	private String title;

	@NotEmpty(message="Description cannot be empty")
	@Size(max = 250, message = "Description cannot exceed 250 characters")
	private String description;
	
	@NotEmpty(message="Location cannot be Empty")
	@Size(max = 50, message = "Location cannot exceed 50 characters")
	private String location;
	
	
	
	@JsonBackReference("employee-organizer")
    @ManyToOne
    @JoinColumn(name = "organizer_id", referencedColumnName = "employeeId")
    private Employee organizer;
	
	@JsonManagedReference("event-bookings")
    @OneToMany(mappedBy = "event", cascade = CascadeType.ALL)
    private Set<EventBooking> eventBookings = new HashSet<>();
	
	
	public Event() {
		super();
		// TODO Auto-generated constructor stub
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


	public Set<EventBooking> getEventBookings() {
		return eventBookings;
	}


	public void setEventBookings(Set<EventBooking> eventBookings) {
		this.eventBookings = eventBookings;
	}


	@Override
	public String toString() {
		return "Event [eventId=" + eventId + ", title=" + title + ", description=" + description + ", location="
				+ location + ", organizer=" + organizer + ", eventBookings=" + eventBookings + "]";
	}


	public Event(long eventId, String title, String description, String location, Employee organizer,
			Set<EventBooking> eventBookings) {
		super();
		this.eventId = eventId;
		this.title = title;
		this.description = description;
		this.location = location;
		this.organizer = organizer;
		this.eventBookings = eventBookings;
	}


	
		
	


	
	
}
