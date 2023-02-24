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
import com.niccopark.dtos.TicketUpdateActivityNameDTO;
import com.niccopark.dtos.TicketUpdateSlotOrDateDTO;
import com.niccopark.entity.Ticket;
//github.com/SubhadeepBid/agreeable-development-7620.git
import com.niccopark.ticketbooking.service.TicketBookingService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/tickets")
public class TicketBookingController {
	
	@Autowired
	private TicketBookingService ticketBookingService;
	
	@PostMapping("/book_a_ticket")
	public ResponseEntity<TicketDTO> insertTicketHandler(@Valid @RequestBody BookingDetails dto) {
		
		return new ResponseEntity<>(ticketBookingService.insertTicket(dto), HttpStatus.ACCEPTED);
		
	}
	
	@PutMapping("/update_tickets_activity_name/{ticketId}")
	public ResponseEntity<TicketDTO> updateTicketsActivityNameHandler(@Valid @RequestBody TicketUpdateActivityNameDTO ticketUpdateDTO, @PathVariable("ticketId") Integer ticketId) {
		
		return new ResponseEntity<>(ticketBookingService.updateTicketsActivityName(ticketUpdateDTO, ticketId), HttpStatus.OK);
		
	}
	
	@PutMapping("/update_tickets_slot_or_date/{ticketId}")
	public ResponseEntity<TicketDTO> updateTicketsSlotOrDate(@Valid @RequestBody TicketUpdateSlotOrDateDTO ticketUpdateDTO, @PathVariable("ticketId") Integer ticketId) {
		
		return new ResponseEntity<>(ticketBookingService.updateTicketsSlotOrDate(ticketUpdateDTO, ticketId), HttpStatus.OK);
		
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
