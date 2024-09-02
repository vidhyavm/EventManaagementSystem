package com.hexaware.eventmanagement.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hexaware.eventmanagement.dto.EventDTO;
import com.hexaware.eventmanagement.entity.Event;
import com.hexaware.eventmanagement.exception.EventNotFoundException;
import com.hexaware.eventmanagement.repository.EmployeeRepo;
import com.hexaware.eventmanagement.repository.EventRepo;

@Service
public class EventServicesImp implements IEventServices {

	@Autowired
	EventRepo eventRepo;
	
	@Autowired
	EmployeeRepo employeeRepo;
	
	@Override
	public Event createEvent(EventDTO eventDTO) {
		try {
			Event event=new Event();
			event.setTitle(eventDTO.getTitle());
			event.setDescription(eventDTO.getDescription());
			event.setLocation(eventDTO.getLocation());
			event.setOrganizer(eventDTO.getOrganizer());
	        Event createEvent=eventRepo.save(event);
	         
		return createEvent;
		}
		catch(Exception e) {
			throw new RuntimeException("Error Creating event",e);
			
		}
	}

	@Override
	public Event updateEvent(EventDTO eventDTO) {
		try {
			if(!eventRepo.existsById(eventDTO.getEventId()))
				throw new EventNotFoundException("Event Not Found With Id:"+eventDTO.getEventId());
			Event event=new Event();
			event.setEventId(eventDTO.getEventId());
			event.setTitle(eventDTO.getTitle());
			event.setDescription(eventDTO.getDescription());
			event.setLocation(eventDTO.getLocation());
			
	         event.setOrganizer(eventDTO.getOrganizer());
			
			Event updateEvent=eventRepo.save(event);
			return updateEvent;
			
		}
		catch(Exception e) {
			throw new RuntimeException("Error updating Event",e);
			
		}
		
		
	}

	@Override
	public void deleteByEventId(long eventId) {
		try {
			if(!eventRepo.existsById(eventId)) {
				throw new EventNotFoundException("Event not Found with ID: "+eventId);
			}
			eventRepo.deleteById(eventId);
		}
		catch(Exception e) {
			throw new RuntimeException("Error Deleting policy",e);
		}
		
	}

	@Override
	public List<Event> getEventByEventTitle(String title) {
		try {
			List<Event> event=eventRepo.findByTitle(title);
			return event;
		}
		catch(Exception e) {
			throw new RuntimeException("Error getting event by title",e);
		}
		
	}

	@Override
	public List<Event> getEventByLocation(String location) {
		try {
			List<Event> event=eventRepo.findByLocation(location);
			return event;
		}
		catch(Exception e) {
			throw new RuntimeException("Error getting event by location",e);
		}
	}

	@Override
	public List<Event> getAllEvent() {
		try {
			List<Event> event=eventRepo.findAll();
			return event;
		}
		 catch (Exception e) 
        {
           
            throw new RuntimeException("Error getting all events", e);
        }
	}

	@Override
	public Event getEventById(long eventId) {
		try {
			Event event=eventRepo.findByEventId(eventId);
			if(event==null) {
				throw new EventNotFoundException("Event not found with Id:"+eventId);
			}
			return event;
		}
		catch(Exception e) {
			throw new RuntimeException("Error getting Event By Id",e);
		}
		
	}

}
