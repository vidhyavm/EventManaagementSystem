package com.hexaware.eventmanagement.controller;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.Arrays;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.hexaware.eventmanagement.dto.EventDTO;
import com.hexaware.eventmanagement.entity.Employee;
import com.hexaware.eventmanagement.entity.Event;
import com.hexaware.eventmanagement.service.IEventServices;


@WebMvcTest(EventController.class)
class EventControllerTest {

	 @Autowired
	    private MockMvc mockMvc;

	    @MockBean
	    private IEventServices service;

	    private Event event;
	    private EventDTO eventDTO;

	    @BeforeEach
	    void setUp() {
	        MockitoAnnotations.openMocks(this);

	        Employee organizer = new Employee();
	        organizer.setEmployeeId(1L);

	        event = new Event(100000L, "happy hours", "for myself", "New York", organizer, null);
	        
	        eventDTO = new EventDTO();
	        eventDTO.setTitle("happy hours");
	        eventDTO.setDescription("for myself");
	        eventDTO.setLocation("New York");
	        eventDTO.setOrganizerId(1L);
	    }

	    @Test
	    void testCreateEvent() throws Exception {
	        when(service.createEvent(any(EventDTO.class))).thenReturn(event);

	        mockMvc.perform(post("/api/v1/events/add")
	                .contentType(MediaType.APPLICATION_JSON)
	                .content(new ObjectMapper().writeValueAsString(eventDTO)))
	                .andExpect(status().isOk())
	                .andExpect(jsonPath("$.title").value("happy hours"))
	                .andExpect(jsonPath("$.location").value("New York"));
	    }

	    @Test
	    void testUpdateEvent() throws Exception {
	        when(service.updateEvent(any(EventDTO.class))).thenReturn(event);

	        mockMvc.perform(put("/api/v1/events/update")
	                .contentType(MediaType.APPLICATION_JSON)
	                .content(new ObjectMapper().writeValueAsString(eventDTO)))
	                .andExpect(status().isOk())
	                .andExpect(jsonPath("$.title").value("happy hours"))
	                .andExpect(jsonPath("$.location").value("New York"));
	    }

	    @Test
	    void testDeleteByEventId() throws Exception {
	        mockMvc.perform(delete("/api/v1/events/delete/100000")
	                .accept(MediaType.APPLICATION_JSON))
	                .andExpect(status().isOk());
	    }

	    @Test
	    void testGetEventByEventTitle() throws Exception {
	        List<Event> events = Arrays.asList(event);
	        when(service.getEventByEventTitle("happy hours")).thenReturn(events);

	        mockMvc.perform(get("/api/v1/events/title/happy hours")
	                .accept(MediaType.APPLICATION_JSON))
	                .andExpect(status().isOk())
	                .andExpect(jsonPath("$[0].title").value("happy hours"));
	    }

	    @Test
	    void testGetEventByLocation() throws Exception {
	        List<Event> events = Arrays.asList(event);
	        when(service.getEventByLocation("New York")).thenReturn(events);

	        mockMvc.perform(get("/api/v1/events/location/New York")
	                .accept(MediaType.APPLICATION_JSON))
	                .andExpect(status().isOk())
	                .andExpect(jsonPath("$[0].location").value("New York"));
	    }

	    @Test
	    void testGetAllEvent() throws Exception {
	        List<Event> events = Arrays.asList(event);
	        when(service.getAllEvent()).thenReturn(events);

	        mockMvc.perform(get("/api/v1/events/getall")
	                .accept(MediaType.APPLICATION_JSON))
	                .andExpect(status().isOk())
	                .andExpect(jsonPath("$[0].title").value("happy hours"));
	    }

	    @Test
	    void testGetEventById() throws Exception {
	        when(service.getEventById(100000L)).thenReturn(event);

	        mockMvc.perform(get("/api/v1/events/get/id/100000")
	                .accept(MediaType.APPLICATION_JSON))
	                .andExpect(status().isOk())
	                .andExpect(jsonPath("$.eventId").value(100000L))
	                .andExpect(jsonPath("$.title").value("happy hours"));
	    }

}
