package com.niccopark.ticketbooking.service;

import java.util.List;

import com.niccopark.dtos.TicketUpdateActivityNameDTO;
import com.niccopark.dtos.TicketUpdateSlotOrDateDTO;
import com.niccopark.dtos.BookingDetails;
import com.niccopark.dtos.TicketDTO;
import com.niccopark.entity.Ticket;
import com.niccopark.exceptions.ActivityException;
import com.niccopark.exceptions.CustomerException;
import com.niccopark.exceptions.SlotException;
import com.niccopark.exceptions.TicketException;

public interface TicketBookingService {
	
	public TicketDTO insertTicket(BookingDetails bookingDetailsDTO) throws TicketException, ActivityException, CustomerException;
	
	public TicketDTO updateTicketsActivityName(TicketUpdateActivityNameDTO ticketUpdateDTO, Integer ticketId) throws ActivityException, SlotException, TicketException;
	
	public TicketDTO updateTicketsSlotOrDate(TicketUpdateSlotOrDateDTO ticketUpdateDTO, Integer ticketId) throws ActivityException, SlotException, TicketException;
	
	public Ticket deleteTicket(Integer ticketId) throws TicketException;
	
	public List<Ticket> viewAllTicketsCustomer(Integer customerId) throws CustomerException, TicketException;

	public Double calculateBill(Integer customerId) throws CustomerException, TicketException;
	
}
