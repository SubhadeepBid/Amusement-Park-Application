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
	
	public TicketDTO insertTicket(BookingDetails bookingDetailsDTO, String customerUuid) throws TicketException, ActivityException, CustomerException;
	
	public TicketDTO updateTicketsActivityName(TicketUpdateActivityNameDTO ticketUpdateDTO, Integer ticketId, String customerUuid) throws ActivityException, SlotException, TicketException, CustomerException;
	
	public TicketDTO updateTicketsSlotOrDate(TicketUpdateSlotOrDateDTO ticketUpdateDTO, Integer ticketId, String customerUuid) throws ActivityException, SlotException, TicketException, CustomerException;
	
	public Ticket deleteTicket(Integer ticketId, String customerUuid) throws TicketException, CustomerException;
	
	public List<Ticket> viewAllTicketsCustomer(String customerUuid) throws CustomerException, TicketException;

	public Double calculateBill(String customerUuid) throws CustomerException, TicketException;
	
}
