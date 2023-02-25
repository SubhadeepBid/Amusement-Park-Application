package com.niccopark.admin.service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.niccopark.authentication.service.LoginLogoutService;
import com.niccopark.dtos.ActivityDetailsDTO;
import com.niccopark.dtos.ActivityDetailsDateWiseDTO;
import com.niccopark.dtos.CustomerDetailsDTO;
import com.niccopark.dtos.CustomerWiseDTO;
import com.niccopark.dtos.DateWiseDTO;
import com.niccopark.dtos.FlagDTO;
import com.niccopark.dtos.UpdateUserPasswordDTO;
import com.niccopark.dtos.UpdateUserUsernameDTO;
import com.niccopark.dtos.UserUpdateDTO;
import com.niccopark.dtos.ValidateUserDTO;
import com.niccopark.entity.Activity;
import com.niccopark.entity.Admin;
import com.niccopark.entity.CurrentUserSession;
import com.niccopark.entity.Customer;
import com.niccopark.entity.Role;
import com.niccopark.entity.Slot;
import com.niccopark.entity.Ticket;
import com.niccopark.exceptions.ActivityException;
import com.niccopark.exceptions.AdminException;
import com.niccopark.exceptions.CustomerException;
import com.niccopark.exceptions.SlotException;
import com.niccopark.exceptions.TicketException;
import com.niccopark.exceptions.UserException;
import com.niccopark.repository.ActivityRepository;
import com.niccopark.repository.AdminRepository;
import com.niccopark.repository.CurrentUserSessionRepository;
import com.niccopark.repository.CustomerRepository;
import com.niccopark.repository.SlotRepository;
import com.niccopark.repository.TicketRepository;

@Service
public class AdminServiceImpl implements AdminService {

	@Autowired
	private CurrentUserSessionRepository currentUserSessionRepository;
	
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
	
	@Autowired
	private LoginLogoutService loginLogoutService;

	@Override
	public Admin insertAdmin(Admin admin) throws AdminException {

		Optional<Admin> opt = adminRepository.findByUsername(admin.getUsername());
		
		if (opt.isPresent()) {
			throw new AdminException("Username Already Exists...");
		}
		
		Optional<Customer> opt1 = customerRepository.findByUsername(admin.getUsername());
		
		if (opt1.isPresent()) {
			throw new AdminException("Username Already Exists...");
		}

		return adminRepository.save(admin);

	}

	@Override
	public Admin validateAdmin(ValidateUserDTO dto) throws AdminException {

		Optional<Admin> opt = adminRepository.findByUsernameAndPassword(dto.getUsername(), dto.getPassword());

		if (opt.isEmpty()) {
			throw new AdminException("Wrong Username Or Password Entered...");
		}

		return opt.get();

	}

	@Override
	public Admin updateAdminDetails(UserUpdateDTO dto, String uuid) throws AdminException {

		String username = getValidatedUsernameForAdmin(uuid);
		
		Admin savedAdmin = adminRepository.findByUsername(username).get();

		if (dto.getAddress() != null) {
			savedAdmin.setAddress(dto.getAddress());
		}
		if (dto.getMobileNumber() != null) {
			savedAdmin.setMobileNumber(dto.getMobileNumber());
		}
		if (dto.getEmail() != null) {
			savedAdmin.setEmail(dto.getEmail());
		}
		if (dto.getName() != null) {
			savedAdmin.setName(dto.getName());
		}

		return adminRepository.save(savedAdmin);

	}

	@Override
	public Admin updateAdminPassword(UpdateUserPasswordDTO dto, String uuid) throws AdminException {

		String username = getValidatedUsernameForAdmin(uuid);
		
		Admin savedAdmin = validateAdmin(new ValidateUserDTO(username, dto.getOldPassword()));

		if (dto.getNewPassword() != null) {
			savedAdmin.setPassword(dto.getNewPassword());
		}

		return adminRepository.save(savedAdmin);

	}

