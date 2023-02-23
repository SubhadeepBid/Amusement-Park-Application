package com.niccopark.customer.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.niccopark.customer.service.CustomerService;
import com.niccopark.dtos.UpdateUserPasswordDTO;
import com.niccopark.dtos.UpdateUserUsernameDTO;
import com.niccopark.dtos.ValidateUserDTO;
import com.niccopark.entity.Customer;

@RestController
@RequestMapping(value = "/customers")
public class CustomerController {

	@Autowired
	private CustomerService customerService;

	@PostMapping("/add_customer")
	public ResponseEntity<Customer> insertCustomer(@RequestBody Customer customer) {
		
		return new ResponseEntity<>(customerService.insertCustomer(customer), HttpStatus.CREATED);
		
	}

	@PutMapping("/update_customer_details")
	public ResponseEntity<Customer> updateCustomerDetails(@RequestBody Customer customer) {

		return new ResponseEntity<>(customerService.updateCustomerDetails(customer), HttpStatus.OK);
		
	}

	@PutMapping("/update_customer_password")
	public ResponseEntity<Customer> updateCustomerPassword(@RequestBody UpdateUserPasswordDTO updateUserPasswordDTO) {
		
		return new ResponseEntity<>(customerService.updateCustomerPassword(updateUserPasswordDTO), HttpStatus.OK);
		
	}

	@PutMapping("/update_customer_username")
	public ResponseEntity<Customer> updateCustomerUsernamehandler(@RequestBody UpdateUserUsernameDTO updateUserUsernameDTO) {
		
		return new ResponseEntity<>(customerService.updateCustomerUsername(updateUserUsernameDTO), HttpStatus.OK);
		
	}

	@DeleteMapping("/delete_customer/{customerId}")
	public ResponseEntity<Customer> deleteCustomer(@PathVariable("customerId") Integer customerId) {
		
		return new ResponseEntity<>(customerService.deleteCustomer(customerId), HttpStatus.OK);
		
	}

	@GetMapping("/get_customers")
	public ResponseEntity<List<Customer>> viewCustomers() {
		
		return new ResponseEntity<>(customerService.viewCustomers(), HttpStatus.OK);
		
	}

	@GetMapping("/get_customer_byId/{customerId}")
	public ResponseEntity<Customer> viewCustomer(@PathVariable("customerId") Integer customerId) {
		
		return new ResponseEntity<>(customerService.viewCustomer(customerId), HttpStatus.OK);
		
	}

	@PostMapping("/validate_customer")
	public ResponseEntity<Customer> validateCustomer(@RequestBody ValidateUserDTO validateUserDTO) {
		
		return new ResponseEntity<>(customerService.validateCustomer(validateUserDTO), HttpStatus.OK);
		
	}
}
