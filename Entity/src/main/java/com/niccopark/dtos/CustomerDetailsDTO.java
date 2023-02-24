package com.niccopark.dtos;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CustomerDetailsDTO {

	private String name;

	private String address;

	private String mobileNumber;

	private String email;
	
	private Integer customerId;

}
