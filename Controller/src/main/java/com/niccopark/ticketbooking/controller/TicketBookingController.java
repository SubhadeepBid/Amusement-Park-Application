package com.niccopark.ticketbooking.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.niccopark.dtos.BookingDetails;
import com.niccopark.dtos.TicketDTO;
<<<<<<< HEAD
import com.niccopark.entity.Ticket;
=======
import com.niccopark.dtos.TicketUpdateDTO;
import com.niccopark.entity.Ticket;
import com.niccopark.exceptions.ActivityException;
import com.niccopark.exceptions.CustomerException;
import com.niccopark.exceptions.TicketException;
>>>>>>> branch 'master' of https://github.com/SubhadeepBid/agreeable-development-7620.git
import com.niccopark.ticketbooking.service.TicketBookingService;

@RestController
@RequestMapping("/tickets")
public class TicketBookingController {
	
	@Autowired
	private TicketBookingService ticketBookingService;
	
	@PostMapping("/book_a_ticket")
	public ResponseEntity<TicketDTO> insertTicketHandler(@RequestBody BookingDetails dto) {
		
		return new ResponseEntity<>(ticketBookingService.insertTicket(dto), HttpStatus.ACCEPTED);
		
	}
	
	@DeleteMapping("/delete_ticket/{ticketId}")
	public ResponseEntity<Ticket> deleteTicketHandler(@PathVariable("ticketId") Integer ticketId) {
		
		return new ResponseEntity<>(ticketBookingService.deleteTicket(ticketId), HttpStatus.OK);
		
	}
	
<<<<<<< HEAD
	@GetMapping("/get_all_tickets_of_customer/{customerId}")
	public ResponseEntity<List<Ticket>> getAllTicketsCustomerHandler(@PathVariable("customerId") Integer customerId) {
=======
	@GetMapping("/ticket/{cid}")
	public ResponseEntity<List<Ticket>> getAllTicketHandler(@PathVariable("cid") Integer customerID)throws TicketException, CustomerException{
>>>>>>> branch 'master' of https://github.com/SubhadeepBid/agreeable-development-7620.git
		
<<<<<<< HEAD
		return new ResponseEntity<>(ticketBookingService.viewAllTicketsCustomer(customerId), HttpStatus.OK);
=======
		return new ResponseEntity<List<Ticket>>(ticketBookingService.viewAllTicketsCustomer(customerID), HttpStatus.OK);
		
	}
	
	
	@DeleteMapping("/ticket/{tid}")
	public ResponseEntity<Ticket> deleteTicketHandler(@PathVariable("tid") Integer ticketID)throws TicketException, CustomerException{
		
		return new ResponseEntity<Ticket>(ticketBookingService.deleteTicket(ticketID), HttpStatus.OK);
>>>>>>> branch 'master' of https://github.com/SubhadeepBid/agreeable-development-7620.git
		
	}

	@GetMapping("/calculate_bill/{customerId}")
	public ResponseEntity<Double> calculateBillHandler(@PathVariable("customerId") Integer customerId) {
		
		return new ResponseEntity<>(ticketBookingService.calculateBill(customerId), HttpStatus.OK);
		
	}
	
}
