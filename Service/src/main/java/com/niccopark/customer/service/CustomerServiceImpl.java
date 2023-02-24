package com.niccopark.customer.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.niccopark.dtos.FlagDTO;
import com.niccopark.dtos.UpdateUserPasswordDTO;
import com.niccopark.dtos.UpdateUserUsernameDTO;
import com.niccopark.dtos.UserUpdateDTO;
import com.niccopark.dtos.ValidateUserDTO;
import com.niccopark.entity.Admin;
import com.niccopark.entity.Customer;
import com.niccopark.entity.Role;
import com.niccopark.exceptions.CustomerException;
import com.niccopark.login.service.LoginLogoutService;
import com.niccopark.repository.AdminRepository;
import com.niccopark.repository.CustomerRepository;

@Service
public class CustomerServiceImpl implements CustomerService {

	@Autowired
	private CustomerRepository customerRepository;

	@Autowired
	private AdminRepository adminRepository;

	@Autowired
	private LoginLogoutService loginLogoutService;

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
		
		String username = getValidatedUsername(uuid);
		
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
	public Customer updateCustomerPassword(UpdateUserPasswordDTO dto) throws CustomerException {

		Customer savedCustomer = validateCustomer(new ValidateUserDTO(dto.getUsername(), dto.getOldPassword()));

		if (dto.getNewPassword() != null) {
			savedCustomer.setPassword(dto.getNewPassword());
		}

		return customerRepository.save(savedCustomer);

	}

	@Override
	public Customer updateCustomerUsername(UpdateUserUsernameDTO dto) throws CustomerException {

		Customer savedCustomer = validateCustomer(new ValidateUserDTO(dto.getOldUsername(), dto.getPassword()));

		if (savedCustomer != null && dto.getNewUsername() != null) {
			if (customerRepository.findByUsername(dto.getNewUsername()).isEmpty()) {
				savedCustomer.setUsername(dto.getNewUsername());

				return customerRepository.save(savedCustomer);
			} else {
				throw new CustomerException("Customer Already Exist With Username " + dto.getNewUsername());
			}
		} else {
			throw new CustomerException("Invalid Username or Password...");
		}

	}

	@Override
	public Customer deleteCustomer(Integer customerId) throws CustomerException {

		Customer savedCustomer = viewCustomer(customerId);

		customerRepository.delete(savedCustomer);

		return savedCustomer;

	}

	@Override
	public List<Customer> viewCustomers() throws CustomerException {

		List<Customer> customers = customerRepository.findAll();

		if (customers.isEmpty()) {
			throw new CustomerException("No Customers Found...");
		}

		return customers;

	}

	@Override
	public Customer viewCustomer(Integer customerId) throws CustomerException {

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
			throw new CustomerException("Customer Does not Exist...");
		}

		return loginLogoutService.getUuid(dto.getUsername(), Role.CUSTOMER);

	}

	@Override
	public String getValidatedUsername(String uuid) throws CustomerException {
		
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
