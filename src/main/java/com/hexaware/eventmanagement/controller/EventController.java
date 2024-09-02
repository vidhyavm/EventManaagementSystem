package com.hexaware.eventmanagement.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.hexaware.eventmanagement.dto.EventDTO;
import com.hexaware.eventmanagement.entity.Event;
import com.hexaware.eventmanagement.service.IEventServices;

@RestController
@RequestMapping("/api/v1/events")
public class EventController {
	
	
	@Autowired
	IEventServices service;
	
	
	@PostMapping(value="/add")
	public Event createEvent(@RequestBody EventDTO eventDTO) {
		return service.createEvent(eventDTO);
		
	}
	
	
	@PutMapping(value="/update")
	public Event updateEvent(@RequestBody EventDTO eventDTO) {
		return service.updateEvent(eventDTO);
		
	}
	
	@DeleteMapping(value="/delete/{eventId}",produces = "application/json")
	public void deleteByEventId(@PathVariable("eventId") long eventId) {
		service.deleteByEventId(eventId);
		
	}
	
	@GetMapping(value="/title/{title}",produces = "application/json")
	public List<Event> getEventByEventTitle(@PathVariable("title")  String title){
		return service.getEventByEventTitle(title);
		
	}
	
	@GetMapping(value="/location/{location}",produces = "application/json")
	public List<Event> getEventByLocation(@PathVariable("location") String location){
		return service.getEventByLocation(location);
		
	}
	
	@GetMapping(value="/getall",produces = "application/json")
	public List<Event> getAllEvent(){
		return service.getAllEvent();
		
	}
	
	@GetMapping(value="/get/id/{eventId}",produces = "application/json")
	public Event getEventById(@PathVariable("eventId") long eventId) {
		return service.getEventById(eventId);
	}


}
