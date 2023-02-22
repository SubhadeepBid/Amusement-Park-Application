package com.niccopark.customer.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.niccopark.customer.service.CustomerService;
import com.niccopark.entity.Customer;

@RestController
//@RequestMapping(value = "/Customer")
public class CustomerController {

	@Autowired
	private CustomerService customerService;

	@PostMapping("/customers")
	public ResponseEntity<Customer> insertCustomer(@RequestBody Customer customer){
		return new ResponseEntity<>(customerService.insertCustomer(customer), HttpStatus.CREATED);
	}

//	public ResponseEntity<Customer> updateCustomerDetails(@RequestBody  
//	gtgt

  

//)
}
