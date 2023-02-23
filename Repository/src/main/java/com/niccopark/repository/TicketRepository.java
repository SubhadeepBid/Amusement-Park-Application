package com.niccopark.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.niccopark.entity.Activity;
import com.niccopark.entity.Customer;
import com.niccopark.entity.Ticket;

public interface TicketRepository extends JpaRepository<Ticket, Integer> {
	
	public List<Ticket> findByCustomer(Customer customer);

	@Query("select activity from Ticket ORDER BY date")
	public List<Activity> getAllTicketsDateWise();

}
