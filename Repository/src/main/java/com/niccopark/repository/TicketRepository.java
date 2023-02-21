package com.niccopark.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.niccopark.entity.Ticket;

public interface TicketRepository extends JpaRepository<Ticket, Integer> {

}
