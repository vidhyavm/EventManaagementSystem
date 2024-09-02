package com.hexaware.eventmanagement.service;

import java.util.List;

import com.hexaware.eventmanagement.dto.EventBookingDTO;
import com.hexaware.eventmanagement.entity.EventBooking;

public interface IEventBookingServices {
	
	public EventBooking createEventBooking(EventBookingDTO eventBookingDTO);
	public EventBooking updateEventBooking(EventBookingDTO eventBookingDTO);
	public List<EventBooking> getAllEventBooking();
	public EventBooking getEventBookingByRegId(long regId);
	public void deleteEventBookingByRegId(long regId);

		
}