	@Override
	public Admin updateAdminUsername(UpdateUserUsernameDTO dto, String uuid) throws AdminException {

		String username = getValidatedUsernameForAdmin(uuid);
		
		Admin savedAdmin = validateAdmin(new ValidateUserDTO(username, dto.getPassword()));

		if (savedAdmin != null && dto.getNewUsername() != null) {
			if (adminRepository.findByUsername(dto.getNewUsername()).isEmpty() && customerRepository.findByUsername(dto.getNewUsername()).isEmpty()) {
				savedAdmin.setUsername(dto.getNewUsername());

				CurrentUserSession session = currentUserSessionRepository.findById(uuid).get();
				session.setUsername(dto.getNewUsername());
				
				return adminRepository.save(savedAdmin);
			} else {
				throw new AdminException("Username Already Exists...");
			}
		} else {
			throw new AdminException("Invalid Password...");
		}

	}

	@Override
	public Admin deleteAdmin(Integer adminId, String uuid) throws AdminException {

		String username = getValidatedUsernameForAdmin(uuid);
		
		Optional<Admin> opt = adminRepository.findById(adminId);

		if (opt.isEmpty()) {
			throw new AdminException("Admin Not Found With Admin ID : " + adminId);
		}

		Admin savedAdmin = opt.get();

		adminRepository.delete(savedAdmin);

		return savedAdmin;

	}

	@Override
	public Slot insertSlot(Slot slot, String uuid) throws AdminException, SlotException {
		
		String username = getValidatedUsernameForAdmin(uuid);

		Optional<Slot> opt = slotRepository.findByStartTimeAndEndTime(slot.getStartTime(), slot.getEndTime());

		if (opt.isPresent()) {
			throw new SlotException("Slot already saved");
		}

		return slotRepository.save(slot);

	}

	@Override
	public List<Activity> getAllActivitiesByCustomerId(Integer customerId, String uuid) throws AdminException, ActivityException {

		String username = getValidatedUsernameForAdmin(uuid);
		
		List<Ticket> tickets = customerRepository.getAllTickets(customerId);

		if (tickets.isEmpty()) {
			throw new ActivityException("No Activities Found");
		}

		List<Activity> activities = new ArrayList<>();

		tickets.forEach(t -> {
			activities.add(t.getActivity());
		});

		return activities; // Add Date And Slot
	}

	@Override
	public List<Activity> getAllActivities(String uuid) throws UserException, ActivityException {

		if(loginLogoutService.validateUuid(uuid).isFlag()) {
			List<Activity> activities = activityRepository.findAll();

			if (activities.isEmpty()) {
				throw new ActivityException("No Activities Found");
			}

			return activities;
		}
		else {
			throw new UserException("User Not Authorized");
		}
		
	}

	@Override
	public List<CustomerWiseDTO> getActivitiesCustomerWise(String uuid) throws AdminException, ActivityException {

		String username = getValidatedUsernameForAdmin(uuid);
		
		List<Ticket> tickets = ticketRepository.getAllTicketsOrderByCustomer();

		if (tickets.isEmpty()) {
			throw new ActivityException("No Activities Found");
		}

		Map<CustomerDetailsDTO, List<ActivityDetailsDTO>> map = new HashMap<>();

		tickets.forEach(t -> {
			ActivityDetailsDTO dto = new ActivityDetailsDTO();
			dto.setActivity(t.getActivity());
			dto.setBookingTime(t.getBookingTime());
			dto.setDate(t.getDate());
			dto.setSlot(t.getSlot());

			Customer c = t.getCustomer();

			CustomerDetailsDTO dto1 = new CustomerDetailsDTO(c.getName(), c.getAddress(), c.getMobileNumber(),
					c.getEmail(), c.getCustomerId());

			if (map.containsKey(dto1)) {
				map.get(dto1).add(dto);
			} else {
				List<ActivityDetailsDTO> dtos = new ArrayList<>();
				dtos.add(dto);

				map.put(dto1, dtos);
			}
		});

		List<CustomerWiseDTO> list = new ArrayList<>();

		for (Map.Entry m : map.entrySet()) {

			CustomerWiseDTO dto = new CustomerWiseDTO();
			dto.setCustomerDetails((CustomerDetailsDTO) m.getKey());
			dto.setActivityDetails((List<ActivityDetailsDTO>) m.getValue());

			list.add(dto);

		}

		return list;

	}

