package com.hexaware.eventmanagement.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.Arrays;
import java.util.List;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import com.hexaware.eventmanagement.dto.EventDTO;
import com.hexaware.eventmanagement.entity.Employee;
import com.hexaware.eventmanagement.entity.Event;
import com.hexaware.eventmanagement.exception.EventNotFoundException;
import com.hexaware.eventmanagement.repository.EventRepo;

class EventServicesImpTest {

	@BeforeAll
	static void setUpBeforeClass() throws Exception {
	}
	
	@Mock
	private EventRepo eventRepo;
	
	@InjectMocks
	private EventServicesImp service;
	
	
	 @BeforeEach
	    void setUp() {
	        MockitoAnnotations.openMocks(this);
	        
	    }

	   @Test
	    void testCreateEvent() {
	        // Arrange
	        Employee organizer = new Employee();  
	        organizer.setEmployeeId(1L); // 

	        EventDTO eventDTO = new EventDTO();
	        eventDTO.setTitle("LaLaurea");
	        eventDTO.setDescription("Mavricks Graduation Day");
	        eventDTO.setLocation("coimbatore");
	        eventDTO.setOrganizer(organizer); 

	        Event event = new Event();
	        event.setTitle("LaLaurea");
	        event.setDescription("Mavricks Graduation Day");
	        event.setLocation("coimbatore");
	        event.setOrganizer(organizer); 

	        when(eventRepo.save(any(Event.class))).thenReturn(event);

	        Event createdEvent = service.createEvent(eventDTO);

	        assertNotNull(createdEvent);
	        assertEquals(eventDTO.getTitle(), createdEvent.getTitle());
	        assertEquals(eventDTO.getOrganizer(), createdEvent.getOrganizer()); 
	        verify(eventRepo, times(1)).save(any(Event.class));
	    }

	   @Test
	   void testUpdateEvent_Success() {
	       
	       Employee organizer = new Employee();
	       organizer.setEmployeeId(1L);

	       EventDTO eventDTO = new EventDTO();
	       eventDTO.setEventId(1L);
	       eventDTO.setTitle("LaLaurea");
	       eventDTO.setDescription("Mavricks Graduation Day");
	       eventDTO.setLocation("coimbatore");
	       eventDTO.setOrganizer(organizer);

	       Event event = new Event();
	       event.setEventId(1L);
	       event.setTitle("LaLaurea");
	       event.setDescription("Mavricks Graduation Day");
	       event.setLocation("coimbatore");
	       event.setOrganizer(organizer); 

	       when(eventRepo.existsById(eventDTO.getEventId())).thenReturn(true);
	       when(eventRepo.save(any(Event.class))).thenReturn(event);

	       Event updatedEvent = service.updateEvent(eventDTO);

	       assertNotNull(updatedEvent);
	       assertEquals(eventDTO.getEventId(), updatedEvent.getEventId());
	       assertEquals(eventDTO.getOrganizer().getEmployeeId(), updatedEvent.getOrganizer().getEmployeeId()); 
	       verify(eventRepo, times(1)).save(any(Event.class));
	   }


	  
	    @Test
	    void testDeleteByEventId_Success() {
	        long eventId = 1L;
	        when(eventRepo.existsById(eventId)).thenReturn(true);

	        service.deleteByEventId(eventId);

	        verify(eventRepo, times(1)).deleteById(eventId);
	    }

	    
	    @Test
	    void testGetEventByEventTitle() {

	        String title = "Happy Hours";
	        Employee organizer = new Employee();
	        organizer.setEmployeeId(1L);

	        Event event1 = new Event();
	        event1.setTitle(title);
	        event1.setOrganizer(organizer);

	        Event event2 = new Event();
	        event2.setTitle(title);
	        event2.setOrganizer(organizer);

	        when(eventRepo.findByTitle(title)).thenReturn(Arrays.asList(event1, event2));

	        List<Event> events = service.getEventByEventTitle(title);

	        assertNotNull(events);
	        assertEquals(2, events.size());
	        assertEquals(organizer, events.get(0).getOrganizer());
	        verify(eventRepo, times(1)).findByTitle(title);
	    }

	    @Test
	    void testGetEventByLocation() {
	    	
	        String location = "coimbatore";
	        Employee organizer = new Employee();
	        organizer.setEmployeeId(1L);

	        Event event1 = new Event();
	        event1.setLocation(location);
	        event1.setOrganizer(organizer);

	        Event event2 = new Event();
	        event2.setLocation(location);
	        event2.setOrganizer(organizer);

	        when(eventRepo.findByLocation(location)).thenReturn(Arrays.asList(event1, event2));

	        List<Event> events = service.getEventByLocation(location);
	        assertNotNull(events);
	        assertEquals(2, events.size());
	        assertEquals(organizer, events.get(0).getOrganizer()); 
	        verify(eventRepo, times(1)).findByLocation(location);
	    }

	    @Test
	    void testGetAllEvent() {
	     
	        Employee organizer = new Employee();
	        organizer.setEmployeeId(1L);

	        Event event1 = new Event();
	        event1.setOrganizer(organizer);

	        Event event2 = new Event();
	        event2.setOrganizer(organizer);

	        when(eventRepo.findAll()).thenReturn(Arrays.asList(event1, event2));

	        List<Event> events = service.getAllEvent();

	        assertNotNull(events);
	        assertEquals(2, events.size());
	        assertEquals(organizer, events.get(0).getOrganizer()); 
	        verify(eventRepo, times(1)).findAll();
	    }

	    @Test
	    void testGetEventById_Success() {
	    
	        long eventId = 1L;
	        Employee organizer = new Employee();
	        organizer.setEmployeeId(1L);

	        Event event = new Event();
	        event.setEventId(eventId);
	        event.setOrganizer(organizer);

	        when(eventRepo.findByEventId(eventId)).thenReturn(event);

	        Event foundEvent = service.getEventById(eventId);

	        assertNotNull(foundEvent);
	        assertEquals(eventId, foundEvent.getEventId());
	        assertEquals(organizer, foundEvent.getOrganizer()); 
	        verify(eventRepo, times(1)).findByEventId(eventId);
	    }

	   
}
