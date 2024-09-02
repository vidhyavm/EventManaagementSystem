package com.hexaware.eventmanagement.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hexaware.eventmanagement.dto.AddressDTO;
import com.hexaware.eventmanagement.entity.Address;
import com.hexaware.eventmanagement.exception.AddressNotFoundException;
import com.hexaware.eventmanagement.repository.AddressRepo;

@Service
public class AddressServicesImp implements IAddressServices {

	@Autowired
    AddressRepo addressrepo;
	
	@Override
	public Address createAddress(AddressDTO addressDTO) {
		try 
        {
            Address address = new Address();
            address.setAddressLine(addressDTO.getAddressLine());
            address.setCity(addressDTO.getCity());
            address.setState(addressDTO.getState());
            address.setPincode(addressDTO.getPincode());


            Address createdAddress = addressrepo.save(address);

            
            return createdAddress;
        } 
		 catch (Exception e) 
        {
            
            throw new RuntimeException("Error creating address", e);
        }
	}

	@Override
	public Address updateAddress(AddressDTO addressDTO) {
		 try 
	        {
	            if (!addressrepo.existsById(addressDTO.getAddressId())) 
	            {
	                throw new AddressNotFoundException("Address not found with ID: " + addressDTO.getAddressId());
	            }

	            Address address = new Address();
	            address.setAddressId(addressDTO.getAddressId());
	            address.setAddressLine(addressDTO.getAddressLine());
	            address.setCity(addressDTO.getCity());
	            address.setState(addressDTO.getState());
	            address.setPincode(addressDTO.getPincode());


	            Address updatedAddress = addressrepo.save(address);

	           

	            return updatedAddress;
	        } 
	        catch (Exception e) 
	        {
	            
	            throw new RuntimeException("Error updating address", e);
	        }
	}

	@Override
	public void deleteByAddressId(long addressId) {
		 try 
	        {
	            if (!addressrepo.existsById(addressId)) 
	            {
	                throw new AddressNotFoundException("Address not found with ID: " + addressId);
	            }

	            addressrepo.deleteById(addressId);

	           
	        } 
	        catch (Exception e) 
	        {
	          
	            throw new RuntimeException("Error deleting address", e);
	        }
		
	}

	@Override
	public List<Address> getByState(String state) {
		try 
        {
            List<Address> addresses = addressrepo.findByState(state);

           
            return addresses;
        } 
        catch (Exception e) 
        {
           
            throw new RuntimeException("Error getting addresses by state", e);
        }
	}

	@Override
	public List<Address> getByCity(String city) {
		 try 
	        {
	            List<Address> addresses = addressrepo.findByCity(city);

	            return addresses;
	        } 
	        catch (Exception e) 
	        {
	          
	            throw new RuntimeException("Error getting addresses by city", e);
	        }
	    }
	

	@Override
	public List<Address> getAllAddress() {
		try 
        {
            List<Address> addresses = addressrepo.findAll();

           

            return addresses;
        } 
        catch (Exception e) 
        {
           
            throw new RuntimeException("Error getting all addresses", e);
        }
	}

	

}
