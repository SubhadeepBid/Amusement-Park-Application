package com.niccopark.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.niccopark.entity.Ticket;

public interface TicketRepository extends JpaRepository<Ticket, Integer> {
	

//	@Query("delete from Activity where Ticket=:1")
//	public Object deleteTicket(Ticket ticket);
	
}
