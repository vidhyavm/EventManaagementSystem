package com.hexaware.eventmanagement.service;


import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
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
import org.springframework.boot.test.context.SpringBootTest;

import com.hexaware.eventmanagement.dto.AddressDTO;
import com.hexaware.eventmanagement.entity.Address;
import com.hexaware.eventmanagement.repository.AddressRepo;

@SpringBootTest
class AddressServicesImpTest {

	@BeforeAll
	static void setUpBeforeClass() throws Exception {
	}
	
	@Mock
	private AddressRepo addressRepo;
	
	@InjectMocks
	private AddressServicesImp service;
	
	
	 @BeforeEach
	    void setUp() {
	        MockitoAnnotations.openMocks(this);
	        
	    }
	 

	    @Test
	    void testCreateAddress() {
	        AddressDTO addressDTO = new AddressDTO();
	        addressDTO.setAddressLine("123 Main St");
	        addressDTO.setCity("coimbatore");
	        addressDTO.setState("Tamilnadu");
	        addressDTO.setPincode(123456);

	        Address address = new Address();
	        address.setAddressLine("123 Main St");
	        address.setCity("coimbatore");
	        address.setState("Tamilnadu");
	        address.setPincode(123456);

	        when(addressRepo.save(any(Address.class))).thenReturn(address);

	        Address createdAddress = service.createAddress(addressDTO);

	        assertNotNull(createdAddress);
	        assertEquals(addressDTO.getAddressLine(), createdAddress.getAddressLine());
	        verify(addressRepo, times(1)).save(any(Address.class));
	    }
	    
	    

	    @Test
	    void testUpdateAddress_Success() {
	      
	        AddressDTO addressDTO = new AddressDTO();
	        addressDTO.setAddressId(1L);
	        addressDTO.setAddressLine("12378 main St");
	        addressDTO.setCity("Coimbatore");
	        addressDTO.setState("Tamilnadu");
	        addressDTO.setPincode(654321);

	        Address address = new Address();
	        address.setAddressId(1L);
	        address.setAddressLine("123 Main St");
	        address.setCity("Chennai");
	        address.setState("Tamilnadu");
	        address.setPincode(987652);

	        when(addressRepo.existsById(addressDTO.getAddressId())).thenReturn(true);
	        when(addressRepo.save(any(Address.class))).thenReturn(address);

	        Address updatedAddress = service.updateAddress(addressDTO);

	        assertNotNull(updatedAddress);
	        assertEquals(addressDTO.getAddressId(), updatedAddress.getAddressId());
	        verify(addressRepo, times(1)).save(any(Address.class));
	    }

	    

	    @Test
	    void testDeleteByAddressId_Success() {
	        
	        long addressId = 1L;
	        when(addressRepo.existsById(addressId)).thenReturn(true);

	        service.deleteByAddressId(addressId);

	        verify(addressRepo, times(1)).deleteById(addressId);
	    }

	   

	    @Test
	    void testGetByState() {
	    
	        String state = "Tamilnadu";
	        Address address1 = new Address();
	        address1.setState(state);

	        Address address2 = new Address();
	        address2.setState(state);

	        when(addressRepo.findByState(state)).thenReturn(Arrays.asList(address1, address2));

	        List<Address> addresses = service.getByState(state);

	        assertNotNull(addresses);
	        assertEquals(2, addresses.size());
	        verify(addressRepo, times(1)).findByState(state);
	    }

	    @Test
	    void testGetByCity() {
	        String city = "Chennai";
	        Address address1 = new Address();
	        address1.setCity(city);

	        Address address2 = new Address();
	        address2.setCity(city);

	        when(addressRepo.findByCity(city)).thenReturn(Arrays.asList(address1, address2));

	        List<Address> addresses = service.getByCity(city);

	        assertNotNull(addresses);
	        assertEquals(2, addresses.size());
	        verify(addressRepo, times(1)).findByCity(city);
	    }

	    @Test
	    void testGetAllAddress() {
	
	        Address address1 = new Address();
	        Address address2 = new Address();

	        when(addressRepo.findAll()).thenReturn(Arrays.asList(address1, address2));

	        List<Address> addresses = service.getAllAddress();

	        assertNotNull(addresses);
	        assertEquals(2, addresses.size());
	        verify(addressRepo, times(1)).findAll();
	    }

	 
	

}
