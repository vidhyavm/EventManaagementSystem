package com.hexaware.eventmanagement.service;

import java.util.List;

import com.hexaware.eventmanagement.dto.EventDTO;
import com.hexaware.eventmanagement.entity.Event;

public interface IEventServices {
	
	public Event createEvent(EventDTO eventDTO);
	public Event updateEvent(EventDTO eventDTO);
	public void deleteByEventId(long eventId);
	public List<Event> getEventByEventTitle(String title);
	public List<Event> getEventByLocation(String location);
	public Event getEventById(long eventId);
	public List<Event> getAllEvent();

}
