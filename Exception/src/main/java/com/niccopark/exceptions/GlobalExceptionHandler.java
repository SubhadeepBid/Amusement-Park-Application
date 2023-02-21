package com.niccopark.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;

public class GlobalExceptionHandler extends RuntimeException{
	@ExceptionHandler(ActivityException.class)
	public ResponseEntity<String> myIllegalHandler(ActivityException ie)  {
		System.out.println("inside myHandler method...");
		
	 return new ResponseEntity<String>(ie.getMessage(),HttpStatus.BAD_REQUEST);
		

	}
	
	@ExceptionHandler(AdminException.class)
	public ResponseEntity<String> myIllegalHandler(AdminException ie)  {
		System.out.println("inside myHandler method...");
		
	 return new ResponseEntity<String>(ie.getMessage(),HttpStatus.BAD_REQUEST);
		

	}
	
	@ExceptionHandler(CustomerException.class)
	public ResponseEntity<String> CustomerException(CustomerException ie)  {
		System.out.println("inside myHandler method...");
		
	 return new ResponseEntity<String>(ie.getMessage(),HttpStatus.BAD_REQUEST);
		

	}
	@ExceptionHandler(TicketException.class)
	public ResponseEntity<String> myIllegalHandler(TicketException ie)  {
		System.out.println("inside myHandler method...");
		
	 return new ResponseEntity<String>(ie.getMessage(),HttpStatus.BAD_REQUEST);
		

	}
	

	//to handle any other type of Exception
	@ExceptionHandler(Exception.class)
	public ResponseEntity<String> myExceptionHandler(Exception e) {
		System.out.println("inside myHandler method...");
		
		 return new ResponseEntity<String>(e.getMessage(),HttpStatus.BAD_REQUEST);
		

	}


}
