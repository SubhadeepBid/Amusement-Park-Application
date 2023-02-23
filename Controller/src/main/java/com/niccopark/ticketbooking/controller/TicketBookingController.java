package com.niccopark.ticketbooking.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.niccopark.dtos.BookingDetails;
import com.niccopark.dtos.TicketDTO;
import com.niccopark.dtos.TicketUpdateDTO;
import com.niccopark.entity.Ticket;
import com.niccopark.exceptions.ActivityException;
import com.niccopark.exceptions.CustomerException;
import com.niccopark.exceptions.TicketException;
import com.niccopark.ticketbooking.service.TicketBookingService;
import com.niccopark.ticketbooking.service.TicketBookingServiceImpl;

@RestController
@RequestMapping("/ticket")
public class TicketBookingController {
	
	@Autowired
	private TicketBookingService ticketBookingService;
	
	@PostMapping("/booking")
	public ResponseEntity<TicketDTO> insertTicketHandler(@RequestBody BookingDetails dto)throws TicketException, ActivityException, CustomerException{
		
		return new ResponseEntity<TicketDTO>(ticketBookingService.insertTicket(dto), HttpStatus.ACCEPTED);
		
	}
	
	
	@GetMapping("/bill/{cid}")
	public ResponseEntity<Double> calculateBillHandler(@PathVariable("cid") Integer customerID)throws CustomerException, TicketException{
		
		return new ResponseEntity<Double>(ticketBookingService.calculateBill(customerID), HttpStatus.OK);
		
	}
	
	@GetMapping("/ticket/{cid}")
	public ResponseEntity<List<Ticket>> getAllTicketHandler(@PathVariable("cid") Integer customerID)throws TicketException, CustomerException{
		
		return new ResponseEntity<List<Ticket>>(ticketBookingService.viewAllTicketsCustomer(customerID), HttpStatus.OK);
		
	}
	
	
	@DeleteMapping("/ticket/{tid}")
	public ResponseEntity<Ticket> deleteTicketHandler(@PathVariable("tid") Integer ticketID)throws TicketException, CustomerException{
		
		return new ResponseEntity<Ticket>(ticketBookingService.deleteTicket(ticketID), HttpStatus.OK);
		
	}
	
	
}
