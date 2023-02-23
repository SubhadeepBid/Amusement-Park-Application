package com.niccopark.customer.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.niccopark.dtos.UpdateUserPasswordDTO;
import com.niccopark.dtos.UpdateUserUsernameDTO;
import com.niccopark.dtos.ValidateUserDTO;
import com.niccopark.entity.Customer;
import com.niccopark.exceptions.CustomerException;
import com.niccopark.repository.CustomerRepository;

@Service
public class CustomerServiceImpl implements CustomerService {
	
	@Autowired
	private CustomerRepository customerRepository;

	@Override
	public Customer insertCustomer(Customer customer) throws CustomerException {

		Optional<Customer> opt = customerRepository.findByUsername(customer.getUsername());
		
		if (opt.isPresent()) {
			throw new CustomerException("Customer Already Exists...");
		}
		
		return customerRepository.save(customer);
		
	}

	@Override
	public Customer updateCustomerDetails(Customer customer) throws CustomerException {
		
		Customer savecustomer = validateCustomer(new ValidateUserDTO(customer.getUsername(), customer.getPassword()));
		
		if (customer.getAddress() != null) {
			savecustomer.setAddress(customer.getAddress());
		}
		if (customer.getMobileNumber() != null) {
			savecustomer.setMobileNumber(customer.getMobileNumber());
		}
		if (customer.getEmail() != null) {
			savecustomer.setEmail(customer.getEmail());
		}
		if (customer.getName() != null) {
			savecustomer.setName(customer.getName());
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
		
		if(savedCustomer != null && dto.getNewUsername() != null) {
			if(customerRepository.findByUsername(dto.getNewUsername()).isEmpty()) {
				savedCustomer.setUsername(dto.getNewUsername());
				
				return customerRepository.save(savedCustomer);
			}
			else {
				throw new CustomerException("Customer Already Exist With Username " + dto.getNewUsername());
			}
		}
		else {
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
		
		List<Customer> customers= customerRepository.findAll();
		
		if(customers.isEmpty()) {
			throw new CustomerException("No Customers Found...");
		}
		
		return customers;
		
	}

	@Override
	public Customer viewCustomer(Integer customerId) throws CustomerException {
		
		Optional<Customer> opt = customerRepository.findById(customerId);
		
		if(opt.isEmpty()) {
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

}
