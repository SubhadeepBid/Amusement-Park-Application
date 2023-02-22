package com.niccopark.customer.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.niccopark.dtos.ValidateUserDTO;
import com.niccopark.dtos.ValidateUserPassword;
import com.niccopark.dtos.ValidateUserUpdateUsername;
import com.niccopark.entity.Customer;
import com.niccopark.exceptions.CustomerException;
import com.niccopark.repository.CustomerRepository;

@Service
public class CustomerServiceImpl implements CustomerService {
	@Autowired
	private CustomerRepository customerRepository;

	@Override
	public Customer insertCustomer(Customer customer) throws CustomerException {

		Customer savecustomer = customerRepository.findByUsername(customer.getUsername());
		if (savecustomer != null) {
			throw new CustomerException("Customer Alredy Exist");
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
		return customerRepository.save(customer);
	}

	@Override
	public Customer updateCustomerPassword(ValidateUserPassword vpudto) throws CustomerException {
		Customer savecustomer = validateCustomer(new ValidateUserDTO(vpudto.getUsername(), vpudto.getOldPassword()));
		if (vpudto.getNewPassword() != null) {
			savecustomer.setPassword(vpudto.getNewPassword());
		}
		return customerRepository.save(savecustomer);
	}

	@Override
	public Customer updateCustomerUsername(ValidateUserUpdateUsername vusernameDto) throws CustomerException {
		Customer customer = validateCustomer(
				new ValidateUserDTO(vusernameDto.getOldUsername(), vusernameDto.getPassword()));
		if (vusernameDto.getNewUsername() != null) {
			Customer savecustomer = customerRepository.findByUsername(customer.getUsername());
			if (savecustomer != null) {
				throw new CustomerException("Customer Alredy Exist");
			}
			customer.setUsername(vusernameDto.getNewUsername());

		}
		return customerRepository.save(customer);
	}

	@Override
	public Customer deleteCustomer(Integer customerId) throws CustomerException {
		
		Customer customer = viewCustomer(customerId);
		customerRepository.delete(customer);		
		return customer;
	}

	@Override
	public List<Customer> viewCustomers() throws CustomerException {
		List<Customer> customers= customerRepository.findAll();
		if(customers.isEmpty()) {
			throw new CustomerException("No customers found");
		}
		return customers;
	}

	@Override
	public Customer viewCustomer(Integer customerId) throws CustomerException {
		Optional<Customer> opt = customerRepository.findById(customerId);
		if(opt.isEmpty()) {
			throw new CustomerException("customer not found woth this id "+ customerId);
		}
		return opt.get();	
		
	}

	@Override
	public Customer validateCustomer(ValidateUserDTO dto) throws CustomerException {
		Customer customer = customerRepository.findByUsernameAndPassword(dto.getUsername(), dto.getPassword());
		if (customer == null) {
			throw new CustomerException("Customer Does not Exist");
		}
		return customer;
	}

}
