package com.niccopark.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.niccopark.entity.Activity;
import com.niccopark.entity.Customer;
import com.niccopark.entity.Ticket;

public interface TicketRepository extends JpaRepository<Ticket, Integer> {
	
<<<<<<< HEAD
	public List<Ticket> findByCustomer(Customer customer);
=======

//	@Query("delete from Activity where Ticket=:1")
//	public Object deleteTicket(Ticket ticket);
>>>>>>> branch 'master' of https://github.com/SubhadeepBid/agreeable-development-7620.git
	
	@Query("select activity from Ticket ORDER BY date")
	public List<Activity> getAllTicketsDateWise();

}
