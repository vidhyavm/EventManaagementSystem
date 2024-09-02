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
import com.hexaware.eventmanagement.dto.EventBookingDTO;
import com.hexaware.eventmanagement.entity.Employee;
import com.hexaware.eventmanagement.entity.Event;
import com.hexaware.eventmanagement.entity.EventBooking;
import com.hexaware.eventmanagement.service.IEmployeeServices;
import com.hexaware.eventmanagement.service.IEventBookingServices;
import com.hexaware.eventmanagement.service.IEventServices;

@WebMvcTest(EventBookingController.class)
class EventBookingControllerTest {

	 @Autowired
	    private MockMvc mockMvc;

	    @MockBean
	    private IEventBookingServices service;
	    
	    @MockBean
	    private IEventServices eventService;
	    
	    @MockBean
	    private IEmployeeServices employeeService;

	    private EventBooking eventBooking;
	    private EventBookingDTO eventBookingDTO;

	    @BeforeEach
	    void setUp() {
	        MockitoAnnotations.openMocks(this);

	        Event event = new Event();
	        event.setEventId(1L);

	        Employee employee = new Employee();
	        employee.setEmployeeId(1L);

	        eventBooking = new EventBooking(110000L, "vidhya", "exciting to participate", event, employee);
	        eventBookingDTO = new EventBookingDTO();
	        eventBookingDTO.setRegName("vidhya");
	        eventBookingDTO.setComments("exciting to participate");
	        eventBookingDTO.setEventId(1L);
	        eventBookingDTO.setEmployeeId(1L);
	    }

	    @Test
	    void testCreateEventBooking() throws Exception {
	        when(service.createEventBooking(any(EventBookingDTO.class))).thenReturn(eventBooking);

	        mockMvc.perform(post("/api/v1/eventbookings/eventreg")
	                .contentType(MediaType.APPLICATION_JSON)
	                .content(new ObjectMapper().writeValueAsString(eventBookingDTO)))
	                .andExpect(status().isOk())
	                .andExpect(jsonPath("$.regName").value("vidhya"))
	                .andExpect(jsonPath("$.comments").value("exciting to participate"));
	    }

	    @Test
	    void testUpdateEventBooking() throws Exception {
	        when(service.updateEventBooking(any(EventBookingDTO.class))).thenReturn(eventBooking);

	        mockMvc.perform(put("/api/v1/eventbookings/update")
	                .contentType(MediaType.APPLICATION_JSON)
	                .content(new ObjectMapper().writeValueAsString(eventBookingDTO)))
	                .andExpect(status().isOk())
	                .andExpect(jsonPath("$.regName").value("vidhya"))
	                .andExpect(jsonPath("$.comments").value("exciting to participate"));
	    }

	    @Test
	    void testGetEventBookingByRegId() throws Exception {
	        when(service.getEventBookingByRegId(110000L)).thenReturn(eventBooking);

	        mockMvc.perform(get("/api/v1/eventbookings/event/110000")
	                .accept(MediaType.APPLICATION_JSON))
	                .andExpect(status().isOk())
	                .andExpect(jsonPath("$.regId").value(110000L))
	                .andExpect(jsonPath("$.regName").value("vidhya"));
	    }

	    @Test
	    void testGetAllEventBooking() throws Exception {
	        List<EventBooking> eventBookingList = Arrays.asList(eventBooking);
	        when(service.getAllEventBooking()).thenReturn(eventBookingList);

	        mockMvc.perform(get("/api/v1/eventbookings/getall")
	                .accept(MediaType.APPLICATION_JSON))
	                .andExpect(status().isOk())
	                .andExpect(jsonPath("$[0].regName").value("vidhya"));
	    }

	    @Test
	    void testDeleteEventBookingByRegId() throws Exception {
	        mockMvc.perform(delete("/api/v1/eventbookings/delete/110000")
	                .accept(MediaType.APPLICATION_JSON))
	                .andExpect(status().isOk());
	    }

}
