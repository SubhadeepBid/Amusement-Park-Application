package com.niccopark.dtos;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ShowUserDTO {

	private String name;
	
	private String username;

	private String address;

	private String mobileNumber;

	private String email;

}
