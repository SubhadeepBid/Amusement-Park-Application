package com.niccopark.admin.service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.niccopark.dtos.UpdateUserPasswordDTO;
import com.niccopark.dtos.UpdateUserUsernameDTO;
import com.niccopark.dtos.ValidateUserDTO;
import com.niccopark.entity.Activity;
import com.niccopark.entity.Admin;
import com.niccopark.entity.Customer;
import com.niccopark.entity.Slot;
import com.niccopark.entity.Ticket;
import com.niccopark.exceptions.ActivityException;
import com.niccopark.exceptions.AdminException;
import com.niccopark.exceptions.CustomerException;
import com.niccopark.exceptions.SlotException;
import com.niccopark.repository.ActivityRepository;
import com.niccopark.repository.AdminRepository;
import com.niccopark.repository.CustomerRepository;
import com.niccopark.repository.SlotRepository;
import com.niccopark.repository.TicketRepository;

@Service
public class AdminServiceImpl implements AdminService {
	
	@Autowired
	private CustomerRepository customerRepository;
	
	@Autowired
	private AdminRepository adminRepository;
	
	@Autowired
	private SlotRepository slotRepository;
	
	@Autowired
	private ActivityRepository activityRepository;
	
	@Autowired
	private TicketRepository ticketRepository;

	@Override
	public Admin insertAdmin(Admin admin) throws AdminException {
		
		Optional<Admin> opt = adminRepository.findByUsername(admin.getUsername());
		
		if(opt.isPresent()) {
			throw new AdminException("Admin With Username " + admin.getUsername() + " Is Already Present...");
		}
		
		return adminRepository.save(admin);
		
	}
	
	@Override
	public Admin validateAdmin(ValidateUserDTO dto) throws AdminException {
		
		Optional<Admin> opt = adminRepository.findByUsernameAndPassword(dto.getUsername(), dto.getPassword());
		
		if(opt.isEmpty()) {
			throw new AdminException("Wrong Username Or Password Entered...");
		}
		
		return opt.get();
		
	}

	@Override
	public Admin updateAdminDetails(Admin admin) throws AdminException {
		
		Admin savedAdmin = validateAdmin(new ValidateUserDTO(admin.getUsername(), admin.getPassword()));
		
		if(admin.getAddress() != null) {
			savedAdmin.setAddress(admin.getAddress());
		}
		if(admin.getMobileNumber() != null) {
			savedAdmin.setMobileNumber(admin.getMobileNumber());
		}
		if(admin.getEmail() != null) {
			savedAdmin.setEmail(admin.getEmail());
		}
		if(admin.getName() != null) {
			savedAdmin.setName(admin.getName());
		}
		
		return adminRepository.save(savedAdmin);
		
	}

	@Override
	public Admin updateAdminPassword(UpdateUserPasswordDTO dto) throws AdminException {
		
		Admin savedAdmin = validateAdmin(new ValidateUserDTO(dto.getUsername(), dto.getOldPassword()));
		
		if(dto.getNewPassword() != null) {
			savedAdmin.setPassword(dto.getNewPassword());
		}
		
		return adminRepository.save(savedAdmin);
		
	}

	@Override
	public Admin updateAdminUsername(UpdateUserUsernameDTO dto) throws AdminException {
		
		Admin savedAdmin = validateAdmin(new ValidateUserDTO(dto.getOldUsername(), dto.getPassword()));
		
		if(savedAdmin != null && dto.getNewUsername() != null) {
			if(adminRepository.findByUsername(dto.getNewUsername()).isEmpty()) {
				savedAdmin.setUsername(dto.getNewUsername());
				
				return adminRepository.save(savedAdmin);
			}
			else {
				throw new AdminException("Admin Already Exist With Username " + dto.getNewUsername());
			}
		}
		else {
			throw new AdminException("Invalid Username or Password...");
		}
		
	}

	@Override
	public Admin deleteAdmin(Integer adminId) throws AdminException {
		
		Optional<Admin> opt = adminRepository.findById(adminId);
		
		if(opt.isEmpty()) {
			throw new AdminException("Admin Not Found With Admin ID : " + adminId);
		}
		
		Admin savedAdmin = opt.get();
		
		adminRepository.delete(savedAdmin);
		
		return savedAdmin;
		
	}

	@Override
	public Slot insertSlot(Slot slot) throws SlotException {
		
		Optional<Slot> opt = slotRepository.findByStartTimeAndEndTime(slot.getStartTime(), slot.getEndTime());
		
		if(opt.isPresent()) {
			throw new SlotException("Slot already saved");
		}
		
		return slotRepository.save(slot);
		
	}

	@Override
	public List<Activity> getAllActivitiesByCustomerId(Integer customerId) throws ActivityException {
		
		List<Ticket> tickets = customerRepository.getAllTickets(customerId);
		
		if(tickets.isEmpty()) {
			throw new ActivityException("No Activities Found");
		}
		
		List<Activity> activities = new ArrayList<>();
		
		tickets.forEach(t -> {
			activities.add(t.getActivity());
		});
		
		return activities;
	}

	@Override
	public List<Activity> getAllActivities() throws ActivityException {
		
		List<Activity> activities = activityRepository.findAll();
		
		if(activities.isEmpty()) {
			throw new ActivityException("No Activities Found");
		}
		
		return activities;
	}

	@Override
	public List<Customer> getActivitiesCustomerWise() throws ActivityException {
		
		List<Customer> customers = customerRepository.findAll();
		
//		if(customers.isEmpty()) {
//			throw new ActivityException("No Activities Found");
//		}
		
		return customers;
	}

	@Override
	public List<Activity> getActivitiesDateWise() throws ActivityException {
		
		List<Activity> tickets = ticketRepository.getAllTicketsDateWise();
		
		if(tickets.isEmpty()) {
			throw new ActivityException("No Activity Found");
		}
		
		return tickets;
	}

	@Override
	public List<Ticket> getAllActivitiesForDays(Integer customerId, LocalDate fromDate, LocalDate toDate)
			throws ActivityException {
		
		Optional<Customer> opt = customerRepository.findById(customerId);
		
		if(opt.isEmpty()) {
			throw new CustomerException("No Customer Found");
		}
		
		Customer existingCustomer = opt.get();
		
		List<Ticket> tickets = ticketRepository.findByCustomerAndDateBetweenOrderByDate(existingCustomer, fromDate, toDate);
		
		if(tickets.isEmpty()) {
			throw new ActivityException("No Activities Found");
		}
		
		return tickets;
		
	}

}