	@Override
	public List<DateWiseDTO> getActivitiesDateWise(String uuid) throws AdminException, ActivityException {

		String username = getValidatedUsernameForAdmin(uuid);
		
		List<Ticket> tickets = ticketRepository.getAllTicketsDateWise();

//		tickets.forEach(System.out::println);

		if (tickets.isEmpty()) {
			throw new ActivityException("No Activity Found");
		}

		Map<LocalDate, List<ActivityDetailsDateWiseDTO>> map = new HashMap<>();

		tickets.forEach(t -> {
			Customer c = t.getCustomer();

			ActivityDetailsDateWiseDTO dto = new ActivityDetailsDateWiseDTO();
			dto.setActivity(t.getActivity());
			dto.setBookingTime(t.getBookingTime());
			dto.setCustomerDetails(new CustomerDetailsDTO(c.getName(), c.getAddress(), c.getMobileNumber(),
					c.getEmail(), c.getCustomerId()));
//			dto.setDate(t.getDate());
			dto.setSlot(t.getSlot());

			if (map.containsKey(t.getDate())) {
				map.get(t.getDate()).add(dto);
			} else {
				List<ActivityDetailsDateWiseDTO> dtos = new ArrayList<>();
				dtos.add(dto);

				map.put(t.getDate(), dtos);
			}
		});

//		map.

//		for (Map.Entry m : map.entrySet()) {
//			System.out.println(m.getKey() + " " + m.getValue());
//		}

		List<DateWiseDTO> list = new ArrayList<>();

		for (Map.Entry m : map.entrySet()) {

			DateWiseDTO dto = new DateWiseDTO();
			dto.setDate((LocalDate) m.getKey());
			dto.setActivityDetails((List<ActivityDetailsDateWiseDTO>) m.getValue());
			
			list.add(dto);
		}

		return list;
	}

	@Override
	public List<Ticket> getAllActivitiesForDays(Integer customerId, LocalDate fromDate, LocalDate toDate, String uuid)
			throws AdminException, ActivityException {

		String username = getValidatedUsernameForAdmin(uuid);
		
		Optional<Customer> opt = customerRepository.findById(customerId);

		if (opt.isEmpty()) {
			throw new CustomerException("No Customer Found");
		}

		Customer existingCustomer = opt.get();

		List<Ticket> tickets = ticketRepository.findByCustomerAndDateBetweenOrderByDate(existingCustomer, fromDate,
				toDate);

		if (tickets.isEmpty()) {
			throw new ActivityException("No Activities Found");
		}

		return tickets;

	}

	@Override
	public Double getTotalRevenue(String uuid) throws AdminException, TicketException {

		String username = getValidatedUsernameForAdmin(uuid);
		
		List<Ticket> tickets = ticketRepository.findAll();

		if (tickets.isEmpty()) {
			throw new TicketException("No Tickets Found To Calculate Revenue");
		}

		Double revenue = 0.0;

		for (Ticket t : tickets) {
			revenue += t.getActivity().getCharges();
		}

		return revenue;

	}

	@Override
	public String loginAdmin(ValidateUserDTO dto) throws AdminException {
		
		Admin savedAdmin = validateAdmin(dto);
		
		if(savedAdmin == null) {
			throw new AdminException("Admin Does Not Exist...");
		}
		
		return loginLogoutService.getUuid(dto.getUsername(), Role.ADMIN);
		
	}

	@Override
	public String getValidatedUsernameForAdmin(String uuid) throws AdminException {

		FlagDTO dto = loginLogoutService.validateUuid(uuid);

		if (dto.isFlag()) {
			if (dto.getRole() == Role.ADMIN) {

				return dto.getUsername();

			} else {
				throw new AdminException("User Authorization Failed");
			}
		} else {
			throw new AdminException("Please Log In First");
		}
		
	}

}
