package com.hexaware.eventmanagement.service;

import java.util.List;

import com.hexaware.eventmanagement.dto.AddressDTO;
import com.hexaware.eventmanagement.entity.Address;

public interface IAddressServices {
	public Address createAddress(AddressDTO addressDTO);
	public Address updateAddress(AddressDTO addressDTO);
	public void deleteByAddressId(long addressId);
	public List<Address> getByState(String state);
	public List<Address> getByCity(String city);

	public List<Address> getAllAddress();

}
