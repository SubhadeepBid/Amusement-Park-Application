package com.niccopark.ticketbooking.service;

import java.util.List;

import com.niccopark.dtos.TicketUpdateDTO;
import com.niccopark.dtos.BookingDetails;
import com.niccopark.dtos.TicketDTO;
import com.niccopark.entity.Ticket;
import com.niccopark.exceptions.ActivityException;
import com.niccopark.exceptions.CustomerException;
import com.niccopark.exceptions.SlotException;
import com.niccopark.exceptions.TicketException;

public interface TicketBookingService {
	
	public TicketDTO insertTicket(BookingDetails bookingDetailsDTO) throws TicketException, ActivityException, CustomerException;
	
	public void updateTicket(TicketUpdateDTO activityDTO, Integer ticketId) throws ActivityException, SlotException;
	
	public Ticket deleteTicket(Integer ticketId) throws TicketException;
	
<<<<<<< HEAD
	public List<Ticket> viewAllTicketsCustomer(Integer customerId) throws CustomerException, TicketException;
=======
	public List<Ticket> viewAllTicketsCustomer(Integer customerId) throws TicketException, CustomerException;
>>>>>>> branch 'master' of https://github.com/SubhadeepBid/agreeable-development-7620.git
	
	public Double calculateBill(Integer customerId) throws CustomerException, TicketException;
	
}
