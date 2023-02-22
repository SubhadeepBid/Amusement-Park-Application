package com.niccopark.customer.service;

import java.util.List;

import com.niccopark.dtos.ValidateUserDTO;
import com.niccopark.dtos.ValidateUserPassword;
import com.niccopark.dtos.ValidateUserUpdateUsername;
import com.niccopark.entity.Customer;
import com.niccopark.exceptions.CustomerException;

public interface CustomerService {
	public Customer insertCustomer(Customer customer) throws CustomerException;// repo

	public Customer updateCustomerDetails(Customer customer) throws CustomerException;

	public Customer updateCustomerPassword(ValidateUserPassword vpudto) throws CustomerException;

	public Customer updateCustomerUsername(ValidateUserUpdateUsername vusernameDto) throws CustomerException;

	public Customer deleteCustomer(Integer customerId) throws CustomerException;

	public List<Customer> viewCustomers() throws CustomerException;

	public Customer viewCustomer(Integer customerId) throws CustomerException;

	public Customer validateCustomer(ValidateUserDTO dto) throws CustomerException;

}
