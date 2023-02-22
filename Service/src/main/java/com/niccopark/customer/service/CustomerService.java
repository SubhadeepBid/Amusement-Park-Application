package com.niccopark.customer.service;

import java.util.List;

import com.niccopark.entity.Customer;
import com.niccopark.exceptions.CustomerException;

public interface CustomerService {
public Customer insertCustomer(Customer customer) throws CustomerException;//repo
	
	public Customer updateCustomer(Customer customer) throws CustomerException;
	
	public Customer deleteCustomer(Integer customerId) throws CustomerException;
	
	public List<Customer> viewCustomers() throws CustomerException;
	
	public Customer viewCustomer(Integer customerId) throws CustomerException;
	
	public Customer validateCustomer(String username, String password) throws CustomerException;

}
