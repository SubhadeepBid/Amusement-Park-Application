package com.niccopark.customer.service;

import java.util.List;

import com.niccopark.dtos.UpdateUserPasswordDTO;
import com.niccopark.dtos.UpdateUserUsernameDTO;
import com.niccopark.dtos.ValidateUserDTO;
import com.niccopark.entity.Customer;
import com.niccopark.exceptions.CustomerException;

public interface CustomerService {
	public Customer insertCustomer(Customer customer) throws CustomerException;// repo

	public Customer updateCustomerDetails(Customer customer) throws CustomerException;

	public Customer updateCustomerPassword(UpdateUserPasswordDTO dto) throws CustomerException;

	public Customer updateCustomerUsername(UpdateUserUsernameDTO dto) throws CustomerException;

	public Customer deleteCustomer(Integer customerId) throws CustomerException;

	public List<Customer> viewCustomers() throws CustomerException;

	public Customer viewCustomer(Integer customerId) throws CustomerException;

	public Customer validateCustomer(ValidateUserDTO dto) throws CustomerException;

}
