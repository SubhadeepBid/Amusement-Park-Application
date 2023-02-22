package com.niccopark.ticketbooking.service;

import java.util.List;

import com.niccopark.dtos.ActvityDTO;
import com.niccopark.dtos.BookingDetails;
import com.niccopark.dtos.TicketDTO;
import com.niccopark.entity.Ticket;
import com.niccopark.exceptions.ActivityException;
import com.niccopark.exceptions.CustomerException;
import com.niccopark.exceptions.TicketException;

public interface TicketBookingService {
	
	public TicketDTO insertTicket(BookingDetails bookingDetailsDTO) throws TicketException, ActivityException, CustomerException;
	
	public void updateTicket(ActvityDTO activityDTO) throws ActivityException;
	
	public Ticket deleteTicket(Integer ticketId) throws TicketException;
	
	public List<Ticket> viewAllTicketsCustomer(Integer customerId) throws TicketException;
	
	public Double calculateBill(Integer customerId) throws CustomerException, TicketException;
	
}
