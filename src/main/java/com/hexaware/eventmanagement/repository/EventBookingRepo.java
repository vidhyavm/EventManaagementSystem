package com.hexaware.eventmanagement.repository;




import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.hexaware.eventmanagement.entity.EventBooking;

@Repository
public interface EventBookingRepo extends JpaRepository<EventBooking,Long>{

	
	
}
