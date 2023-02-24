package com.niccopark.ticketbooking.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.niccopark.dtos.BookingDetails;
import com.niccopark.dtos.TicketDTO;
import com.niccopark.dtos.TicketUpdateActivityNameDTO;
import com.niccopark.dtos.TicketUpdateSlotOrDateDTO;
import com.niccopark.entity.Activity;
import com.niccopark.entity.Customer;
import com.niccopark.entity.Slot;
import com.niccopark.entity.Ticket;
import com.niccopark.exceptions.ActivityException;
import com.niccopark.exceptions.CustomerException;
import com.niccopark.exceptions.SlotException;
import com.niccopark.exceptions.TicketException;
import com.niccopark.repository.ActivityRepository;
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

					List<Slot> slots = existingActivity.getSlots();

					Slot existingSlot = slot.get();

					if (slots.contains(existingSlot)) {

						Ticket ticket = new Ticket();

						ticket.setDate(dto.getDate());

						ticket.setActivity(existingActivity);

						ticket.setCustomer(existingCustomer);

						ticket.setSlot(existingSlot);

						existingActivity.getTickets().add(ticket);

						existingCustomer.getTickets().add(ticket);

						ticketRepository.save(ticket);

						customerRepository.save(existingCustomer);

						activityRepository.save(existingActivity);

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
	public Ticket deleteTicket(Integer ticketId) throws TicketException {

		Optional<Ticket> opt = ticketRepository.findById(ticketId);

		if (opt.isEmpty()) {
			throw new TicketException("No Ticket Found");
		}

		Ticket existingTicket = opt.get();

		ticketRepository.delete(existingTicket);

		return existingTicket;

	}

	@Override
	public List<Ticket> viewAllTicketsCustomer(Integer customerId) throws CustomerException, TicketException {

		Optional<Customer> opt = customerRepository.findById(customerId);

		if (opt.isEmpty()) {
			throw new CustomerException("No Customer Found");
		}

		List<Ticket> tickets = ticketRepository.findByCustomer(opt.get());

		if (tickets.isEmpty()) {
			throw new TicketException("No Tickets Found");
		}

		return tickets;

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

	
	@Override
	public TicketDTO updateTicketsActivityName(TicketUpdateActivityNameDTO ticketUpdateDTO, Integer ticketId)
			throws ActivityException, SlotException, TicketException {

		Optional<Ticket> opt = ticketRepository.findById(ticketId);

		if (opt.isEmpty()) {
			throw new TicketException("No Tickets Found");
		}

		Ticket existingTicket = opt.get();
		
		Optional<Activity> opt1 = activityRepository.findByName(ticketUpdateDTO.getActivityName());
		
		if(opt1.isEmpty()) {
			throw new ActivityException("No Activity Found");
		}
		
		Activity existingActivity = opt1.get();
		
		Optional<Slot> opt2 = slotRepository.findById(ticketUpdateDTO.getSlotId());
		
		if(opt2.isEmpty()) {
			throw new SlotException("No Slot Found");
		}
		
		Slot existingSlot = opt2.get();
		
		if(existingActivity.getSlots().contains(existingSlot)) {
			
			existingTicket.setActivity(existingActivity);
			
			existingTicket.setSlot(existingSlot);
			
			if(ticketUpdateDTO.getDate() != null) {
				existingTicket.setDate(ticketUpdateDTO.getDate());
				
				ticketRepository.save(existingTicket);
				
				TicketDTO ticketDTO = new TicketDTO();

				ticketDTO.setActivityCharge(existingActivity.getCharges());

				ticketDTO.setActivityName(existingActivity.getName());

				ticketDTO.setCustomerName(existingTicket.getCustomer().getName());

				ticketDTO.setSlot(existingSlot);

				return ticketDTO;
			}
			else {
				throw new TicketException("Please Enter A Date");
			}
			
		}
		else {
			throw new ActivityException("Slot Is Not Available For This Activity");
		}

	}

	
	@Override
	public TicketDTO updateTicketsSlotOrDate(TicketUpdateSlotOrDateDTO ticketUpdateDTO, Integer ticketId)
			throws ActivityException, SlotException, TicketException {
		
		Optional<Ticket> opt = ticketRepository.findById(ticketId);

		if (opt.isEmpty()) {
			throw new TicketException("No Tickets Found");
		}

		Ticket existingTicket = opt.get();
		
		Optional<Slot> opt1 = slotRepository.findById(ticketUpdateDTO.getSlotId());
		
		if(opt1.isEmpty()) {
			throw new SlotException("No Slot Found");
		}
		
		Slot existingSlot = opt1.get();
		
		Activity existingActivity = existingTicket.getActivity();
		
		if(existingActivity.getSlots().contains(existingSlot)) {
			existingTicket.setSlot(existingSlot);
			
			if(ticketUpdateDTO.getDate() != null) {
				existingTicket.setDate(ticketUpdateDTO.getDate());
			}
			
			ticketRepository.save(existingTicket);
			
			TicketDTO ticketDTO = new TicketDTO();

			ticketDTO.setActivityCharge(existingActivity.getCharges());

			ticketDTO.setActivityName(existingActivity.getName());

			ticketDTO.setCustomerName(existingTicket.getCustomer().getName());

			ticketDTO.setSlot(existingSlot);

			return ticketDTO;
			
		}
		else {
			throw new ActivityException("Slot Is Not Available For This Activity");
		}
		
	}

}
