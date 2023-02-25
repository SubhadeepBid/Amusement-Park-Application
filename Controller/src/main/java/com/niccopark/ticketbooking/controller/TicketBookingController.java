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
import org.springframework.web.bind.annotation.RequestParam;
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
	public ResponseEntity<TicketDTO> insertTicketHandler(@Valid @RequestBody BookingDetails dto, @RequestParam String customerUuid) {
		
		return new ResponseEntity<>(ticketBookingService.insertTicket(dto, customerUuid), HttpStatus.ACCEPTED);
		
	}
	
	@PutMapping("/update_tickets_activity_name/{ticketId}")
	public ResponseEntity<TicketDTO> updateTicketsActivityNameHandler(@Valid @RequestBody TicketUpdateActivityNameDTO ticketUpdateDTO, @PathVariable("ticketId") Integer ticketId, @RequestParam String customerUuid) {
		
		return new ResponseEntity<>(ticketBookingService.updateTicketsActivityName(ticketUpdateDTO, ticketId, customerUuid), HttpStatus.OK);
		
	}
	
	@PutMapping("/update_tickets_slot_or_date/{ticketId}")
	public ResponseEntity<TicketDTO> updateTicketsSlotOrDate(@Valid @RequestBody TicketUpdateSlotOrDateDTO ticketUpdateDTO, @PathVariable("ticketId") Integer ticketId, @RequestParam String customerUuid) {
		
		return new ResponseEntity<>(ticketBookingService.updateTicketsSlotOrDate(ticketUpdateDTO, ticketId, customerUuid), HttpStatus.OK);
		
	}
	
	@DeleteMapping("/delete_ticket/{ticketId}")
	public ResponseEntity<Ticket> deleteTicketHandler(@PathVariable("ticketId") Integer ticketId, @RequestParam String customerUuid) {
		
		return new ResponseEntity<>(ticketBookingService.deleteTicket(ticketId, customerUuid), HttpStatus.OK);
		
	}

	@GetMapping("/get_all_tickets_of_customer")
	public ResponseEntity<List<Ticket>> getAllTicketsCustomerHandler(@RequestParam String customerUuid) {

		return new ResponseEntity<>(ticketBookingService.viewAllTicketsCustomer(customerUuid), HttpStatus.OK);

	}
	
	@GetMapping("/calculate_bill")
	public ResponseEntity<Double> calculateBillHandler(@RequestParam String customerUuid) {
		
		return new ResponseEntity<>(ticketBookingService.calculateBill(customerUuid), HttpStatus.OK);
		
	}
	
}
