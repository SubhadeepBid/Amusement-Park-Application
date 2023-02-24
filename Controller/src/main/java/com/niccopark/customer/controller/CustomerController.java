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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.niccopark.customer.service.CustomerService;
import com.niccopark.dtos.UpdateUserPasswordDTO;
import com.niccopark.dtos.UpdateUserUsernameDTO;
import com.niccopark.dtos.UserUpdateDTO;
import com.niccopark.dtos.ValidateUserDTO;
import com.niccopark.entity.Customer;
import com.niccopark.login.service.LoginLogoutService;

import jakarta.validation.Valid;

@RestController
@RequestMapping(value = "/customers")
public class CustomerController {

	@Autowired
	private CustomerService customerService;
	
	@Autowired
	private LoginLogoutService loginLogoutService;

	@PostMapping("/add_customer")
	public ResponseEntity<Customer> insertCustomerHandler(@Valid @RequestBody Customer customer) {
		
		return new ResponseEntity<>(customerService.insertCustomer(customer), HttpStatus.CREATED);
		
	}

	@PutMapping("/update_customer_details")
	public ResponseEntity<Customer> updateCustomerDetailsHandler(@Valid @RequestBody UserUpdateDTO dto, @RequestParam String uuid) {

		return new ResponseEntity<>(customerService.updateCustomerDetails(dto, uuid), HttpStatus.OK);
		
	}

	@PutMapping("/update_customer_password")
	public ResponseEntity<Customer> updateCustomerPasswordHandler(@Valid @RequestBody UpdateUserPasswordDTO updateUserPasswordDTO) {
		
		return new ResponseEntity<>(customerService.updateCustomerPassword(updateUserPasswordDTO), HttpStatus.OK);
		
	}

	@PutMapping("/update_customer_username")
	public ResponseEntity<Customer> updateCustomerUsernamehandler(@Valid @RequestBody UpdateUserUsernameDTO updateUserUsernameDTO) {
		
		return new ResponseEntity<>(customerService.updateCustomerUsername(updateUserUsernameDTO), HttpStatus.OK);
		
	}

	@DeleteMapping("/delete_customer/{customerId}")
	public ResponseEntity<Customer> deleteCustomerHandler(@PathVariable("customerId") Integer customerId) {
		
		return new ResponseEntity<>(customerService.deleteCustomer(customerId), HttpStatus.OK);
		
	}

	@GetMapping("/get_customers")
	public ResponseEntity<List<Customer>> viewCustomersHandler() {
		
		return new ResponseEntity<>(customerService.viewCustomers(), HttpStatus.OK);
		
	}

	@GetMapping("/get_customer_byId/{customerId}")
	public ResponseEntity<Customer> viewCustomerHandler(@PathVariable("customerId") Integer customerId) {
		
		return new ResponseEntity<>(customerService.viewCustomer(customerId), HttpStatus.OK);
		
	}

	@PostMapping("/login_customer")
	public ResponseEntity<String> loginCustomerHandler(@Valid @RequestBody ValidateUserDTO validateUserDTO) {
		
		return new ResponseEntity<>(customerService.loginCustomer(validateUserDTO), HttpStatus.OK);
		
	}
	
//	public ResponseEntity<String> validateCustomerHandler(@Valid @RequestBody ValidateUserDTO validateUserDTO) {
//		
//		return new ResponseEntity<>(customerService.validateCustomer(validateUserDTO), HttpStatus.OK);
//		
//	}
	
	@DeleteMapping("/log_out_customer/{uuid}")
	public ResponseEntity<String> logOutHandler(@PathVariable("uuid") String uuid) {
		
		return new ResponseEntity<>(loginLogoutService.logOut(uuid), HttpStatus.OK);
		
	}
	
}
