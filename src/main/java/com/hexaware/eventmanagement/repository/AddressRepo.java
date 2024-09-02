package com.hexaware.eventmanagement.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.hexaware.eventmanagement.entity.Address;

@Repository
public interface AddressRepo extends JpaRepository<Address,Long>{

public List<Address> findByState(String state);
	
	public List<Address> findByCity(String city);
}
