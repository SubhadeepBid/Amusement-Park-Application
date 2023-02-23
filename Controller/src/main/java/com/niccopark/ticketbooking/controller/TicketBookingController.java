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
import com.niccopark.entity.Ticket;
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
	
	@GetMapping("/get_all_tickets_of_customer/{customerId}")
	public ResponseEntity<List<Ticket>> getAllTicketsCustomerHandler(@PathVariable("customerId") Integer customerId) {
		
		return new ResponseEntity<>(ticketBookingService.viewAllTicketsCustomer(customerId), HttpStatus.OK);
		
	}

	@GetMapping("/calculate_bill/{customerId}")
	public ResponseEntity<Double> calculateBillHandler(@PathVariable("customerId") Integer customerId) {
		
		return new ResponseEntity<>(ticketBookingService.calculateBill(customerId), HttpStatus.OK);
		
	}
	
}
