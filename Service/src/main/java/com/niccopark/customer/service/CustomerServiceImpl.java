package com.niccopark.customer.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.niccopark.admin.service.AdminService;
import com.niccopark.authentication.service.LoginLogoutService;
import com.niccopark.dtos.FlagDTO;
import com.niccopark.dtos.UpdateUserPasswordDTO;
import com.niccopark.dtos.UpdateUserUsernameDTO;
import com.niccopark.dtos.UserUpdateDTO;
import com.niccopark.dtos.ValidateUserDTO;
import com.niccopark.entity.Admin;
import com.niccopark.entity.CurrentUserSession;
import com.niccopark.entity.Customer;
import com.niccopark.entity.Role;
import com.niccopark.exceptions.AdminException;
import com.niccopark.exceptions.CustomerException;
import com.niccopark.repository.AdminRepository;
import com.niccopark.repository.CurrentUserSessionRepository;
import com.niccopark.repository.CustomerRepository;

@Service
public class CustomerServiceImpl implements CustomerService {

	@Autowired
	private CurrentUserSessionRepository currentUserSessionRepository;
	
	@Autowired
	private CustomerRepository customerRepository;

	@Autowired
	private AdminRepository adminRepository;

	@Autowired
	private LoginLogoutService loginLogoutService;
	
	@Autowired
	private AdminService adminService;

	@Override
	public Customer insertCustomer(Customer customer) throws CustomerException {

		Optional<Customer> opt = customerRepository.findByUsername(customer.getUsername());

		if (opt.isPresent()) {
			throw new CustomerException("Username Already Exists...");
		}

		Optional<Admin> opt1 = adminRepository.findByUsername(customer.getUsername());

		if (opt1.isPresent()) {
			throw new CustomerException("Username Already Exists...");
		}

		return customerRepository.save(customer);

	}

	@Override
	public Customer updateCustomerDetails(UserUpdateDTO dto, String uuid) throws CustomerException {
		
		String username = getValidatedUsernameForCustomer(uuid);
		
		Customer savecustomer = customerRepository.findByUsername(username).get();

		if (dto.getAddress() != null) {
			savecustomer.setAddress(dto.getAddress());
		}
		if (dto.getMobileNumber() != null) {
			savecustomer.setMobileNumber(dto.getMobileNumber());
		}
		if (dto.getEmail() != null) {
			savecustomer.setEmail(dto.getEmail());
		}
		if (dto.getName() != null) {
			savecustomer.setName(dto.getName());
		}

		return customerRepository.save(savecustomer);

	}

	@Override
	public Customer updateCustomerPassword(UpdateUserPasswordDTO dto, String uuid) throws CustomerException {

		String username = getValidatedUsernameForCustomer(uuid);
		
		Customer savedCustomer = validateCustomer(new ValidateUserDTO(username, dto.getOldPassword()));

		if (dto.getNewPassword() != null) {
			savedCustomer.setPassword(dto.getNewPassword());
		}

		return customerRepository.save(savedCustomer);

	}

	@Override
	public Customer updateCustomerUsername(UpdateUserUsernameDTO dto, String uuid) throws CustomerException {

		String username = getValidatedUsernameForCustomer(uuid);
		
		Customer savedCustomer = validateCustomer(new ValidateUserDTO(username, dto.getPassword()));

		if (savedCustomer != null && dto.getNewUsername() != null) {
			if (customerRepository.findByUsername(dto.getNewUsername()).isEmpty() && adminRepository.findByUsername(dto.getNewUsername()).isEmpty()) {
				savedCustomer.setUsername(dto.getNewUsername());

				CurrentUserSession session = currentUserSessionRepository.findById(uuid).get();
				session.setUsername(dto.getNewUsername());
				
				currentUserSessionRepository.save(session);
				return customerRepository.save(savedCustomer);
			} else {
				throw new CustomerException("Username Already Exists...");
			}
		} else {
			throw new CustomerException("Invalid Password...");
		}

	}

	@Override
	public Customer deleteCustomer(Integer customerId, String uuid) throws AdminException, CustomerException {

		String username = adminService.getValidatedUsernameForAdmin(uuid);

		Optional<Customer> opt = customerRepository.findById(customerId);
		
		if (opt.isEmpty()) {
			throw new CustomerException("Customer Not Found With Customer ID : " + customerId);
		}

		Customer savedCustomer = opt.get();

		customerRepository.delete(savedCustomer);

		return savedCustomer;

	}

	@Override
	public List<Customer> viewCustomers(String uuid) throws CustomerException {

		String username = adminService.getValidatedUsernameForAdmin(uuid);

		List<Customer> customers = customerRepository.findAll();

		if (customers.isEmpty()) {
			throw new CustomerException("No Customers Found...");
		}

		return customers;

	}

	@Override
	public Customer viewCustomer(Integer customerId, String uuid) throws CustomerException {

		String username = adminService.getValidatedUsernameForAdmin(uuid);

		Optional<Customer> opt = customerRepository.findById(customerId);

		if (opt.isEmpty()) {
			throw new CustomerException("Customer Not Found With Customer ID : " + customerId);
		}

		return opt.get();

	}

	@Override
	public Customer validateCustomer(ValidateUserDTO dto) throws CustomerException {

		Optional<Customer> opt = customerRepository.findByUsernameAndPassword(dto.getUsername(), dto.getPassword());

		if (opt.isEmpty()) {
			throw new CustomerException("Customer Does not Exist...");
		}

		return opt.get();

	}

	@Override
	public String loginCustomer(ValidateUserDTO dto) throws CustomerException {

		Customer savedCustomer = validateCustomer(dto);

		if (savedCustomer == null) {
			throw new CustomerException("Customer Does Not Exist...");
		}

		return loginLogoutService.getUuid(dto.getUsername(), Role.CUSTOMER);

	}

	@Override
	public String getValidatedUsernameForCustomer(String uuid) throws CustomerException {
		
		FlagDTO dto = loginLogoutService.validateUuid(uuid);

		if (dto.isFlag()) {
			if (dto.getRole() == Role.CUSTOMER) {

				return dto.getUsername();

			} else {
				throw new CustomerException("User Authorization Failed");
			}
		} else {
			throw new CustomerException("Please Log In First");
		}
		
	}

}
