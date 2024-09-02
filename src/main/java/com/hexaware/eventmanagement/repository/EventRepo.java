package com.hexaware.eventmanagement.repository;

import java.util.List;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


import com.hexaware.eventmanagement.entity.Event;

@Repository
public interface EventRepo extends JpaRepository<Event,Long> {
	
	public List<Event> findByTitle(String title);
	public List<Event> findByLocation(String location);
	public Event findByEventId(long eventId);
	
	
}
