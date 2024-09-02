package com.hexaware.eventmanagement.controller;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.Arrays;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.hexaware.eventmanagement.dto.AddressDTO;
import com.hexaware.eventmanagement.entity.Address;
import com.hexaware.eventmanagement.service.IAddressServices;


@WebMvcTest(AddressController.class)
class AddressControllerTest {


    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private IAddressServices addressServices;

    @Autowired
    private ObjectMapper objectMapper;

    private AddressDTO addressDTO;
    private Address address;
    
    
    @BeforeEach
    public void setup() {
        addressDTO = new AddressDTO();
        addressDTO.setAddressId(1L);
        addressDTO.setCity("Chennai");
        addressDTO.setAddressLine("MG Road");
        addressDTO.setState("Tamil Nadu");
        addressDTO.setPincode(600001);

        address = new Address();
        address.setAddressId(1L);
        address.setCity("Chennai");
        address.setAddressLine("MG Road");
        address.setState("Tamil Nadu");
        address.setPincode(600001);
    }

    @Test
    public void testCreateAddress() throws Exception {
        Mockito.when(addressServices.createAddress(Mockito.any(AddressDTO.class))).thenReturn(address);

        mockMvc.perform(post("/api/v1/address/add")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(addressDTO)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.addressId").value(1L))
                .andExpect(jsonPath("$.city").value("Chennai"))
                .andExpect(jsonPath("$.state").value("Tamil Nadu"))
                .andExpect(jsonPath("$.pincode").value(600001));
    }

    @Test
    public void testUpdateAddress() throws Exception {
        Mockito.when(addressServices.updateAddress(Mockito.any(AddressDTO.class))).thenReturn(address);

        mockMvc.perform(put("/api/v1/address/update")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(addressDTO)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.addressId").value(1L))
                .andExpect(jsonPath("$.city").value("Chennai"))
                .andExpect(jsonPath("$.state").value("Tamil Nadu"))
                .andExpect(jsonPath("$.pincode").value(600001));
    }

    @Test
    public void testDeleteByAddressId() throws Exception {
        Mockito.doNothing().when(addressServices).deleteByAddressId(1L);

        mockMvc.perform(delete("/api/v1/address/delete/1"))
                .andExpect(status().isOk());
    }

    @Test
    public void testGetAddressByState() throws Exception {
        Mockito.when(addressServices.getByState("Tamil Nadu")).thenReturn(Arrays.asList(address));

        mockMvc.perform(get("/api/v1/address/get/state/Tamil Nadu")
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].addressId").value(1L))
                .andExpect(jsonPath("$[0].city").value("Chennai"))
                .andExpect(jsonPath("$[0].state").value("Tamil Nadu"))
                .andExpect(jsonPath("$[0].pincode").value(600001));
    }

    @Test
    public void testGetAddressByCity() throws Exception {
        Mockito.when(addressServices.getByCity("Chennai")).thenReturn(Arrays.asList(address));

        mockMvc.perform(get("/api/v1/address/get/city/Chennai")
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].addressId").value(1L))
                .andExpect(jsonPath("$[0].city").value("Chennai"))
                .andExpect(jsonPath("$[0].state").value("Tamil Nadu"))
                .andExpect(jsonPath("$[0].pincode").value(600001));
    }

    @Test
    public void testGetAllAddress() throws Exception {
        Mockito.when(addressServices.getAllAddress()).thenReturn(Arrays.asList(address));

        mockMvc.perform(get("/api/v1/address/getall")
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].addressId").value(1L))
                .andExpect(jsonPath("$[0].city").value("Chennai"))
                .andExpect(jsonPath("$[0].state").value("Tamil Nadu"))
                .andExpect(jsonPath("$[0].pincode").value(600001));
    }


}
