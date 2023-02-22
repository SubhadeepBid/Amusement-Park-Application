package com.niccopark.ticketbooking.service;

import java.util.List;

import com.niccopark.entity.Ticket;
import com.niccopark.exceptions.TicketException;

public interface TicketBookingService {
	
	public Ticket insertTicket(Ticket ticket) throws TicketException;
	
	public Ticket updateTicket(Ticket ticket) throws TicketException;
	
	public Ticket deleteTicket(Integer ticketId) throws TicketException;
	
	public List<Ticket> viewAllTicketsCustomer(Integer customerId) throws TicketException;
	
	public Double calculateBill(Integer customerId) throws TicketException;
	
}
