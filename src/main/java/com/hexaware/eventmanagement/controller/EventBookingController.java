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

import com.hexaware.eventmanagement.dto.EventBookingDTO;
import com.hexaware.eventmanagement.entity.EventBooking;
import com.hexaware.eventmanagement.service.IEventBookingServices;

@RestController
@RequestMapping("/api/v1/eventbookings")
public class EventBookingController {
	
	@Autowired
	IEventBookingServices service;
	
	
	@PostMapping(value="/eventreg")
	public EventBooking createEventBooking(@RequestBody EventBookingDTO eventBookingDTO) {
		return service.createEventBooking(eventBookingDTO);
	}
	
	@PutMapping(value="/update")
	public EventBooking updateEventBooking(@RequestBody EventBookingDTO eventBookingDTO) {
		return service.updateEventBooking(eventBookingDTO);
	}
	
	@GetMapping(value="/event/{regId}",produces = "application/json")
	public EventBooking getEventBookingByRegId(@PathVariable("regId") long regId) {
		return service.getEventBookingByRegId(regId);
		
	}
	
	@GetMapping(value="/getall",produces = "application/json")
	public List<EventBooking> getAllEventBooking(){
		return service.getAllEventBooking();
		
	}
	
	@DeleteMapping(value="/delete/{regId}",produces = "application/json")
	public void deleteEventBookingByRegId(@PathVariable("regId") long regId) {
		 service.deleteEventBookingByRegId(regId);
		
	}
	

	
}
