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

import com.niccopark.authentication.service.LoginLogoutService;
import com.niccopark.customer.service.CustomerService;
import com.niccopark.dtos.SampleUserDTO;
import com.niccopark.dtos.ShowUserDTO;
import com.niccopark.dtos.UpdateUserPasswordDTO;
import com.niccopark.dtos.UpdateUserUsernameDTO;
import com.niccopark.dtos.UserUpdateDTO;
import com.niccopark.dtos.ValidateUserDTO;

import jakarta.validation.Valid;

@RestController
@RequestMapping(value = "/customers")
public class CustomerController {

	@Autowired
	private CustomerService customerService;
	
	@Autowired
	private LoginLogoutService loginLogoutService;

	@PostMapping("/customer_sign_up")
	public ResponseEntity<ShowUserDTO> insertCustomerHandler(@Valid @RequestBody SampleUserDTO user) {
		
		return new ResponseEntity<>(customerService.insertCustomer(user), HttpStatus.CREATED);
		
	}

	@PutMapping("/update_customer_details")
	public ResponseEntity<ShowUserDTO> updateCustomerDetailsHandler(@Valid @RequestBody UserUpdateDTO dto, @RequestParam String customerUuid) {

		return new ResponseEntity<>(customerService.updateCustomerDetails(dto, customerUuid), HttpStatus.OK);
		
	}

	@PutMapping("/update_customer_password")
	public ResponseEntity<ShowUserDTO> updateCustomerPasswordHandler(@Valid @RequestBody UpdateUserPasswordDTO updateUserPasswordDTO, @RequestParam String customerUuid) {
		
		return new ResponseEntity<>(customerService.updateCustomerPassword(updateUserPasswordDTO, customerUuid), HttpStatus.OK);
		
	}

	@PutMapping("/update_customer_username")
	public ResponseEntity<ShowUserDTO> updateCustomerUsernamehandler(@Valid @RequestBody UpdateUserUsernameDTO updateUserUsernameDTO, @RequestParam String customerUuid) {
		
		return new ResponseEntity<>(customerService.updateCustomerUsername(updateUserUsernameDTO, customerUuid), HttpStatus.OK);
		
	}

	@DeleteMapping("/delete_customer/{customerId}")
	public ResponseEntity<ShowUserDTO> deleteCustomerHandler(@PathVariable("customerId") Integer customerId, @RequestParam String adminUuid) {
		
		return new ResponseEntity<>(customerService.deleteCustomer(customerId, adminUuid), HttpStatus.OK);
		
	}

	@GetMapping("/get_all_customers")
	public ResponseEntity<List<ShowUserDTO>> viewCustomersHandler(@RequestParam String adminUuid) {
		
		return new ResponseEntity<>(customerService.viewAllCustomers(adminUuid), HttpStatus.OK);
		
	}

	@GetMapping("/get_customer_byId/{customerId}")
	public ResponseEntity<ShowUserDTO> viewCustomerHandler(@PathVariable("customerId") Integer customerId, @RequestParam String adminUuid) {
		
		return new ResponseEntity<>(customerService.viewCustomer(customerId, adminUuid), HttpStatus.OK);
		
	}

	@PostMapping("/login_customer")
	public ResponseEntity<String> loginCustomerHandler(@Valid @RequestBody ValidateUserDTO validateUserDTO) {
		
		return new ResponseEntity<>(customerService.loginCustomer(validateUserDTO), HttpStatus.OK);
		
	}
	
	@DeleteMapping("/log_out_customer")
	public ResponseEntity<String> logOutHandler(@RequestParam  String customerUuid) {
		
		return new ResponseEntity<>(loginLogoutService.logOut(customerUuid), HttpStatus.OK);
		
	}
	
}
