package com.niccopark.customer.service;

import java.util.List;

import com.niccopark.dtos.ShowUserDTO;
import com.niccopark.dtos.UpdateUserPasswordDTO;
import com.niccopark.dtos.UpdateUserUsernameDTO;
import com.niccopark.dtos.UserUpdateDTO;
import com.niccopark.dtos.ValidateUserDTO;
import com.niccopark.entity.Customer;
import com.niccopark.entity.User;
import com.niccopark.exceptions.AdminException;
import com.niccopark.exceptions.CustomerException;

public interface CustomerService {
	
	public ShowUserDTO insertCustomer(User user) throws CustomerException;

	public ShowUserDTO updateCustomerDetails(UserUpdateDTO dto, String uuid) throws CustomerException;

	public ShowUserDTO updateCustomerPassword(UpdateUserPasswordDTO dto, String uuid) throws CustomerException;

	public ShowUserDTO updateCustomerUsername(UpdateUserUsernameDTO dto, String uuid) throws CustomerException;

	public ShowUserDTO deleteCustomer(Integer customerId, String uuid) throws AdminException, CustomerException;

	public List<ShowUserDTO> viewAllCustomers(String uuid) throws CustomerException;

	public ShowUserDTO viewCustomer(Integer customerId, String uuid) throws CustomerException;

	public Customer validateCustomer(ValidateUserDTO dto) throws CustomerException;

	public String loginCustomer(ValidateUserDTO dto) throws CustomerException;

	public String getValidatedUsernameForCustomer(String uuid) throws CustomerException;

}
