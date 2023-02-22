package com.niccopark.ticketbooking.service;

import java.util.List;
import java.util.Optional;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.niccopark.dtos.ActvityDTO;
import com.niccopark.dtos.BookingDetails;
import com.niccopark.dtos.TicketDTO;
import com.niccopark.entity.Activity;
import com.niccopark.entity.Customer;
import com.niccopark.entity.Slot;
import com.niccopark.entity.Ticket;
import com.niccopark.exceptions.ActivityException;
import com.niccopark.exceptions.CustomerException;
import com.niccopark.exceptions.TicketException;
import com.niccopark.repository.ActivityRepository;
import com.niccopark.repository.BookedActivityRepository;
import com.niccopark.repository.CustomerRepository;
import com.niccopark.repository.SlotRepository;
import com.niccopark.repository.TicketRepository;

@Service
public class TicketBookingServiceImpl implements TicketBookingService {

	@Autowired
	private ActivityRepository activityRepository;

	@Autowired
	private CustomerRepository customerRepository;

	@Autowired
	private BookedActivityRepository bookedActivityRepository;

	@Autowired
	private TicketRepository ticketRepository;

	@Autowired
	private SlotRepository slotRepository;

	@Override
	public TicketDTO insertTicket(BookingDetails dto) throws TicketException, ActivityException, CustomerException {

		Optional<Customer> optional = customerRepository.findById(dto.getCustomerId());

		if (optional.isPresent()) {

			Customer existingCustomer = optional.get();

			Optional<Activity> option = activityRepository.findById(dto.getActivityId());

			if (option.isPresent()) {

				Activity existingActivity = option.get();
				
				System.out.println(existingActivity);
				
				Optional<Slot> slot = slotRepository.findById(dto.getSlotId());

				if (slot.isPresent()) {

					Set<Slot> slots = existingActivity.getSlots();

					Slot existingSlot = slot.get();

					if (slots.contains(existingSlot)) {

						Ticket ticket = new Ticket();

						ticket.setActivity(existingActivity);

						ticket.setCustomer(existingCustomer);

						ticket.setSlot(existingSlot);

						existingActivity.getTickets().add(ticket);
						
						existingCustomer.getTickets().add(ticket);
						
						ticketRepository.save(ticket);
						

						TicketDTO ticketDTO = new TicketDTO();

						ticketDTO.setActivityCharge(existingActivity.getCharges());

						ticketDTO.setActivityName(existingActivity.getName());

						ticketDTO.setCustomerName(existingCustomer.getName());

						ticketDTO.setSlot(existingSlot);

						return ticketDTO;

					} else {

						throw new ActivityException("Slot does not exists for this activity");

					}

				} else {

					throw new ActivityException("Slot does not exists..");

				}
			} else {
				throw new ActivityException("Activity not found..");
			}

		} else {
			throw new CustomerException("Customer does not exists..");
		}

	}

	@Override
	public void updateTicket(ActvityDTO actvityDTO) throws ActivityException {

//		Optional<Activity> existingActivity = arepo.findById(actvityDTO.getActivityId());
//		
//		if(existingActivity.isPresent()) {
//			
//			Activity activity = arepo.findByName(actvityDTO.getActivityName());
//			
//			if(activity != null) {
//				
//				
//				
//			}
//			else {
//				
//				
//				
//			}
//			
//		}
//		else {
//			throw new TicketException("Ticket can't update because activity id is wrong");
//		}

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
	public Double calculateBill(Integer customerId) throws CustomerException, TicketException {

		Optional<Customer> customer = customerRepository.findById(customerId);

		if (customer.isPresent()) {

			Customer existingCustomer = customer.get();

			List<Ticket> tickets = existingCustomer.getTickets();

			if (tickets.isEmpty()) {

				throw new TicketException("No tickets found");

			} else {

				Double bill = 0.0;

				for (Ticket t : tickets) {

					bill += t.getActivity().getCharges();

				}

				return bill;

			}
		} else {
			throw new CustomerException("Customer not found...");
		}

	}

}
