package com.niccopark.ticketbooking.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.niccopark.dtos.ActvityDTO;
import com.niccopark.dtos.BookingDetails;
import com.niccopark.dtos.TicketDTO;
import com.niccopark.entity.Activity;
import com.niccopark.entity.BookedActivity;
import com.niccopark.entity.Customer;
import com.niccopark.entity.Ticket;
import com.niccopark.exceptions.ActivityException;
import com.niccopark.exceptions.TicketException;
import com.niccopark.repository.ActivityRepository;
import com.niccopark.repository.BookedActivityRepository;
import com.niccopark.repository.CustomerRepository;
import com.niccopark.repository.TicketRepository;

@Service
public class TicketBookingServiceImpl implements TicketBookingService {

	@Autowired
	private ActivityRepository arepo;

	@Autowired
	private CustomerRepository crepo;

	@Autowired
	private BookedActivityRepository brepo;

	@Autowired
	private TicketRepository trepo;

	@Override
	public TicketDTO insertTicket(BookingDetails bookingDetailsDTO) throws TicketException, ActivityException {

		Activity existingActivity = arepo.findByName(bookingDetailsDTO.getActivityName());

		if (existingActivity != null) {

			Optional<Customer> existingCustomer = crepo.findById(bookingDetailsDTO.getCustomerId());

			if (existingCustomer.isPresent()) {

				Customer customer = existingCustomer.get();

//				BookedActivity bookedActivity = new BookedActivity();

//				bookedActivity.setActivity(existingActivity);

//				bookedActivity.setCustomer(customer);

//				BookedActivity customerActivity = brepo.save(bookedActivity);

				Ticket ticket = new Ticket();

				ticket.setCustomer(customer);
				
				ticket.setActivity(existingActivity);
				
//				ticket.getBookedActivities().add(customerActivity);

				trepo.save(ticket);

				TicketDTO ticketDTO = new TicketDTO();

				ticketDTO.setActivityCharge(existingActivity.getCharges());

				ticketDTO.setActivityName(existingActivity.getName());

				ticketDTO.setMobileNumber(customer.getMobileNumber());

				return ticketDTO;

			} else {
				throw new TicketException("Customer does not exists...");
			}

		} else {

			throw new ActivityException("Activity does not exists..");
		}

	}

	@Override
	public void updateTicket(ActvityDTO actvityDTO) throws ActivityException {
		
		Optional<Activity> existingActivity = arepo.findById(actvityDTO.getActivityId());
		
		if(existingActivity.isPresent()) {
			
			Activity activity = arepo.findByName(actvityDTO.getActivityName());
			
			if(activity != null) {
				
				
				
			}
			else {
				
				
				
			}
			
		}
		else {
			throw new TicketException("Ticket can't update because activity id is wrong");
		}
		
	}

	@Override
	public Ticket deleteTicket(Integer ticketId) throws TicketException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Ticket> viewAllTicketsCustomer(Integer customerId) throws TicketException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Double calculateBill(Integer customerId) throws TicketException {
		// TODO Auto-generated method stub
		return null;
	}

}
