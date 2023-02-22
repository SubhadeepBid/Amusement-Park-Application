package com.niccopark.customer.controller;

import java.util.List;

import org.apache.catalina.connector.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.niccopark.customer.service.CustomerService;
import com.niccopark.dtos.UpdateUserPasswordDTO;
import com.niccopark.dtos.UpdateUserUsernameDTO;
import com.niccopark.dtos.ValidateUserDTO;
import com.niccopark.entity.Customer;

@RestController
//@RequestMapping(value = "/Customer")
public class CustomerController {

	@Autowired
	private CustomerService customerService;

	@PostMapping("/customers")
	public ResponseEntity<Customer> insertCustomer(@RequestBody Customer customer) {
		return new ResponseEntity<>(customerService.insertCustomer(customer), HttpStatus.CREATED);
	}

	@PutMapping("/updateCustomer")
	public ResponseEntity<Customer> updateCustomerDetails(@RequestBody Customer customer) {

		return new ResponseEntity<>(customerService.updateCustomerDetails(customer), HttpStatus.CREATED);
	}

	@PutMapping("/updateCustomerPassword")
	public ResponseEntity<Customer> updateCustomerPassword(@RequestBody UpdateUserPasswordDTO updateUserPasswordDTO) {
		return new ResponseEntity<>(customerService.updateCustomerPassword(updateUserPasswordDTO), HttpStatus.OK);
	}
	@PutMapping("/updateCustomerUsername")
	public ResponseEntity<Customer> updateCustomerUsernamehandler(@RequestBody UpdateUserUsernameDTO updateUserUsernameDTO){
		return new ResponseEntity<>(customerService.updateCustomerUsername(updateUserUsernameDTO),HttpStatus.CREATED);
	}
	@DeleteMapping("/deleteCustomer/{cid}")
	public ResponseEntity<Customer> deleteCustomer(@PathVariable("cid") Integer cid ){
		return new ResponseEntity<>(customerService.deleteCustomer(cid),HttpStatus.OK);
	}
	@GetMapping("/customerbyList")
	public ResponseEntity<List<Customer>>viewCustomers(){
		return new ResponseEntity<List<Customer>>(customerService.viewCustomers(),HttpStatus.ACCEPTED);
	}
	
	@GetMapping("/customerbyId/{cid}")
	public ResponseEntity<Customer>viewCustomer(@PathVariable("cid")Integer cid ){
		return new ResponseEntity<>(customerService.viewCustomer(cid),HttpStatus.OK);
	}
	@PostMapping("/validateCustomer")
	public ResponseEntity<Customer>validateCustomer(@RequestBody ValidateUserDTO validateUserDTO){
		return new ResponseEntity<>(customerService.validateCustomer(validateUserDTO),HttpStatus.ACCEPTED);
	}
}
