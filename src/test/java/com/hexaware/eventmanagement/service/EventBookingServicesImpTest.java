package com.hexaware.eventmanagement.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;

import com.hexaware.eventmanagement.dto.EventBookingDTO;
import com.hexaware.eventmanagement.entity.Employee;
import com.hexaware.eventmanagement.entity.Event;
import com.hexaware.eventmanagement.entity.EventBooking;
import com.hexaware.eventmanagement.repository.EventBookingRepo;


@SpringBootTest
class EventBookingServicesImpTest {
	
	@BeforeAll
	static void setUpBeforeClass() throws Exception {
	}

	@Mock
	private EventBookingRepo bookingRepo;
	
	
	@InjectMocks
	private EventBookingServicesImp eventBookingService;
	
	 @BeforeEach
	    void setUp() {
	        MockitoAnnotations.openMocks(this);
	    }
	 @Test
	    void testCreateEventBooking_Success() {
	        EventBookingDTO eventBookingDTO = new EventBookingDTO();
	        eventBookingDTO.setRegName("Kavitha");
	        eventBookingDTO.setComments("Looking forward to the event");

	        Employee employee = new Employee();
	        Event event = new Event();

	        eventBookingDTO.setEmployee(employee);
	        eventBookingDTO.setEvent(event);

	        EventBooking eventBooking = new EventBooking();
	        when(bookingRepo.save(any(EventBooking.class))).thenReturn(eventBooking);

	        EventBooking createdBooking = eventBookingService.createEventBooking(eventBookingDTO);

	        assertNotNull(createdBooking);
	        verify(bookingRepo, times(1)).save(any(EventBooking.class));
	    }

	    @Test
	    void testUpdateEventBooking_Success() {
	      
	        EventBookingDTO eventBookingDTO = new EventBookingDTO();
	        eventBookingDTO.setRegId(1L);
	        eventBookingDTO.setRegName("Kavitha M");
	        eventBookingDTO.setComments("Waiting for Event");

	        Employee employee = new Employee();
	        Event event = new Event();

	        eventBookingDTO.setEmployee(employee);
	        eventBookingDTO.setEvent(event);

	        when(bookingRepo.existsById(eventBookingDTO.getRegId())).thenReturn(true);
	        when(bookingRepo.save(any(EventBooking.class))).thenReturn(new EventBooking());

	        EventBooking updatedBooking = eventBookingService.updateEventBooking(eventBookingDTO);

	        assertNotNull(updatedBooking);
	        verify(bookingRepo, times(1)).save(any(EventBooking.class));
	    }

	    
	    @Test
	    void testGetAllEventBookings() {
	      
	        EventBooking eventBooking1 = new EventBooking();
	        EventBooking eventBooking2 = new EventBooking();

	        when(bookingRepo.findAll()).thenReturn(Arrays.asList(eventBooking1, eventBooking2));

	        List<EventBooking> eventBookings = eventBookingService.getAllEventBooking();

	        assertNotNull(eventBookings);
	        assertEquals(2, eventBookings.size());
	        verify(bookingRepo, times(1)).findAll();
	    }

	    @Test
	    void testGetEventBookingByRegId_Success() {
	      
	        long regId = 1L;
	        EventBooking eventBooking = new EventBooking();
	        when(bookingRepo.findById(regId)).thenReturn(Optional.of(eventBooking));

	        EventBooking foundBooking = eventBookingService.getEventBookingByRegId(regId);

	        assertNotNull(foundBooking);
	        assertEquals(eventBooking, foundBooking);
	        verify(bookingRepo, times(1)).findById(regId);
	    }

	   
	    @Test
	    void testDeleteEventBookingByRegId_Success() {
	      
	        long regId = 1L;
	        when(bookingRepo.existsById(regId)).thenReturn(true);

	        eventBookingService.deleteEventBookingByRegId(regId);

	        verify(bookingRepo, times(1)).deleteById(regId);
	    }

	   

}
