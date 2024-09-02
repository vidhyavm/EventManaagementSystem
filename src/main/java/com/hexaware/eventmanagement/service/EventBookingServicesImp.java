package com.hexaware.eventmanagement.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hexaware.eventmanagement.dto.EventBookingDTO;
import com.hexaware.eventmanagement.entity.EventBooking;
import com.hexaware.eventmanagement.exception.EventBookingNotFoundException;
import com.hexaware.eventmanagement.repository.EmployeeRepo;
import com.hexaware.eventmanagement.repository.EventBookingRepo;
import com.hexaware.eventmanagement.repository.EventRepo;


@Service
public class EventBookingServicesImp implements IEventBookingServices {

	@Autowired
	EventBookingRepo bookingRepo;
	
	@Autowired
	EmployeeRepo employeeRepo;
	
	@Autowired 
	EventRepo eventRepo;

	@Override
	public EventBooking createEventBooking(EventBookingDTO eventBookingDTO) {
		try {
            EventBooking eventBooking=new EventBooking();
            eventBooking.setRegName(eventBookingDTO.getRegName());
            eventBooking.setComments(eventBookingDTO.getComments());
            eventBooking.setEmployee(eventBookingDTO.getEmployee());
            eventBooking.setEvent(eventBookingDTO.getEvent());
            EventBooking createEventBooking=bookingRepo.save(eventBooking);
            System.out.println("Event Registered successfully");
            return createEventBooking;
            
        } catch (Exception e) {
            throw new RuntimeException("Error creating event booking", e);
        }
  
	}

	@Override
	public EventBooking updateEventBooking(EventBookingDTO eventBookingDTO) {
		try {
            if(!bookingRepo.existsById(eventBookingDTO.getRegId())) {
            	throw new EventBookingNotFoundException("Event Booking not found with reg Id: "+eventBookingDTO.getRegId());
            }
            
   
            EventBooking eventBooking=new EventBooking();
			eventBooking.setRegId(eventBookingDTO.getRegId());
            eventBooking.setRegName(eventBookingDTO.getRegName());
            eventBooking.setComments(eventBookingDTO.getComments());
            eventBooking.setEmployee(eventBookingDTO.getEmployee());
            eventBooking.setEvent(eventBookingDTO.getEvent());
            EventBooking updateEventBooking=bookingRepo.save(eventBooking);
            return updateEventBooking;

        } catch (Exception e) {
            throw new RuntimeException("Error creating event booking", e);
        }
	}

	@Override
	public List<EventBooking> getAllEventBooking() {
		try {
			List<EventBooking> eventBookings=bookingRepo.findAll();
			return eventBookings;
		}
		catch(Exception e) {
			throw new RuntimeException("Error getting all event bookings",e);
		}
		
	}

	@Override
	public EventBooking getEventBookingByRegId(long regId) {
		try {
			EventBooking eventbooking=bookingRepo.findById(regId).orElseThrow(
					()-> new EventBookingNotFoundException("Event Booking not found with reg Id:"+ regId ));
			
		return eventbooking;
		}
		catch(Exception e) {
			throw new RuntimeException("Error getting Event booking by reg id",e);
		}
			
	}

	@Override
	public void deleteEventBookingByRegId(long regId) {
		try {
			if(!bookingRepo.existsById(regId)) {
				throw new EventBookingNotFoundException("Event Booking not found with reg_id:"+regId);
			}
			bookingRepo.deleteById(regId);
			
		}catch (Exception e) {
           
            throw new RuntimeException("Error deleting Event Booing", e);
        }
		
	}


	
	

	
}	